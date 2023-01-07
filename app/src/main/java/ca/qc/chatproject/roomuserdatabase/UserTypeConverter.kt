package ca.qc.chatproject.roomuserdatabase

import androidx.room.TypeConverter
import java.util.*

class UserTypeConverter {
    @TypeConverter
    fun fromDate(date: Date?) : Long?{
        return date?.time
    }

    @TypeConverter
    fun toDate(millisSinceEpoch: Long?): Date? {
        return millisSinceEpoch?.let {
            Date(it)
        }
    }

    @TypeConverter
    fun fromUUID(uuid: UUID?): String? {
        return uuid?.toString()
    }

    @TypeConverter
    fun fromUUID(uuid: String?): UUID? {
        return UUID.fromString(uuid)
    }
}