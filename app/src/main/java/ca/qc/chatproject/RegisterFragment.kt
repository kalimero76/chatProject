package ca.qc.chatproject

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import ca.qc.chatproject.databinding.FragmentRegisterBinding
import ca.qc.chatproject.models.UserData
import ca.qc.chatproject.models.UserLoginData
import ca.qc.chatproject.viewModels.UsersViewModel


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val userViewModel: UsersViewModel by navGraphViewModels(R.id.messages_nav_graph)
    private var _binding: FragmentRegisterBinding?=null
    private val binding get() = _binding!!



    private lateinit var loginData: UserLoginData
    private lateinit var userData: UserData


    private var imageUri: Uri? = null

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
        loginData = UserLoginData()
        userData = UserData()

        _binding= FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root
        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
binding.btnCreerCompteRegister.setOnClickListener{

    loginData.login = binding.loginRegister.text.toString()
    loginData.password = binding.motpassRegister.getText().toString();
    userData.nom = binding.nomRegister.text.toString()
    userData.imageUrl ="userImage"

    if (loginData.login.isNotEmpty() && loginData.password.isNotEmpty() && userData.nom.isNotEmpty() ) {
        userViewModel.addUser(userData)
        //userViewModel.

    }

}


    }







        companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegisterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}