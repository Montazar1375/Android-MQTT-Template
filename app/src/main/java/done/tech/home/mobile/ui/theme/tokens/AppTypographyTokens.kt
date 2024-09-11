package done.tech.home.mobile.ui.theme.tokens

import androidx.compose.ui.text.TextStyle

internal object AppTypographyTokens {
    val BodyLarge =
        TextStyle.Default.copy(
            fontFamily = AppTypeScaleTokens.BodyLargeFont,
            fontWeight = AppTypeScaleTokens.BodyLargeWeight,
            fontSize = AppTypeScaleTokens.BodyLargeSize,
            letterSpacing = AppTypeScaleTokens.BodyLargeTracking
        )
    val BodyMedium =
        TextStyle.Default.copy(
            fontFamily = AppTypeScaleTokens.BodyMediumFont,
            fontWeight = AppTypeScaleTokens.BodyMediumWeight,
            fontSize = AppTypeScaleTokens.BodyMediumSize,
            letterSpacing = AppTypeScaleTokens.BodyMediumTracking
        )
    val BodySmall =
        TextStyle.Default.copy(
            fontFamily = AppTypeScaleTokens.BodySmallFont,
            fontWeight = AppTypeScaleTokens.BodySmallWeight,
            fontSize = AppTypeScaleTokens.BodySmallSize,
            letterSpacing = AppTypeScaleTokens.BodySmallTracking
        )
    val HeadlineLarge =
        TextStyle.Default.copy(
            fontFamily = AppTypeScaleTokens.HeadlineLargeFont,
            fontWeight = AppTypeScaleTokens.HeadlineLargeWeight,
            fontSize = AppTypeScaleTokens.HeadlineLargeSize,
            letterSpacing = AppTypeScaleTokens.HeadlineLargeTracking
        )
    val HeadlineMedium =
        TextStyle.Default.copy(
            fontFamily = AppTypeScaleTokens.HeadlineMediumFont,
            fontWeight = AppTypeScaleTokens.HeadlineMediumWeight,
            fontSize = AppTypeScaleTokens.HeadlineMediumSize,
            letterSpacing = AppTypeScaleTokens.HeadlineMediumTracking
        )
    val HeadlineSmall =
        TextStyle.Default.copy(
            fontFamily = AppTypeScaleTokens.HeadlineSmallFont,
            fontWeight = AppTypeScaleTokens.HeadlineSmallWeight,
            fontSize = AppTypeScaleTokens.HeadlineSmallSize,
            letterSpacing = AppTypeScaleTokens.HeadlineSmallTracking
        )
    val LabelLarge =
        TextStyle.Default.copy(
            fontFamily = AppTypeScaleTokens.LabelLargeFont
        )
    val TitleLarge =
        TextStyle.Default.copy(
            fontFamily = AppTypeScaleTokens.TitleLargeFont,
            fontWeight = AppTypeScaleTokens.TitleLargeWeight,
            fontSize = AppTypeScaleTokens.TitleLargeSize,
            letterSpacing = AppTypeScaleTokens.TitleLargeTracking
        )
    val TitleMedium =
        TextStyle.Default.copy(
            fontFamily = AppTypeScaleTokens.TitleMediumFont,
            fontWeight = AppTypeScaleTokens.TitleMediumWeight,
            fontSize = AppTypeScaleTokens.TitleMediumSize,
            letterSpacing = AppTypeScaleTokens.TitleMediumTracking
        )
    val TitleSmall =
        TextStyle.Default.copy(
            fontFamily = AppTypeScaleTokens.TitleSmallFont,
            fontWeight = AppTypeScaleTokens.TitleSmallWeight,
            fontSize = AppTypeScaleTokens.TitleSmallSize,
            letterSpacing = AppTypeScaleTokens.TitleSmallTracking
        )
}