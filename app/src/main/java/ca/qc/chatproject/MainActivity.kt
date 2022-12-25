package ca.qc.chatproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ca.qc.chatproject.adapters.UsersAdapter
import ca.qc.chatproject.databinding.ActivityMainBinding
import ca.qc.chatproject.models.UserData
import ca.qc.chatproject.models.UserLoginData
import ca.qc.chatproject.repository.UsersRepository
import ca.qc.chatproject.viewModels.UsersViewModel
import ca.qc.chatproject.viewModels.UsersViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var usersViewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        val adapter = UsersAdapter()
        binding.iduserRv.adapter= adapter

        val usersRepository = UsersRepository()
        val viewModelProviderFactory = UsersViewModelProviderFactory(usersRepository)


        try {
        //View model initiation
            val viewModelProvider = ViewModelProvider(this, viewModelProviderFactory)
            usersViewModel = viewModelProvider.get(UsersViewModel::class.java)



            usersViewModel.users.observe(this, Observer {
                Log.i("main Activity",it.data.joinToString { "\n ${it.login}   ${it.password}" }  )
                adapter.setUsers(it.data)
            })

            usersViewModel.addUser(UserData("Karimano","76543"))
            usersViewModel.addUsersMessage.observe(this, Observer {


                Log.i("Add user message",it.toString()   )

            })


        }catch (e: java.lang.IllegalArgumentException){
            e.printStackTrace()
        }

        setContentView(binding.root )
    }
}