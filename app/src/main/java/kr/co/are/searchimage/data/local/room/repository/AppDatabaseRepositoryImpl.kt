package kr.co.are.searchimage.data.local.room.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.co.are.searchimage.data.local.room.databases.AppDatabase
import kr.co.are.searchimage.data.local.room.entity.TableBookmarkInfoEntity
import kr.co.are.searchimage.domain.repositroy.AppDatabaseRepository
import javax.inject.Inject

class AppDatabaseRepositoryImpl @Inject constructor(
    private val appDatabase: AppDatabase,
) : AppDatabaseRepository {


    /*override suspend fun setSettingString(
        license: String,
        key: String,
        value: String
    ): Flow<Boolean> {
        return flow {
            var tableSettingInfoEntity =
                appDatabase.settingDao().selectSetting(license, key)
            if (tableSettingInfoEntity != null) {
                tableSettingInfoEntity.settingStringValue = value
                appDatabase.settingDao().update(tableSettingInfoEntity)
            } else {
                tableSettingInfoEntity = TableBookmarkInfoEntity(
                    userLicense = license,
                    settingTypeStatus = key,
                    settingStringValue = value
                )
                appDatabase.settingDao().insert(tableSettingInfoEntity)
            }
            emit(true)
        }
    }*/
    override suspend fun addBookmarkInfo(): Flow<Boolean> {
        return flow {

            emit(true)
        }
    }

    override suspend fun removeBookmarkInfo(): Flow<Boolean> {
        return flow {

            emit(true)
        }
    }


}