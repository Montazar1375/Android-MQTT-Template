package done.tech.home.mobile.data.datasource.mqtt.impl

import done.tech.home.mobile.data.datasource.mqtt.MQTTDataSource
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.IMqttActionListener
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.IMqttToken
import org.eclipse.paho.client.mqttv3.MqttCallback
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttException
import org.eclipse.paho.client.mqttv3.MqttMessage
import timber.log.Timber
import javax.inject.Inject

class DefaultMQTTDataSource @Inject constructor(
    private val client: MqttAndroidClient,
    private val connectionOptions: MqttConnectOptions
) : MQTTDataSource {

    private val subscribes = mutableMapOf<String, MutableStateFlow<String>>()
    private var isConnected = MutableStateFlow(false)

    init {
        @OptIn(DelicateCoroutinesApi::class)
        GlobalScope.launch {
            while (true) {
                delay(2000)
                if (!isConnected.value && client.isConnected) resubscribe()
            }
        }
    }

    override suspend fun connect() {
        return callbackFlow {
            client.setCallback(object : MqttCallback {
                override fun connectionLost(cause: Throwable?) {
                    Timber.e("Connection lost!")
                    isConnected.value = false
                }

                override fun messageArrived(topic: String?, message: MqttMessage?) {
                    Timber.i("messageArrived \n ${message.toString()}")
                    subscribes[topic]?.value = message.toString()
                }

                override fun deliveryComplete(token: IMqttDeliveryToken?) {
                    Timber.i("deliveryComplete")
                }
            })

            try {
                val token = client.connect(connectionOptions)
                token.userContext = null
                token.actionCallback = object : IMqttActionListener {
                    override fun onSuccess(asyncActionToken: IMqttToken) {
                        Timber.d("MQTT is connected")
                        trySend(Unit)
                    }

                    override fun onFailure(asyncActionToken: IMqttToken, exception: Throwable) {
                        Timber.d("MQTT first connection attempt failed !")
                        exception.printStackTrace()
                    }
                }
            } catch (e: MqttException) {
                e.printStackTrace()
            }

            awaitClose { }
        }.first()
    }

    private fun resubscribe() {
        if (isConnected.value) return
        Timber.w("Resubscribe all topics!")
        subscribes.forEach {
            client.subscribe(it.key, 0)
        }
        isConnected.value = true
    }

    override fun subscribe(topic: String): Flow<String> {
        return if (subscribes[topic] != null) subscribes[topic]!!
        else {
            client.subscribe(topic, 0)
            val flow = MutableStateFlow("")
            subscribes[topic] = flow
            subscribes[topic]!!
        }
    }


    override suspend fun publish(topic: String, message: ByteArray) {
        return callbackFlow {
            client.publish(topic, message, 2, true, null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    Timber.i("Publishing message was successful!")
                    trySend(Unit)
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    Timber.i("Publishing message failed!")
                    exception?.printStackTrace()
                }
            })
            awaitClose {}
        }.first()
    }

    override fun isConnected() = isConnected


}