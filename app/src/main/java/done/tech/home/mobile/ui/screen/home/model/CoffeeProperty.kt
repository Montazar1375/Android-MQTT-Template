package done.tech.home.mobile.ui.screen.home.model

import kotlinx.serialization.Serializable

@Serializable
data class CoffeeProperty(
    val Cup: Int,
    val GinderLevel: Int
)