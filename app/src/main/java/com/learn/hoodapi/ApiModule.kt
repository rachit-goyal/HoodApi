package com.learn.hoodapi

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
created by Rachit on 12/24/2023.
 */

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideRetrofit():Retrofit{
       return Retrofit.Builder().baseUrl(ApiConstants.BASE_URL).addConverterFactory(
           GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit):ApiService{
     return   retrofit.create(ApiService::class.java)
    }
}