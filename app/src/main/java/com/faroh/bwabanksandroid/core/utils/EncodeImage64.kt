package com.faroh.bwabanksandroid.core.utils

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream

object EncodeImage64 {
    fun getEncoded64ImageStringFromBitmap(bitmap: Bitmap): String {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream)
        val byteFormat = stream.toByteArray()

        return "data:image/png;base64,${Base64.encodeToString(byteFormat, Base64.NO_WRAP)}"
    }
}