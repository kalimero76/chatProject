package ca.qc.chatproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.navGraphViewModels
import ca.qc.chatproject.databinding.FragmentAcceuilBinding
import ca.qc.chatproject.databinding.FragmentUsersListBinding
import ca.qc.chatproject.models.ConnectResponse
import ca.qc.chatproject.models.UserConnectedResponse
import ca.qc.chatproject.models.UserData
import ca.qc.chatproject.models.UserLoginData
import ca.qc.chatproject.viewModels.UsersViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AcceuilFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AcceuilFragment : Fragment() {





    private lateinit var loginData: UserLoginData
    private lateinit var userData: UserData


    private val userViewModel: UsersViewModel by navGraphViewModels(R.id.messages_nav_graph)
    private var _binding: FragmentAcceuilBinding?=null
    private val binding get() = _binding!!

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding= FragmentAcceuilBinding.inflate(inflater, container, false)
        val view = binding.root
        loginData = UserLoginData()
         userData = UserData()
        // Inflate the layout for this fragment
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.etlogin.addTextChangedListener{
            binding.errorConnexiontxt.text=""
        }

        binding.etPassword.addTextChangedListener{
            binding.errorConnexiontxt.text=""
        }

        binding.btnConnexion.setOnClickListener {
            loginData.login = binding.etlogin.text.toString()
            loginData.password = binding.etPassword.getText().toString();

            if (loginData.login.isNotEmpty()) {

                Log.i("Login data ",loginData.login)
                Log.i("Login password ",loginData.password)

                userViewModel.userLoginRequest(loginData)

                userViewModel.responseLogin.observe(viewLifecycleOwner, Observer {
                   if(it.isSuccessful)
                       it.body()?.let {
                               it1 ->
                           loginData=it1.userLoginData
                           if(loginData.login!="")
                           {
                               Log.i("Login data ",userData.nom)
                       // R.id.action_acceuilFragment_to_messagesListFragment;;
                               Navigation.findNavController(view).navigate(R.id.action_acceuilFragment_to_messagesListFragment);
                           }
                           else
                           {
                               binding.errorConnexiontxt.text="Erreur d'identification"
                           }
                    }
                   })
            }
        }

        binding.btnNcompte.setOnClickListener{

            Navigation.findNavController(view).navigate(R.id.action_acceuilFragment_to_registerFragment);

        }



    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AcceuilFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AcceuilFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}