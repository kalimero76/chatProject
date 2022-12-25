package ca.qc.chatproject.models


import com.google.gson.annotations.SerializedName

data class UserLoginData(
    @SerializedName("login")
    val login: String = "",
    @SerializedName("password")
    val password: String = ""
)