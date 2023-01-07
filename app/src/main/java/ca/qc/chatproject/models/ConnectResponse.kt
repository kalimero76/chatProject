package ca.qc.chatproject.models


import com.google.gson.annotations.SerializedName

data class ConnectResponse(
    @SerializedName("data")
    val `userLoginData`: UserLoginData = UserLoginData(),
    @SerializedName("message")
    val message: String = ""
)