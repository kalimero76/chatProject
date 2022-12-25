package ca.qc.chatproject.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.chatproject.models.UserLoginData
import ca.qc.chatproject.repository.UsersRepository
import ca.qc.chatproject.models.GetAllUserResponse
import ca.qc.chatproject.models.UserData
import kotlinx.coroutines.launch

class UsersViewModel (private val usersRepository: UsersRepository): ViewModel() {
    val users: MutableLiveData<GetAllUserResponse> = MutableLiveData()  // login Response
    val addUsersMessage: MutableLiveData<String> = MutableLiveData()  // login Response


    init {
        getUsers()
    }

    fun getUsers() = viewModelScope.launch {
        try {
            val response = usersRepository.getUsers()
            Log.i("response message ", response.body()?.message.toString())
            if(response.isSuccessful){
                users.postValue(response.body())
                Log.i("response body ",response.body().toString()  )
            }
        }catch (e: java.lang.Exception){
            Log.i(javaClass.simpleName, e.message.toString())
        }

    }

    fun addUser(user: UserData)= viewModelScope.launch {
        try {
            val response = usersRepository.addUser(user)
            Log.i("response befor chek added ", response.body()?.message.toString() )
            if(response.isSuccessful){
            //    users.postValue(response.message)
                Log.i("response added ",response.body().toString()  )
            }
        }catch (e: java.lang.Exception){
            Log.i(javaClass.simpleName, e.message.toString())
        }
    }
    }




