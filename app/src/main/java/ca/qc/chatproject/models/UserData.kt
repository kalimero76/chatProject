package ca.qc.chatproject.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import retrofit2.http.Body
@Entity
class UserData(
    @SerializedName("login")
    var login: String = "",
    @SerializedName("password")
    var password: String = "",
    @SerializedName("idUser")
    var idUser: Int = 0,
    @SerializedName("imageUrl")
    var imageUrl: String = "",
    @SerializedName("nom")
   var nom: String = ""
)