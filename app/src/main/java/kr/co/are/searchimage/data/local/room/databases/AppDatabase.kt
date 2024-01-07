package kr.co.are.searchimage.data.local.room.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import kr.co.are.searchimage.data.local.room.dao.BookmarkInfoDao
import kr.co.are.searchimage.data.local.room.entity.TableBookmarkInfoEntity

@Database(
    entities = [TableBookmarkInfoEntity::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase: RoomDatabase() {
    abstract fun settingDao(): BookmarkInfoDao


}