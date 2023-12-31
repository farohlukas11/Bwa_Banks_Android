package com.faroh.bwabanksandroid.view.signin

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faroh.bwabanksandroid.R
import com.faroh.bwabanksandroid.core.data.Resource
import com.faroh.bwabanksandroid.core.domain.model.LoginBody
import com.faroh.bwabanksandroid.ui.components.ButtonComponent
import com.faroh.bwabanksandroid.ui.components.ProgressBarComponent
import com.faroh.bwabanksandroid.ui.components.TextButtonComponent
import com.faroh.bwabanksandroid.ui.components.TextFieldComponent
import com.faroh.bwabanksandroid.ui.theme.BwaBanksAndroidTheme
import com.faroh.bwabanksandroid.ui.theme.blackTextStyle
import com.faroh.bwabanksandroid.ui.theme.blueTextStyle
import com.faroh.bwabanksandroid.ui.theme.lightBackgroundColor
import com.faroh.bwabanksandroid.ui.theme.medium
import com.faroh.bwabanksandroid.ui.theme.semiBold
import com.faroh.bwabanksandroid.ui.theme.whiteColor


@Composable
fun SignInActivity(
    signInViewModel: SignInViewModel,
    toSignUp: () -> Unit,
    toHome: () -> Unit
) {
    val signInVm =
        signInViewModel.signInState.collectAsState()
    val signInToast = signInViewModel.toastMessage
    val context = LocalContext.current

    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var showProgressBarr by remember {
        mutableStateOf(false)
    }

    Scaffold(containerColor = lightBackgroundColor) { innerPadding ->
        SignInContent(
            innerPadding = innerPadding,
            usernameChange = {
                username = it
            }, passwordChange = {
                password = it
            }, onSignIn = {
                if (username.isNotEmpty() && password.isNotEmpty()) signInViewModel.loginUser(
                    LoginBody(email = username, password = password)
                )
                else signInViewModel.setMessage("Field tidak boleh Kosong!")
            }, toSignUp = {
                toSignUp.invoke()
            })

        if (showProgressBarr) ProgressBarComponent()

        LaunchedEffect(signInVm.value) {
            signInVm.value?.let {
                when (it) {
                    is Resource.Loading -> showProgressBarr = true
                    is Resource.Success -> {
                        showProgressBarr = false
                        toHome.invoke()
                    }

                    is Resource.Error -> {
                        showProgressBarr = false
                        signInViewModel.setMessage(it.message!!)
                    }
                }
            }
        }

        LaunchedEffect(Unit) {
            signInToast.collect {
                Toast.makeText(
                    context,
                    it,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}

@Composable
fun SignInContent(
    innerPadding: PaddingValues,
    usernameChange: (String) -> Unit,
    passwordChange: (String) -> Unit,
    onSignIn: () -> Unit,
    toSignUp: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(
                vertical = innerPadding.calculateTopPadding(),
                horizontal = 24.dp
            )
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
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
            SignInContent(
                passwordChange = {},
                usernameChange = {},
                toSignUp = {},
                onSignIn = {},
                innerPadding = PaddingValues()
            )
        }
    }
}