package kr.co.are.searchimage.data.local.room.converter

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AppTypeConverter {
    private val localDateTimePattern = "yyyy/MM/dd HH:mm:ss.SSS"

    @TypeConverter
    fun localDateTimeToString(localDateTime: LocalDateTime): String =
        localDateTime.format(DateTimeFormatter.ofPattern(localDateTimePattern))

    @TypeConverter
    fun stringToLocalDateTime(string: String): LocalDateTime =
        LocalDateTime.parse(string, DateTimeFormatter.ofPattern(localDateTimePattern))
}