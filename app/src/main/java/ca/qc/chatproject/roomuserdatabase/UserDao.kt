package ca.qc.chatproject.roomuserdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import java.util.*

@Dao
interface UserDao {
    @Query("select * from user")
    fun getUsers(): LiveData<List<User>>

   /* @Query("select * from user where id = (:id)")
    fun getUser(id: UUID): LiveData<User>*/

    @Query("select * from user where login = (:login)")
    fun getUser(login: String): LiveData<User>

    @Update
    fun updateUser(user: User)

    @Insert
    fun addUser(user: User)
}