package com.faroh.bwabanksandroid.core.domain.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class UserModel(
	val cardNumber: String? = null,
	val ktp: String? = null,
	val createdAt: String? = null,
	val profilePicture: String? = null,
	val tokenType: String? = null,
	val token: String? = null,
	val updatedAt: String? = null,
	val balance: Int? = null,
	val pin: String? = null,
	val name: String? = null,
	val id: Int? = null,
	val email: String? = null,
	val username: String? = null
) : Parcelable
