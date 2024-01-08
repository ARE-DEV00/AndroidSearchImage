package kr.co.are.searchimage.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.co.are.searchimage.data.local.room.databases.AppDatabase
import kr.co.are.searchimage.data.remote.api.ApiService
import kr.co.are.searchimage.data.repository.AppDataRepositoryImpl
import kr.co.are.searchimage.domain.repositroy.AppDataRepository
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDataModule {

    @Provides
    @Singleton
    fun provideAppDataRepository(apiService: ApiService, retrofit: Retrofit,database: AppDatabase): AppDataRepository =
        AppDataRepositoryImpl(apiService, retrofit, database)

}