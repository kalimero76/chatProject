package ca.qc.chatproject.models


import com.google.gson.annotations.SerializedName

data class UserLoginData(
    @SerializedName("login")
    var login: String = ""
)