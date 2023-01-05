package ca.qc.chatproject.models

import com.google.gson.annotations.SerializedName

data class UserConnectedResponse(
    @SerializedName("statut")
    var statut: String,
    @SerializedName("message")
    var message: String,
    @SerializedName("userData")
    var  userData:  UserData = UserData()
)
