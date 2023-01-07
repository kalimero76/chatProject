package ca.qc.chatproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import ca.qc.chatproject.databinding.FragmentUsersListBinding
import ca.qc.chatproject.roomuserdatabase.User
import ca.qc.chatproject.roomuserdatabase.UserDetailRoomViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UserFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

private const val ARG_USER_ID = "user_id"
class UserFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var user: User
    private var _binding: FragmentUsersListBinding?=null
    private val binding get() = _binding!!


    private val userViewModel: UserDetailRoomViewModel by lazy {
        ViewModelProvider(this).get(UserDetailRoomViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

       // val userId = arguments?.getSerializable(ARG_USER_ID) as UUID
        val userLogin = arguments?.getSerializable(ARG_USER_ID) as String
        user = User()
        userViewModel.loadUser(userLogin)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentUsersListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel.userLiveData.observe(
            viewLifecycleOwner,
            Observer {
                it?.let {
                    this.user = it
                    //updateUI()
                }
            }
        )

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UserFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}