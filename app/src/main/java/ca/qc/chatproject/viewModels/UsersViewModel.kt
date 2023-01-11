package ca.qc.chatproject.viewModels

import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.chatproject.models.ConnectResponse
import ca.qc.chatproject.models.UserLoginData
import ca.qc.chatproject.repository.UsersRepository
import ca.qc.chatproject.models.GetAllUserResponse
import ca.qc.chatproject.models.UserData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Response

class UsersViewModel (private val usersRepository: UsersRepository): ViewModel(),Observable {

    val users: MutableLiveData<GetAllUserResponse> = MutableLiveData()  // login Response

    fun getUsers()  = viewModelScope.launch {
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



    @Bindable
    val inputRegisterUsername = MutableLiveData<String>()
    @Bindable
    val inputRegisterPassword = MutableLiveData<String>()
    @Bindable
    val inputRegisterFirstName = MutableLiveData<String>()
    @Bindable
    val inputImageUrl = MutableLiveData<String>()


    @Bindable
    val inputFirstName = MutableLiveData<String>()
    @Bindable
    val inputUsername = MutableLiveData<String>()
    @Bindable
    val inputPassword = MutableLiveData<String>()



    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigatetoRegister = MutableLiveData<Boolean>()

    val navigatetoRegister: LiveData<Boolean>
        get() = _navigatetoRegister

    private val _navigatetoUsersMessages= MutableLiveData<Boolean>()

    val navigatetoUsersMessages: LiveData<Boolean>
        get() = _navigatetoUsersMessages

    private val _errorToast = MutableLiveData<Boolean>()

    val errotoast: LiveData<Boolean>
        get() = _errorToast

    private val _errorToastUsername = MutableLiveData<Boolean>()

    val errotoastUsername: LiveData<Boolean>
        get() = _errorToastUsername

    private val _errorToastInvalidPassword = MutableLiveData<Boolean>()

    val errorToastInvalidPassword: LiveData<Boolean>
        get() = _errorToastInvalidPassword


    private val _errorToastUsernameExists = MutableLiveData<Boolean>()
    val errortoastUsernameExist: LiveData<Boolean>
        get() = _errorToastUsernameExists


    private val _successToastRegister = MutableLiveData<Boolean>()
    val successToastRegister : LiveData<Boolean>
        get() =  _successToastRegister



    fun signUP() {
        _navigatetoRegister.value = true
    }





    fun loginButton() {
        Log.i("Login data ", inputUsername.value.toString())
        Log.i("pass data ", inputPassword.value.toString())
        if (inputUsername.value == "" || inputPassword.value == "") {
            _errorToast.value = true
        } else {

            uiScope.launch {
                try {
                    val loginData = UserLoginData(inputUsername.value.toString())
                    val response = usersRepository.getUser(loginData)
                    Log.i("Response data ", response.toString())
                    if (response.isSuccessful) {
                        val usersData = response.body()?.userData

                        Log.i("Response data login ", usersData?.login.toString())
                        Log.i("Response data password ", usersData?.password.toString())

                        if (usersData?.login== inputUsername.value) {
                            if (usersData?.password == inputPassword.value) {
                                _navigatetoUsersMessages.value = true
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

    }




    fun registerButton() {
        Log.i("MYTAG", "Inside SUBMIT BUTTON")
        if (inputFirstName.value == "" || inputRegisterUsername .value =="" || inputPassword.value =="") {
            _errorToast.value = true
        } else {
            uiScope.launch {
                val loginData = UserLoginData(inputRegisterUsername .value.toString())
                val response = usersRepository.getUser(loginData)
              //  Log.i("apres,,,, ", inputRegisterUsername .value .toString())

                if (response.isSuccessful) {
                    val usersData = response.body()?.userData
                  //  Log.i("usersData?.login.toString() ", usersData?.login.toString())
                    if (usersData?.login.toString() !=""){
                        _errorToastUsernameExists.value=true
                }
                    else {

                        var userData = UserData()
                        userData.login = inputRegisterUsername.value.toString()
                        userData.password = inputRegisterPassword.value.toString()
                        userData.nom = inputRegisterFirstName.value.toString()
                        userData.imageUrl  = inputImageUrl.value.toString()



                      val responseUserAdd = usersRepository.addUser(userData)

                        if(responseUserAdd.isSuccessful)
                        _successToastRegister.value = true

                    }


                    }


                    }

                }
            }




    fun donetoast() {
        _errorToast.value = false
        Log.i("MYTAG", "Done taoasting ")
    }

    fun donetoastInvalidPassword() {
        _errorToastInvalidPassword .value = false
        Log.i("MYTAG", "Done taoasting ")
    }

    fun donetoastErrorUsername() {
        _errorToastUsername .value = false
        Log.i("MYTAG", "Done taoasting ")
    }

    fun  doneAddingUserMessages() {
        _successToastRegister.value = false
        Log.i("MYTAG", "Done taoasting ")
    }


    fun donetoastErrorUsernameExist() {
        _errorToastUsernameExists .value = false
        Log.i("MYTAG", "Done taoasting ")
    }


    fun doneNavigatingRegiter() {
        _navigatetoRegister.value = false
    }

    fun doneNavigatingUserMessages() {
        _navigatetoUsersMessages.value = false

    }



    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }


}




