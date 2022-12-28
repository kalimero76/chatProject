package ca.qc.chatproject.adapters

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ca.qc.chatproject.R
import ca.qc.chatproject.models.UserData
import ca.qc.chatproject.models.UserLoginData

class UsersAdapter: RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    private var users = emptyList<UserData>()

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var login = itemView.findViewById<TextView>(R.id.login)
        var passeword=itemView.findViewById<TextView>(R.id.password)

        fun bind(position: Int) {
            val user= users[position]
            login.text=user.login
            passeword.text= user.password
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val userView = LayoutInflater.from(parent.context).inflate(R.layout.users_list_item, parent,false)
    return UserViewHolder(userView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

    holder.bind(position)
    }

    override fun getItemCount() :Int {
        if (users == null)
        return 0
      return   users.size

    }

    fun setUsers(usersa: List<UserData>){
        this.users = usersa
        notifyDataSetChanged()
    }


}