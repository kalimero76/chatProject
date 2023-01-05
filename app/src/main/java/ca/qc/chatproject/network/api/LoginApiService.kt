package ca.qc.chatproject.network.api


import ca.qc.chatproject.models.*
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginApiService {

    @GET("/getAllUsers")
    suspend fun getUsers(): Response<GetAllUserResponse>

    @POST("/addUser")
    suspend fun addUser(
        @Body
        user: UserData,
    ): Response<AddUserResponse>

    @POST("/userLoginRequest")
    suspend fun userLoginRequest(
        @Body
        userLoginData: UserLoginData,

        ): Response<ConnectResponse>

}