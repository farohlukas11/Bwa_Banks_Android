package com.faroh.bwabanksandroid.core.utils

import com.faroh.bwabanksandroid.core.data.source.remote.response.UserResponse
import com.faroh.bwabanksandroid.core.domain.model.UserModel

object DataMapper {

    //get from api
    fun mapUserResponseToModel(userResponse: UserResponse) = UserModel(
        cardNumber = userResponse.cardNumber,
        ktp = userResponse.ktp,
        createdAt = userResponse.createdAt,
        profilePicture = userResponse.profilePicture,
        tokenType = userResponse.tokenType,
        token = userResponse.token,
        updatedAt = userResponse.updatedAt,
        balance = userResponse.balance,
        pin = userResponse.pin,
        name = userResponse.name,
        id = userResponse.id,
        email = userResponse.email,
        username = userResponse.username
    )
}