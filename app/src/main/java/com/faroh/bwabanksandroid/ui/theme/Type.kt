package com.faroh.bwabanksandroid.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.faroh.bwabanksandroid.R


// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

private val fontProvider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

private val fontName = GoogleFont("Poppins")
private val fontFamily = FontFamily(
    Font(googleFont = fontName, fontProvider = fontProvider)
)

val blackTextStyle = TextStyle(fontFamily = fontFamily, color = blackColor)
val whiteTextStyle = TextStyle(fontFamily = fontFamily, color = whiteColor)
val greyTextStyle = TextStyle(fontFamily = fontFamily, color = greyColor)
val blueTextStyle = TextStyle(fontFamily = fontFamily, color = blueColor)
val greenTextStyle = TextStyle(fontFamily = fontFamily, color = greenColor)

var light: FontWeight = FontWeight.W300
var regular: FontWeight = FontWeight.W400
var medium: FontWeight = FontWeight.W500
var semiBold: FontWeight = FontWeight.W600
var bold: FontWeight = FontWeight.W700
var extraBold: FontWeight = FontWeight.W800
var black: FontWeight = FontWeight.W900


