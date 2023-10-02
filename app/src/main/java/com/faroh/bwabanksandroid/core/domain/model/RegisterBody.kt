package com.faroh.bwabanksandroid.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegisterBody(
    val name: String,
    val email: String,
    val password: String,
    val pin: Int,
    val profilePicture: String? = null,
    val ktp: String? = null,
) : Parcelable
