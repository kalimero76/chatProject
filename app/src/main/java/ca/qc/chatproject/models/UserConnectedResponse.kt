package ca.qc.chatproject.models

import com.google.gson.annotations.SerializedName

data class UserConnectedResponse(
    @SerializedName("statut")
    val statut: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("userLoginData")
    val  userLoginData:  UserLoginData
)
