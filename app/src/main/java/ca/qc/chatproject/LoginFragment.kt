package ca.qc.chatproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navGraphViewModels
import ca.qc.chatproject.databinding.FragmentLoginBinding
import ca.qc.chatproject.models.ConnectResponse
import ca.qc.chatproject.models.UserData
import ca.qc.chatproject.models.UserLoginData
import ca.qc.chatproject.viewModels.UsersViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AcceuilFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {


    private val userViewModel: UsersViewModel by navGraphViewModels(R.id.messages_nav_graph)
    private var _binding: FragmentLoginBinding?=null
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
        _binding= FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
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
            userViewModel.inputUsername.value = binding.etlogin.text.toString()
            userViewModel.inputPassword.value =binding.etPassword.getText().toString();

           userViewModel.loginButton()

            userViewModel.errotoast.observe(viewLifecycleOwner, Observer { hasError->
               if(hasError==true){
                    Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
                   userViewModel.donetoast()
                }
            })

            userViewModel.errotoastUsername .observe(viewLifecycleOwner, Observer { hasError->
                if(hasError==true){
                    Toast.makeText(requireContext(), "User doesnt exist,please Register!", Toast.LENGTH_SHORT).show()
                    userViewModel.donetoastErrorUsername()
                }
            })


            userViewModel.errorToastInvalidPassword .observe(viewLifecycleOwner, Observer { hasError->
                if(hasError==true){
                    Toast.makeText(requireContext(), "Please check your Password !", Toast.LENGTH_SHORT).show()
                    userViewModel.donetoastInvalidPassword()
                }
            })

          userViewModel.navigatetoUsersMessages.observe(viewLifecycleOwner, Observer { hasFinished->
                if (hasFinished == true){
                    Log.i("MYTAG","insidi observe")
                    Toast.makeText(requireContext(), "Login success", Toast.LENGTH_SHORT).show()
                    displayUsersMessagesList()
                    userViewModel.doneNavigatingUserMessages()
                }
            })
        }

        binding.btnNcompte.setOnClickListener{
            Toast.makeText(requireContext(), "nouveaucommpt click", Toast.LENGTH_SHORT).show()
            navigateToRegisterFragment()
           userViewModel.doneNavigatingRegiter()
        }
    }


    private fun displayUsersMessagesList() {
        Log.i("MYTAG","insidisplayUsersList")
        val action = LoginFragmentDirections.actionLoginFragmentToMessagesListFragment()
        NavHostFragment.findNavController(this).navigate(action)

    }

    private fun navigateToRegisterFragment() {
        Log.i("MYTAG","insidisplayUsersList")
        val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
        NavHostFragment.findNavController(this).navigate(action)
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
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}