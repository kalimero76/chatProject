package ca.qc.chatproject.roomuserdatabase

import androidx.lifecycle.ViewModel


class UserListRoomViewModel : ViewModel() {
    private val userRepository = UserRepository.get()
    val usersListLiveData = userRepository.getUsers()

    fun addUser(user: User) {
        userRepository.addUser(user)
    }
}