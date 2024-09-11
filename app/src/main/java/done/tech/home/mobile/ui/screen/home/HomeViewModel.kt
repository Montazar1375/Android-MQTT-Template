package done.tech.home.mobile.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import done.tech.home.mobile.data.datasource.mqtt.MQTTDataSource
import done.tech.home.mobile.ui.screen.home.model.TempCoffeeMakerDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mqtt: MQTTDataSource, private val json: Json,
) : ViewModel() {

    private var coffeeMakerMQTTState: MutableStateFlow<TempCoffeeMakerDataModel> =
        MutableStateFlow(TempCoffeeMakerDataModel())
    val mqttConnection = mqtt.isConnected()

    init {
        viewModelScope.launch {
            mqtt.connect()
//            mqtt.subscribe("AndroidApp/TV").map {
//                Timber.e("From Topic: AndroidApp/TV")
//                try {
//                    json.decodeFromString<TempCoffeeMakerDataModel>(it)
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                    TempCoffeeMakerDataModel()
//                }
//            }.collect {
//                coffeeMakerMQTTState.value = it
//            }
            mqtt.subscribe("Device").map {
                Timber.e("From Topic: Device")
                try {
                    json.decodeFromString<TempCoffeeMakerDataModel>(it)
                } catch (e: Exception) {
                    e.printStackTrace()
                    TempCoffeeMakerDataModel()
                }
            }.collect {
                coffeeMakerMQTTState.value = it
            }
        }
    }

    fun getCoffeeMakerState(): Flow<TempCoffeeMakerDataModel> = coffeeMakerMQTTState

    fun updateCoffeeMakerStatus(model: TempCoffeeMakerDataModel) {
        viewModelScope.launch {
            mqtt.publish("AndroidApp/TV", json.encodeToString(model).toByteArray())
        }
    }
}