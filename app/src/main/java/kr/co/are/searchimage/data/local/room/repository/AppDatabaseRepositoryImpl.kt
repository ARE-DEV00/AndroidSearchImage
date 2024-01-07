package kr.co.are.searchimage.data.local.room.repository

import android.net.Uri
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.orhanobut.logger.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kr.co.are.searchimage.data.local.room.databases.AppDatabase
import kr.co.are.searchimage.data.local.room.entity.TableBookmarkInfoEntity
import kr.co.are.searchimage.data.local.room.pagesource.BookmarkInfoPagingSource
import kr.co.are.searchimage.data.remote.api.pagingsoruce.PhotoDetailPagingSource
import kr.co.are.searchimage.domain.entitiy.PhotoDetailEntity
import kr.co.are.searchimage.domain.repositroy.AppDatabaseRepository
import javax.inject.Inject

class AppDatabaseRepositoryImpl @Inject constructor(
    private val appDatabase: AppDatabase,
) : AppDatabaseRepository {

    override suspend fun addBookmarkInfo(
        id: String,
        author: String,
        width: Int,
        height: Int,
        createdAt: String,
        description: String,
        imageUrlRaw: String,
        imageUrlFull: String,
        imageUrlRegular: String,
        imageUrlSmall: String,
        imageUrlThumb: String
    ): Flow<Boolean> {
        return flow {
            var tableBookmarkInfoEntity = appDatabase.bookmarkInfoDao().selectBookmarkInfoById(id)

            if (tableBookmarkInfoEntity != null) {
                appDatabase.bookmarkInfoDao().update(tableBookmarkInfoEntity)
            } else {
                tableBookmarkInfoEntity = TableBookmarkInfoEntity(
                    id = id,
                    author = author,
                    width = width,
                    height = height,
                    createdAt = createdAt,
                    description = description,
                    imageUrlRaw = imageUrlRaw,
                    imageUrlFull = imageUrlFull,
                    imageUrlRegular = imageUrlRegular,
                    imageUrlSmall = imageUrlSmall,
                    imageUrlThumb = imageUrlThumb
                )
                appDatabase.bookmarkInfoDao().insert(tableBookmarkInfoEntity)
            }
            emit(true)
        }
    }

    override suspend fun deleteBookmarkInfo(id: String): Flow<Boolean> {
        return flow {
            appDatabase.bookmarkInfoDao().deleteBookmarkInfo(id = id)
            emit(true)
        }
    }

    override suspend fun getBookmarkInfo(id: String): Flow<PhotoDetailEntity?> {
        return flow {
            val tableBookmarkInfoEntity =
                appDatabase.bookmarkInfoDao().selectBookmarkInfoById(id = id)
            if (tableBookmarkInfoEntity != null) {
                emit(
                    convertPhotoDetailEntity(tableBookmarkInfoEntity)
                )
            } else {
                emit(null)
            }
        }
    }

    override suspend fun getBookmarkInfoList(): Flow<List<PhotoDetailEntity>> {
        return flow {
            val tableBookmarkInfoEntity = appDatabase.bookmarkInfoDao().selectBookmarkInfoList()
            if (tableBookmarkInfoEntity != null) {
                emit(tableBookmarkInfoEntity.map {
                    convertPhotoDetailEntity(it)
                })
            } else {
                emit(emptyList())
            }
        }
    }

    override suspend fun getBookmarkInfoPagingList(perPage: Int): Flow<Pager<Int, PhotoDetailEntity>> {
        Logger.d("#### AppDatabaseRepositoryImpl-getBookmarkInfoPagingList")
        return flow {
            Logger.d("#### AppDatabaseRepositoryImpl-getBookmarkInfoPagingList-2")
            emit(Pager(
                config = PagingConfig(
                    pageSize = 10,
                    enablePlaceholders = true,
                    maxSize = 50
                ),
                pagingSourceFactory = {
                    BookmarkInfoPagingSource(appDatabase, perPage)
                }
            ))
        }
    }

    private fun convertPhotoDetailEntity(tableBookmarkInfoEntity: TableBookmarkInfoEntity): PhotoDetailEntity {
        return PhotoDetailEntity(
            imageUrl = PhotoDetailEntity.ImageUrl(
                raw = tableBookmarkInfoEntity.imageUrlRaw,
                full = tableBookmarkInfoEntity.imageUrlFull,
                regular = tableBookmarkInfoEntity.imageUrlRegular,
                small = tableBookmarkInfoEntity.imageUrlSmall,
                thumb = tableBookmarkInfoEntity.imageUrlThumb
            ),
            imageInfo = PhotoDetailEntity.ImageInfo(
                id = tableBookmarkInfoEntity.id,
                author = tableBookmarkInfoEntity.author,
                width = tableBookmarkInfoEntity.width,
                height = tableBookmarkInfoEntity.height,
                createdAt = tableBookmarkInfoEntity.createdAt,
                description = tableBookmarkInfoEntity.description,
            )
        )
    }

}