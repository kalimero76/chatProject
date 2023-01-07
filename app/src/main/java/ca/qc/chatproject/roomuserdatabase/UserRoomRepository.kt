package ca.qc.chatproject.roomuserdatabase

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

import java.lang.IllegalStateException
import java.util.concurrent.Executors

private const val DATABASE_NAME = "user-database"

class UserRepository private constructor(context: Context){

    private val migration_1_2 = object : Migration(1,2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("alter table User add column suspect text not null default ''")
        }
    }
    private val database: UserDatabase = Room.databaseBuilder(
        context.applicationContext,
        UserDatabase::class.java,
        DATABASE_NAME
    ).addMigrations(migration_1_2)
        .build()



    private val userDao = database.userDao()

    private val executor = Executors.newSingleThreadExecutor()

    fun getUsers(): LiveData<List<User>> = userDao.getUsers()

   // fun getUser(id: UUID): LiveData<User> = userDao.getUser(id)
   fun getUser(login: String): LiveData<User> = userDao.getUser(login)

    fun updateUser(user: User) {
        executor.execute {
           userDao.updateUser(user)
        }
    }

    fun addUser(user: User) {
        executor.execute {
           userDao.addUser(user)
        }
    }

    companion object{
        private var instance: UserRepository? = null
        fun init(context: Context) {
            if (instance == null) {
                instance = UserRepository(context)
            }
        }
        fun get(): UserRepository {
            return instance ?: throw IllegalStateException("User Repository must be init")
        }
    }
}