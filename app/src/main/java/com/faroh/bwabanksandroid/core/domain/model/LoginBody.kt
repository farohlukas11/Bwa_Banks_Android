package com.faroh.bwabanksandroid.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginBody(
    val email: String,
    val password: String
) : Parcelable