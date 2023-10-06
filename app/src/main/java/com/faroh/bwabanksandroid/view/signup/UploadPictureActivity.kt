package com.faroh.bwabanksandroid.view.signup

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faroh.bwabanksandroid.R
import com.faroh.bwabanksandroid.ui.components.ButtonComponent
import com.faroh.bwabanksandroid.ui.components.TextFieldComponent
import com.faroh.bwabanksandroid.ui.theme.BwaBanksAndroidTheme
import com.faroh.bwabanksandroid.ui.theme.blackTextStyle
import com.faroh.bwabanksandroid.ui.theme.lightBackgroundColor
import com.faroh.bwabanksandroid.ui.theme.medium
import com.faroh.bwabanksandroid.ui.theme.semiBold
import com.faroh.bwabanksandroid.ui.theme.whiteColor

@Composable
fun UploadPictureActivity(
    signUpViewModel: SignUpViewModel,
    toUploadKtp: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize(), color = lightBackgroundColor) {
        UploadPictureContent(
            imagePathChange = {
                signUpViewModel.onEvent(SignUpEvent.PictureChanged(it))
            },
            pinChange = {
                signUpViewModel.onEvent(SignUpEvent.PinChanged(it))
            },
            toUploadKtp = toUploadKtp,
        )
    }
}

@Composable
fun UploadPictureContent(
    imagePathChange: (String?) -> Unit,
    pinChange: (Int) -> Unit,
    toUploadKtp: () -> Unit
) {
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val context = LocalContext.current
    val bitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }

    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

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
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier
                    .background(color = Color.Transparent)
                    .clickable {
                        launcher.launch("image/*")
                    }) {
                    imagePathChange(imageUri?.path)

                    if (imageUri != null) imageUri?.let {
                        if (Build.VERSION.SDK_INT < 28) {
                            bitmap.value = MediaStore.Images
                                .Media.getBitmap(context.contentResolver, it)

                        } else {
                            val source = ImageDecoder
                                .createSource(context.contentResolver, it)
                            bitmap.value = ImageDecoder.decodeBitmap(source)
                        }

                        bitmap.value?.let { btm ->
                            Image(
                                bitmap = btm.asImageBitmap(),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(120.dp)
                                    .clip(CircleShape)
                            )
                        }
                    } else Image(
                        painterResource(id = R.drawable.img_profile),
                        contentDescription = null,
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Shayna Hanna",
                    style = blackTextStyle.copy(fontSize = 18.sp, fontWeight = medium)
                )
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = "Set PIN (6 digit number)",
                    style = blackTextStyle.copy(fontWeight = medium),
                    modifier = Modifier.align(Alignment.Start),
                )
                TextFieldComponent(change = {
                    pinChange(it.toInt())
                })
                Spacer(modifier = Modifier.height(30.dp))
                ButtonComponent(text = "Continue") {
                    toUploadKtp.invoke()
                }
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_2)
@Composable
fun UploadPictureContentPrev() {
    BwaBanksAndroidTheme {
        Surface(color = lightBackgroundColor) {
            UploadPictureContent(imagePathChange = {}, pinChange = {}) {

            }
        }
    }
}