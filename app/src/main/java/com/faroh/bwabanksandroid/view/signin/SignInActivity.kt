package com.faroh.bwabanksandroid.view.signin

import android.widget.Toast
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.faroh.bwabanksandroid.R
import com.faroh.bwabanksandroid.core.data.Resource
import com.faroh.bwabanksandroid.core.domain.model.UserModel
import com.faroh.bwabanksandroid.ui.components.ButtonComponent
import com.faroh.bwabanksandroid.ui.components.TextButtonComponent
import com.faroh.bwabanksandroid.ui.components.TextFieldComponent
import com.faroh.bwabanksandroid.ui.theme.BwaBanksAndroidTheme
import com.faroh.bwabanksandroid.ui.theme.blackTextStyle
import com.faroh.bwabanksandroid.ui.theme.blueTextStyle
import com.faroh.bwabanksandroid.ui.theme.lightBackgroundColor
import com.faroh.bwabanksandroid.ui.theme.medium
import com.faroh.bwabanksandroid.ui.theme.semiBold
import com.faroh.bwabanksandroid.ui.theme.whiteColor
import com.google.android.material.progressindicator.CircularProgressIndicator

@Composable
fun SignInActivity(
    signInViewModel: SignInViewModel,
    toSignUp: () -> Unit,
    toHome: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize(), color = lightBackgroundColor) {
        signInViewModel.signInState.collectAsState().value.let {
            when (it) {
                is SignInState.LoginSuccess -> toHome.invoke()
                is SignInState.LoginEmpty -> {}
                is SignInState.LoginError -> {
                    Toast.makeText(LocalContext.current, "Error", Toast.LENGTH_LONG).show()
                }

                is SignInState.LoginLoading -> CircularProgressIndicator(LocalContext.current)
                is SignInState.FieldHasNull -> {
                    Toast.makeText(LocalContext.current, "Null", Toast.LENGTH_LONG).show()
                }

                else -> {}
            }
        }
        SignInContent(usernameChange = {
            signInViewModel.onEvent(SignInEvent.EmailChanged(it))
        }, passwordChange = {
            signInViewModel.onEvent(SignInEvent.PasswordChanged(it))
        }, onSignIn = {
            signInViewModel.onEvent(SignInEvent.OnSignInEvent)
        }, toSignUp = {
            toSignUp.invoke()
        })
    }
}

@Composable
fun SignInContent(
    usernameChange: (String) -> Unit,
    passwordChange: (String) -> Unit,
    onSignIn: () -> Unit,
    toSignUp: () -> Unit
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
            Column(
                modifier = Modifier
                    .padding(22.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Email Address",
                    style = blackTextStyle.copy(fontWeight = medium),
                )
                TextFieldComponent(change = {
                    usernameChange(it)
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
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Forgot Password",
                    style = blueTextStyle,
                    modifier = Modifier.align(Alignment.End)
                )
                Spacer(modifier = Modifier.height(30.dp))
                ButtonComponent(text = "Sign In") {
                    onSignIn()
                }
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        TextButtonComponent(text = "Create New Account") {
            toSignUp.invoke()
        }
        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_2)
@Composable
fun SignInActivityPreview() {
    BwaBanksAndroidTheme {
        Surface(color = lightBackgroundColor) {
            SignInContent(passwordChange = {}, usernameChange = {}, toSignUp = {}, onSignIn = {})
        }
    }
}