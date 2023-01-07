package ca.qc.chatproject.roomuserdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

import java.util.*

@Entity
data class User(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    val imageUrl: String = "",
    val login: String = "",
    val nom: String = "",
    val passWord: String = ""
)
