package done.tech.home.mobile.data.datasource.mqtt.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import done.tech.home.mobile.data.datasource.mqtt.MQTTDataSource
import done.tech.home.mobile.data.datasource.mqtt.impl.DefaultMQTTDataSource
import kotlinx.serialization.json.Json
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MqttModule {

    @Provides
    @Singleton
    fun provideMqttClient(
        @ApplicationContext context: Context,
    ): MqttAndroidClient {
        val clientId = MqttClient.generateClientId()

        val client = MqttAndroidClient(
            context.applicationContext,
            "ssl://a1ac8d2cf85949888902fda264117496.s1.eu.hivemq.cloud",
            clientId
        )

        return client
    }

    @Provides
    @Singleton
    fun provideMqttConnectionOption(): MqttConnectOptions {
        val options = MqttConnectOptions()
        options.userName = "mohammad_j76"
        options.password = "MohammadJavadAbbasi@76".toCharArray()
        options.isAutomaticReconnect = true
        options.maxReconnectDelay = 10000
        return options
    }

    @Provides
    @Singleton
    fun provideJSON(): Json {
        return Json {
            ignoreUnknownKeys = true
            prettyPrint= true
            explicitNulls = false
            encodeDefaults = true
        }
    }

}


@Module
@InstallIn(SingletonComponent::class)
interface MqttModuleBind{
    @Binds
    fun provideMQTTDataSource(datasource:DefaultMQTTDataSource):MQTTDataSource
}