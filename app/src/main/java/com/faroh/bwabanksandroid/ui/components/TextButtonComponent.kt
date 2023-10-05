package com.faroh.bwabanksandroid.ui.components

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.faroh.bwabanksandroid.ui.theme.greyTextStyle

@Composable
fun TextButtonComponent(text: String, onClick: () -> Unit) {
    TextButton(onClick = { onClick.invoke() }) {
        Text(text = text, style = greyTextStyle.copy(fontSize = 16.sp))
    }
}