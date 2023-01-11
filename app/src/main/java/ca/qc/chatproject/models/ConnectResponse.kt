package ca.qc.chatproject.models


import com.google.gson.annotations.SerializedName

data class ConnectResponse(
    @SerializedName("userData")
    val `userData`: UserData = UserData(),
    @SerializedName("message")
    val message: String = ""
)