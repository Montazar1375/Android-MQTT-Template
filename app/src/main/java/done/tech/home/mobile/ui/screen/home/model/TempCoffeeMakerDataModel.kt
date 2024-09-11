package done.tech.home.mobile.ui.screen.home.model

import kotlinx.serialization.Serializable

@Serializable
data class TempCoffeeMakerDataModel(
    val Coffee: Boolean = false,
    val CoffeeProperty: CoffeeProperty = CoffeeProperty(0, 0),
    val Security: Security = Security("00:00:00:00:00:00", "1234"),
    val Tea: Boolean = false,
    val TeaProperty: TeaProperty = TeaProperty(0, 0),
    val UpdateTime: Int = 0,
    val State: String = "Ready"
)