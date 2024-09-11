package done.tech.home.mobile.ui.theme.tokens

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import done.tech.home.mobile.R

internal val Peyda = FontFamily(
    Font(R.font.peyda_fa_num_bold, FontWeight.Bold),
    Font(R.font.peyda_fa_num_medium, FontWeight.Medium),
    Font(R.font.peyda_fa_num_regular, FontWeight.Normal),
)

internal object AppTypefaceTokens {
    val Plain = Peyda
}