package done.tech.home.mobile.ui.screen.home.model

import kotlinx.serialization.Serializable

@Serializable
data class Security(
    val Mac: String,
    val Pass: String
)