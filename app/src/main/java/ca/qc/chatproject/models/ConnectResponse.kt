package ca.qc.chatproject.models


import com.google.gson.annotations.SerializedName

data class ConnectResponse(
    @SerializedName("data")
    val `UserData`: UserData = UserData(),
    @SerializedName("message")
    val message: String = ""
)