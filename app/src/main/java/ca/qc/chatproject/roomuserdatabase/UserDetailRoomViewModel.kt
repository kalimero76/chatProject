package ca.qc.chatproject.roomuserdatabase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class UserDetailRoomViewModel () : ViewModel() {
    private val repository: UserRepository = UserRepository.get()
  //  private val crimeIdLiveData = MutableLiveData<UUID>()
  private val userLoginLiveData = MutableLiveData<String>()

    var userLiveData: LiveData<User> =
        Transformations.switchMap(userLoginLiveData){
            repository.getUser(it)
        }
/*
    fun loadUser(userId: UUID) {
        crimeIdLiveData.value = userId
    }*/

    fun loadUser(login: String) {
        userLoginLiveData.value = login
    }

    fun saveUser(user: User) {
        repository.updateUser(user)
    }
}