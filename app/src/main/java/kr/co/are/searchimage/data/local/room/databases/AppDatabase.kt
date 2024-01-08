package kr.co.are.searchimage.data.local.room.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kr.co.are.searchimage.data.local.room.converter.AppTypeConverter
import kr.co.are.searchimage.data.local.room.dao.BookmarkInfoDao
import kr.co.are.searchimage.data.local.room.entity.TableBookmarkInfoEntity

@Database(
    entities = [TableBookmarkInfoEntity::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(AppTypeConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun bookmarkInfoDao(): BookmarkInfoDao


}