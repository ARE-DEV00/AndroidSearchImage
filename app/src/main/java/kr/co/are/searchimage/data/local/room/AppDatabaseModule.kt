package kr.co.are.searchimage.data.local.room

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kr.co.are.searchimage.data.local.room.databases.AppDatabase
import kr.co.are.searchimage.data.local.room.repository.AppDatabaseRepositoryImpl
import kr.co.are.searchimage.domain.repositroy.AppDatabaseRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDataBaseKey.APP_DATABASE_NAME
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideAppDatabaseRepository(database: AppDatabase): AppDatabaseRepository =
        AppDatabaseRepositoryImpl(database)

}