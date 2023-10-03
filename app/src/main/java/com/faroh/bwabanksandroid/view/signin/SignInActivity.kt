package com.faroh.bwabanksandroid.view.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faroh.bwabanksandroid.R
import com.faroh.bwabanksandroid.ui.theme.BwaBanksAndroidTheme
import com.faroh.bwabanksandroid.ui.theme.blackTextStyle
import com.faroh.bwabanksandroid.ui.theme.blueColor
import com.faroh.bwabanksandroid.ui.theme.lightBackgroundColor
import com.faroh.bwabanksandroid.ui.theme.medium
import com.faroh.bwabanksandroid.ui.theme.semiBold
import com.faroh.bwabanksandroid.ui.theme.whiteColor

@Composable
fun SignInActivity() {
    Surface(modifier = Modifier.fillMaxSize()) {
        SignInContent()
    }
}

@Composable
fun SignInContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_logo_light),
            contentDescription = "",
            modifier = Modifier
                .padding(top = 100.dp)
                .width(155.dp)
        )
        Text(
            text = "Sign In &\n" +
                    "Grow Your Finance",
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 100.dp),
            style = blackTextStyle,
            fontWeight = semiBold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(30.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(whiteColor),
        ) {
            Column(modifier = Modifier.padding(22.dp)) {
                Text(
                    text = "Email Address",
                    style = blackTextStyle.copy(fontWeight = medium),
                    textAlign = TextAlign.Start
                )
                OutlinedTextField(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(
                            color = lightBackgroundColor
                        )
                        .border(
                            width = 1.dp,
                            color = blueColor,
                            shape = RoundedCornerShape(10.dp)
                        ),
                    maxLines = 1,
                    shape = RoundedCornerShape(10.dp),
                    value = "",
                    onValueChange = {})

            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_2)
@Composable
fun SignInContentPreview() {
    BwaBanksAndroidTheme {
        Surface(color = lightBackgroundColor) {
            SignInContent()
        }
    }
}