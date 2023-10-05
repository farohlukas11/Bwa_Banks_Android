package com.faroh.bwabanksandroid.view.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faroh.bwabanksandroid.R
import com.faroh.bwabanksandroid.ui.components.ButtonComponent
import com.faroh.bwabanksandroid.ui.components.TextButtonComponent
import com.faroh.bwabanksandroid.ui.components.TextFieldComponent
import com.faroh.bwabanksandroid.ui.theme.BwaBanksAndroidTheme
import com.faroh.bwabanksandroid.ui.theme.blackTextStyle
import com.faroh.bwabanksandroid.ui.theme.lightBackgroundColor
import com.faroh.bwabanksandroid.ui.theme.medium
import com.faroh.bwabanksandroid.ui.theme.semiBold
import com.faroh.bwabanksandroid.ui.theme.whiteColor
import com.google.android.material.progressindicator.CircularProgressIndicator

@Composable
fun SignUpActivity(
    signUpViewModel: SignUpViewModel,
    toSignIn: () -> Unit,
    toUploadPic: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize(), color = lightBackgroundColor) {
        signUpViewModel.signUpState.collectAsState().value.let {
            when (it) {
                is SignUpState.SignUpSuccess -> toUploadPic.invoke()
                is SignUpState.SignUpEmpty -> {}
                is SignUpState.SignUpError -> {}
                is SignUpState.SignUpLoading -> CircularProgressIndicator(LocalContext.current)
                is SignUpState.FieldHasNull -> {}
                else -> {}
            }
        }

        SignUpContent(
            fullnameChange = {
                signUpViewModel.onEvent(SignUpEvent.FullNameChanged(it))
            }, emailChange = {
                signUpViewModel.onEvent(SignUpEvent.EmailChanged(it))
            }, passwordChange = {
                signUpViewModel.onEvent(SignUpEvent.PasswordChanged(it))
            }, onCheckEmail = {
                signUpViewModel.onEvent(SignUpEvent.OnSignUpCheckEmailEvent)
            }, toSignIn = {
                toSignIn.invoke()
            }
        )
    }
}

@Composable
fun SignUpContent(
    fullnameChange: (String) -> Unit,
    emailChange: (String) -> Unit,
    passwordChange: (String) -> Unit,
    onCheckEmail: () -> Unit,
    toSignIn: () -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
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
            text = "Join Us to Unlock\n" +
                    "Your Growth",
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
            Column(
                modifier = Modifier
                    .padding(22.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Full Name",
                    style = blackTextStyle.copy(fontWeight = medium),
                )
                TextFieldComponent(change = {
                    fullnameChange(it)
                })
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Email Address",
                    style = blackTextStyle.copy(fontWeight = medium),
                )
                TextFieldComponent(change = {
                    emailChange(it)
                })
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Password",
                    style = blackTextStyle.copy(fontWeight = medium),
                )
                TextFieldComponent(
                    change = {
                        passwordChange(it)
                    },
                    secureText = true,
                )
                Spacer(modifier = Modifier.height(30.dp))
                ButtonComponent(text = "Continue") {
                    onCheckEmail.invoke()
                }
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        TextButtonComponent(text = "Sign In") {
            toSignIn.invoke()
        }
        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_2)
@Composable
fun SignUpActivityPreview() {
    BwaBanksAndroidTheme {
        Surface(color = lightBackgroundColor) {
            SignUpContent(
                fullnameChange = {

                }, emailChange = {

                }, passwordChange = {

                }, onCheckEmail = {

                }, toSignIn = {

                }
            )
        }
    }
}