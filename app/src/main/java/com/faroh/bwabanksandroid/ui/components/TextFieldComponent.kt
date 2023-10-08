package com.faroh.bwabanksandroid.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faroh.bwabanksandroid.ui.theme.blackTextStyle
import com.faroh.bwabanksandroid.ui.theme.blueColor
import com.faroh.bwabanksandroid.ui.theme.greyColor
import com.faroh.bwabanksandroid.ui.theme.lightBackgroundColor

@Composable
fun TextFieldComponent(change: (String) -> Unit, secureText: Boolean = false) {
    var textValue by remember {
        mutableStateOf("")
    }

    val localFocusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth()
            .height(50.dp)
            .background(
                color = lightBackgroundColor
            ),
        value = textValue,
        onValueChange = {
            textValue = it
            change(it)
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = blueColor,
            disabledBorderColor = greyColor
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions {
            localFocusManager.clearFocus()
        },
        visualTransformation = if (secureText) PasswordVisualTransformation() else VisualTransformation.None,
        maxLines = 1,
        shape = RoundedCornerShape(10.dp),
        textStyle = blackTextStyle.copy(fontSize = 16.sp),
    )
}