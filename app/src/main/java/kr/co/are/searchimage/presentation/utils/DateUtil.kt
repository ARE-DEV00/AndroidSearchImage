package kr.co.are.searchimage.presentation.utils

import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.TimeZone

object DateUtil {

    @JvmStatic
    fun convertUtcToLocal(utcDateStr: String?): String {
        return try {
            val utcDateTime: ZonedDateTime =
                ZonedDateTime.from(DateTimeFormatter.ISO_DATE_TIME.parse(utcDateStr))
            val localDateTime: ZonedDateTime =
                utcDateTime.withZoneSameInstant(ZoneId.of(TimeZone.getDefault().id))

            localDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")).toString()
        } catch (ex: Exception) {
            ex.printStackTrace()
            ""
        }
    }
}