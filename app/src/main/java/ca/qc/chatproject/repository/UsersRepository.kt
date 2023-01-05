package ca.qc.chatproject.repository

import ca.qc.chatproject.models.UserData
import ca.qc.chatproject.models.UserLoginData
import ca.qc.chatproject.network.RetrofitInstance

class UsersRepository {
    suspend fun getUsers() = RetrofitInstance.retrofitService.getUsers()
    suspend fun addUser(user: UserData)= RetrofitInstance.retrofitService.addUser(user)
    suspend fun userLoginRequest(user: UserLoginData)= RetrofitInstance.retrofitService.userLoginRequest(user)
}



