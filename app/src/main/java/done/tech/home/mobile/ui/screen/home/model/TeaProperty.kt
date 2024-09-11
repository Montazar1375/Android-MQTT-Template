package done.tech.home.mobile.ui.screen.home.model

import kotlinx.serialization.Serializable

@Serializable
data class TeaProperty(
    val Cups: Int,
    val Temp: Int
)