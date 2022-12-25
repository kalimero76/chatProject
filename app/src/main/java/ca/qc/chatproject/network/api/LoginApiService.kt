package ca.qc.chatproject.network.api


import ca.qc.chatproject.models.UserData
import ca.qc.chatproject.models.GetAllUserResponse
import ca.qc.chatproject.models.AddUserResponse
import ca.qc.chatproject.models.UserConnectedResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginApiService {

    @GET("/getAllUsers")
    suspend fun getUsers(): Response<GetAllUserResponse>

    @POST("/addUser")
    suspend fun addUser(
        @Body
        user: UserData
    ): Response<AddUserResponse>

    @GET("/userLoginRequest")
    suspend fun userLoginRequest(): Response<UserConnectedResponse>

}