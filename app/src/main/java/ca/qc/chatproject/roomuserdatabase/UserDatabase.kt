package ca.qc.chatproject.roomuserdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [User::class], version = 2)
@TypeConverters(UserTypeConverter::class)
abstract class UserDatabase: RoomDatabase(){
    abstract fun userDao(): UserDao
}