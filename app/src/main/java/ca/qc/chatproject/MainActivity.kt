package ca.qc.chatproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ca.qc.chatproject.adapters.UsersAdapter
import ca.qc.chatproject.databinding.ActivityMainBinding
import ca.qc.chatproject.models.UserData
import ca.qc.chatproject.models.UserLoginData
import ca.qc.chatproject.repository.UsersRepository
import ca.qc.chatproject.viewModels.UsersViewModel
import ca.qc.chatproject.viewModels.UsersViewModelProviderFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var usersViewModel: UsersViewModel


    private val navController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       // val navBar: BottomNavigationView = findViewById(R.id.bottomNavView)
      //  navBar.visibility = View.GONE

        binding.bottomNavView.setupWithNavController(navController)

        /* val adapter = UsersAdapter()
         binding.fragmentContainerView.usrecyclerviewUsersListFragment.adapter= adapter*/

        val usersRepository = UsersRepository()
        val viewModelProviderFactory = UsersViewModelProviderFactory(usersRepository)

        try {
            //View model initiation
            val viewModelProvider = ViewModelProvider(
                navController.getViewModelStoreOwner(R.id.messages_nav_graph),
                viewModelProviderFactory)
            usersViewModel = viewModelProvider.get(UsersViewModel::class.java)

        } catch (e: java.lang.IllegalArgumentException) {
            e.printStackTrace()
        }
    }
}

