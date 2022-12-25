package ca.qc.chatproject.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ca.qc.chatproject.repository.UsersRepository

class UsersViewModelProviderFactory (private val usersRepository: UsersRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(UsersViewModel::class.java)){
            UsersViewModel(usersRepository) as T
        }else{
            throw java.lang.IllegalArgumentException("Unknown ViewModel class")
        }
    }
}