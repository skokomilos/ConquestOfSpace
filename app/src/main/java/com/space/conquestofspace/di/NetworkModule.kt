package com.space.conquestofspace.di

import android.app.Application
import com.space.conquestofspace.BuildConfig
import com.space.conquestofspace.data.remote.TheSpaceDevApi
import com.space.conquestofspace.data.remote.interceptor.NetworkInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(BuildConfig.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
        }.build()
    }

    @Provides
    fun provideRestApiService(retrofit: Retrofit): TheSpaceDevApi {
        return retrofit.create(TheSpaceDevApi::class.java)
    }

    @Provides
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideHttpClient(
        networkInterceptor: NetworkInterceptor,
        loggingInterceptor: HttpLoggingInterceptor) : OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(networkInterceptor)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
            addInterceptor(loggingInterceptor)
        }.build()
    }

    @Provides
    fun provideInterceptor(application : Application) : NetworkInterceptor {
        return NetworkInterceptor(application)
    }
}