package com.faroh.bwabanksandroid.view.signup

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.faroh.bwabanksandroid.ui.theme.BwaBanksAndroidTheme
import com.faroh.bwabanksandroid.ui.theme.lightBackgroundColor

@Composable
fun SignUpActivity() {
    Surface(modifier = Modifier.fillMaxSize(), color = lightBackgroundColor) {
        SignUpContent()
    }
}

@Composable
fun SignUpContent() {

}

@Preview(showBackground = true, device = Devices.PIXEL_2)
@Composable
fun SignUpActivityPreview() {
    BwaBanksAndroidTheme {
        Surface(color = lightBackgroundColor) {
            SignUpContent()
        }
    }
}