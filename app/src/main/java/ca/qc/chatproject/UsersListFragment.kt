package ca.qc.chatproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import ca.qc.chatproject.adapters.UsersAdapter
import ca.qc.chatproject.databinding.FragmentUsersListBinding
import ca.qc.chatproject.viewModels.UsersViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UsersListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UsersListFragment : Fragment() {


    private lateinit var usersAdapter: UsersAdapter

    private val userViewModel: UsersViewModel by navGraphViewModels(R.id.messages_nav_graph)


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding:FragmentUsersListBinding?=null
    private val binding get() = _binding!!

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
        _binding= FragmentUsersListBinding.inflate(inflater, container, false)
        val view = binding.root
        // Inflate the layout for this fragment
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        usersAdapter = UsersAdapter()
        binding.userListRecyclerView.adapter = usersAdapter

        userViewModel.users.observe( viewLifecycleOwner,
            Observer {userResponse->usersAdapter.setUsers(userResponse.data)
                Log.i("Message fragment ",   userResponse.data.joinToString { "\n  ${it.imageUrl} ${it.login}   ${it.nom}" })
              }


        )

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
         * @return A new instance of fragment UsersListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UsersListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}