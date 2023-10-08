package com.faroh.bwabanksandroid.view.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faroh.bwabanksandroid.ui.components.ButtonComponent
import com.faroh.bwabanksandroid.ui.theme.BwaBanksAndroidTheme
import com.faroh.bwabanksandroid.ui.theme.blackTextStyle
import com.faroh.bwabanksandroid.ui.theme.greyTextStyle
import com.faroh.bwabanksandroid.ui.theme.lightBackgroundColor
import com.faroh.bwabanksandroid.ui.theme.semiBold

@Composable
fun SuccessSignUpActivity(
    toHome: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize(), color = lightBackgroundColor) {
        SuccessSignUpContent {
            toHome.invoke()
        }
    }
}

@Composable
fun SuccessSignUpContent(
    toHome: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 94.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Akun Berhasil\nTerdaftar",
            style = blackTextStyle.copy(fontSize = 20.sp, fontWeight = semiBold),
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Grow your finance start together with us",
            style = greyTextStyle.copy(fontSize = 16.sp),
            lineHeight = 30.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(50.dp))
        ButtonComponent(text = "Get Started") {
            toHome.invoke()
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_2)
@Composable
fun SuccessSignUpContentPreview() {
    BwaBanksAndroidTheme {
        Surface(color = lightBackgroundColor) {
            SuccessSignUpContent {

            }
        }
    }
}