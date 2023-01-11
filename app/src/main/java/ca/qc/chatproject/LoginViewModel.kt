package ca.qc.chatproject

import android.app.Application

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ca.qc.chatproject.models.ConnectResponse
import ca.qc.chatproject.models.GetAllUserResponse
import ca.qc.chatproject.models.UserLoginData
import ca.qc.chatproject.repository.UsersRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel (private val repository: UsersRepository, application: Application) :
    AndroidViewModel(application), Observable {

    val users: MutableLiveData<GetAllUserResponse> = MutableLiveData()  // login Response
    val addUsersMessage: MutableLiveData<String> = MutableLiveData()  // login Response
    val  responseLogin : MutableLiveData<Response<ConnectResponse>> = MutableLiveData()


    fun getUsers()  = viewModelScope.launch {
        try {
            val response = repository.getUsers()
            Log.i("response message ", response.body()?.message.toString())
            if(response.isSuccessful){
                users.postValue(response.body())
                Log.i("response body ",response.body().toString()  )

            }
        }catch (e: java.lang.Exception){
            Log.i(javaClass.simpleName, e.message.toString())
        }

    }


    @Bindable
    val inputUsername = MutableLiveData<String>()

    @Bindable
    val inputPassword = MutableLiveData<String>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigatetoRegister = MutableLiveData<Boolean>()

    val navigatetoRegister: LiveData<Boolean>
        get() = _navigatetoRegister

    private val _navigatetoUserDetails = MutableLiveData<Boolean>()

    val navigatetoUserDetails: LiveData<Boolean>
        get() = _navigatetoUserDetails

    private val _errorToast = MutableLiveData<Boolean>()

    val errotoast: LiveData<Boolean>
        get() = _errorToast

    private val _errorToastUsername = MutableLiveData<Boolean>()

    val errotoastUsername: LiveData<Boolean>
        get() = _errorToastUsername

    private val _errorToastInvalidPassword = MutableLiveData<Boolean>()

    val errorToastInvalidPassword: LiveData<Boolean>
        get() = _errorToastInvalidPassword


    fun signUP() {
        _navigatetoRegister.value = true
    }





    fun loginButton() {
     /*   if (inputUsername.value == null || inputPassword.value == null) {
            _errorToast.value = true
        } else {

            uiScope.launch {
                try {
                    val loginData: UserLoginData = UserLoginData(inputUsername.value.toString(),
                        inputPassword.value.toString())
                    val response = repository.getUser(loginData)

                    if (response.isSuccessful) {
                        val usersData = response.body()?.userData
                        if (usersData != null) {
                            if (usersData.password == inputPassword.value) {
                                inputUsername.value = null
                                inputPassword.value = null
                                _navigatetoUserDetails.value = true
                            } else {
                                _errorToastInvalidPassword.value = true
                            }
                        } else {
                            _errorToastUsername.value = true
                        }
                    }

                } catch (e: java.lang.Exception) {
                    Log.i(javaClass.simpleName, e.message.toString())
                }
            }


        }

*/



    }




    fun doneNavigatingRegiter() {
        _navigatetoRegister.value = false
    }

    fun doneNavigatingUserDetails() {
        _navigatetoUserDetails.value = false
    }


    fun donetoast() {
        _errorToast.value = false
        Log.i("MYTAG", "Done taoasting ")
    }


    fun donetoastErrorUsername() {
        _errorToastUsername .value = false
        Log.i("MYTAG", "Done taoasting ")
    }

    fun donetoastInvalidPassword() {
        _errorToastInvalidPassword .value = false
        Log.i("MYTAG", "Done taoasting ")
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
 
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }


}