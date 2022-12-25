package ca.qc.chatproject.models


import com.google.gson.annotations.SerializedName

data class GetAllUserResponse(
    @SerializedName("data")
    val `data`: List<UserData> = listOf(),
    @SerializedName("message")
    val message: String = ""
)