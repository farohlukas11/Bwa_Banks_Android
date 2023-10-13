package com.faroh.bwabanksandroid.view.signup

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
import com.faroh.bwabanksandroid.core.domain.model.RegisterBody
import com.faroh.bwabanksandroid.ui.components.ButtonComponent
import com.faroh.bwabanksandroid.ui.components.ProgressBarComponent
import com.faroh.bwabanksandroid.ui.components.TextButtonComponent
import com.faroh.bwabanksandroid.ui.components.TextFieldComponent
import com.faroh.bwabanksandroid.ui.theme.BwaBanksAndroidTheme
import com.faroh.bwabanksandroid.ui.theme.blackTextStyle
import com.faroh.bwabanksandroid.ui.theme.lightBackgroundColor
import com.faroh.bwabanksandroid.ui.theme.medium
import com.faroh.bwabanksandroid.ui.theme.semiBold
import com.faroh.bwabanksandroid.ui.theme.whiteColor

@Composable
fun SignUpActivity(
    signUpViewModel: SignUpViewModel,
    toSignIn: () -> Unit,
    toUploadPic: (registerBody: RegisterBody) -> Unit
) {
    val signUpVm = signUpViewModel.signUpState.collectAsState()
    val signUpToast = signUpViewModel.toastMessage
    val context = LocalContext.current

    var fullName by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var showProgressBarr by remember {
        mutableStateOf(false)
    }

    Scaffold(containerColor = lightBackgroundColor) { paddingValues ->
        SignUpContent(
            paddingValues = paddingValues,
            fullnameChange = {
                fullName = it
            }, emailChange = {
                email = it
            }, passwordChange = {
                password = it
            }, onCheckEmail = {
                if (fullName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) signUpViewModel.checkUser(
                    email
                ) else signUpViewModel.setMessage("Field tidak boleh Kosong!")
            }, toSignIn = {
                toSignIn.invoke()
            }
        )

        if (showProgressBarr) ProgressBarComponent()

        LaunchedEffect(signUpVm.value) {
            signUpVm.value?.let {
                when (it) {
                    is Resource.Loading -> showProgressBarr = true
                    is Resource.Success -> {
                        showProgressBarr = false
                        if (!it.data!!) toUploadPic(
                            RegisterBody(
                                name = fullName,
                                email = email,
                                password = password,
                                pin = 0
                            )
                        )
                        else signUpViewModel.setMessage("Email Sudah digunakan!")
                    }

                    is Resource.Error -> {
                        showProgressBarr = false
                        signUpViewModel.setMessage(it.message!!)
                    }
                }
            }
        }

        LaunchedEffect(Unit) {
            signUpToast.collect {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }
    }
}

@Composable
fun SignUpContent(
    paddingValues: PaddingValues,
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
            .padding(
                vertical = paddingValues.calculateTopPadding(),
                horizontal = 24.dp
            ),
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
                paddingValues = PaddingValues(),
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