package com.techies.threatdetectiondemo.di

import android.content.Context
import com.techies.threatdetectiondemo.BuildConfig
import com.techies.threatdetectiondemo.data.local.LocalDataSource
import com.techies.threatdetectiondemo.data.local.LocalDataSourceImpl
import com.techies.threatdetectiondemo.data.remote.api.ThreatApiService
import com.techies.threatdetectiondemo.data.remote.datasource.RemoteDataSourceImpl
import com.techies.threatdetectiondemo.data.remote.datasource.RemoteDatasource
import com.techies.threatdetectiondemo.data.repository.ThreatRepository
import com.techies.threatdetectiondemo.data.repository.ThreatRepositoryImpl
import com.techies.threatdetectiondemo.domain.usecase.GetThreatsUseCase
import com.techies.threatdetectiondemo.domain.usecase.GetThreatsUseCaseImpl
import com.techies.threatdetectiondemo.presentation.viewmodel.ThreatsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideHttpLogginInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun provideRetrofit(httpLoggingInterceptor: HttpLoggingInterceptor): Retrofit {
        val client = OkHttpClient.Builder()
            .connectTimeout(BuildConfig.TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .writeTimeout(BuildConfig.TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .readTimeout(BuildConfig.TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()

        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(client).build()
    }

    @Provides
    @Singleton
    fun provideThreatsApiService(retrofit: Retrofit): ThreatApiService {
        return retrofit.create(ThreatApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteSource(service: ThreatApiService): RemoteDatasource {
        return RemoteDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun provideLocalDataStore(@ApplicationContext context: Context): LocalDataSource {
        return LocalDataSourceImpl(context)
    }

    @Provides
    fun provideThreatRepository(
        remoteDataSource: RemoteDatasource,
        localDataSource: LocalDataSource
    ): ThreatRepository {
        return ThreatRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Provides
    @Singleton
    fun provideGetThreatsUseCase(repository: ThreatRepository): GetThreatsUseCase {
        return GetThreatsUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun providesThreatsViewModel(
        useCase: GetThreatsUseCase
    ): ThreatsViewModel {
        return ThreatsViewModel(useCase)
    }


}