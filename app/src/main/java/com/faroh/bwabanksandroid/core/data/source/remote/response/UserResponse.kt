package com.faroh.bwabanksandroid.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("card_number")
	val cardNumber: String? = null,

	@field:SerializedName("ktp")
	val ktp: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("email_verified_at")
	val emailVerifiedAt: Any? = null,

	@field:SerializedName("profile_picture")
	val profilePicture: String? = null,

	@field:SerializedName("token_type")
	val tokenType: String? = null,

	@field:SerializedName("token")
	val token: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("balance")
	val balance: Int? = null,

	@field:SerializedName("pin")
	val pin: String? = null,

	@field:SerializedName("token_expires_in")
	val tokenExpiresIn: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
