package com.example.samil_delacruz_ap2_p2.di

import com.example.samil_delacruz_ap2_p2.data.remote.api.GastosApi
import com.example.samil_delacruz_ap2_p2.data.repository.GastosRepositoryImpl
import com.example.samil_delacruz_ap2_p2.domain.repository.GastosRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    private const val BASE_URL = "https://api-2026-h7eddqgydxc0fmau.eastus2-01.azurewebsites.net/"

    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiGastoService(retrofit: Retrofit): GastosApi =
        retrofit.create(GastosApi::class.java)

    @Provides
    @Singleton
    fun provideGastoRepository(apiService: GastosApi): GastosRepository =
        GastosRepositoryImpl(apiService)
}