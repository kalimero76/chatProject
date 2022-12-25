package ca.qc.chatproject.models

import com.google.gson.annotations.SerializedName
import retrofit2.http.Body

class UserData(

    @SerializedName("login")
    val login: String = "",
    @SerializedName("password")
    val password: String = ""
)