package ca.qc.chatproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ca.qc.chatproject.databinding.ActivityAcceuilBinding
import ca.qc.chatproject.models.UserConnectedResponse
import ca.qc.chatproject.models.UserLoginData
import ca.qc.chatproject.viewModels.UsersViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class AcceuilActivity : AppCompatActivity() {

    private lateinit var binding:ActivityAcceuilBinding
    private lateinit var loginData: UserLoginData
    private lateinit var userViewModel: UsersViewModel
    private lateinit var loginResponse: UserConnectedResponse


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityAcceuilBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.btnConnexion.setOnClickListener{






            loginData.login = binding.etlogin.text.toString()
            loginData.password  =binding.etPassword.toString()

           // userViewModel.userLoginRequest(loginData)

            val intent = Intent(this@AcceuilActivity,MainActivity::class.java)
            startActivity(intent)
        }



    }
}


/*
    val usersRepository = UsersRepository()
            val viewModelProviderFactory = UsersViewModelProviderFactory(usersRepository)

            try {
                val viewModelProvider = ViewModelProvider(this, viewModelProviderFactory)
                userViewModel = viewModelProvider.get(userViewModel::class.java)

            }catch (e: java.lang.IllegalArgumentException){
                e.printStackTrace()
            }
 */