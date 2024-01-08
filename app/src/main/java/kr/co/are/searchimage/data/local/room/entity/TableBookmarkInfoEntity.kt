package kr.co.are.searchimage.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kr.co.are.searchimage.data.local.room.AppDataBaseKey
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset


@Entity(tableName = AppDataBaseKey.TABLE_BOOKMARK_INFO)
data class TableBookmarkInfoEntity(
    @PrimaryKey
    val id: String,

    val author: String = "",
    val width: Int = 0,
    val height: Int = 0,
    val createdAt: String = "",
    val description: String = "",

    val imageUrlRaw: String,
    val imageUrlFull: String,
    val imageUrlRegular: String,
    val imageUrlSmall: String,
    val imageUrlThumb: String,

    var createdTime: LocalDateTime = LocalDateTime.now(),
    var modifiedTime: LocalDateTime = LocalDateTime.now(ZoneId.from(ZoneOffset.UTC))

)