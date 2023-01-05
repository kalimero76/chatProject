package ca.qc.chatproject.models

import com.google.gson.annotations.SerializedName
import retrofit2.http.Body

class UserData(

    @SerializedName("idUser")
    val idUser: Int = 0,
    @SerializedName("imageUrl")
    val imageUrl: String = "",
    @SerializedName("login")
    val login: String = "",
    @SerializedName("nom")
    val nom: String = ""
)