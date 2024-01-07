package kr.co.are.searchimage.data.local.room.dao

import androidx.room.*
import kr.co.are.searchimage.data.local.room.entity.TableBookmarkInfoEntity


@Dao
interface BookmarkInfoDao {

    @Update
    fun update(entity: TableBookmarkInfoEntity)

    @Query("SELECT * FROM bookmark_info WHERE id = :id")
    fun selectBookmarkInfoList(id: String): List<TableBookmarkInfoEntity>

    @Query("SELECT * FROM bookmark_info WHERE id = :id")
    fun selectBookmarkInfoById(id: String): TableBookmarkInfoEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: TableBookmarkInfoEntity)

    @Query("DELETE FROM bookmark_info WHERE id = :id")
    fun deleteBookmarkInfo(id: String)

    @Query("DELETE FROM bookmark_info")
    fun deleteAllBookmarkInfo()

}
// OnConflictStrategy.ABORT	충돌이 발생할 경우 처리 중단
// OnConflictStrategy.FAIL	충돌이 발생할 경우 실패처리
// OnConflictStrategy.IGNORE	충돌이 발생할 경우 무시
// OnConflictStrategy.REPLACE	충돌이 발생할 경우 덮어쓰기
// OnConflictStrategy.ROLLBACK	충돌이 발생할 경우 이전으로 되돌리기
