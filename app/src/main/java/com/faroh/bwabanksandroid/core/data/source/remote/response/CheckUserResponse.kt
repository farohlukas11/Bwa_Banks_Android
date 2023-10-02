package com.faroh.bwabanksandroid.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CheckUserResponse(

	@field:SerializedName("is_email_exist")
	val isEmailExist: Boolean? = null
)
