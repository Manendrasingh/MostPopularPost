package com.viewpost.di

import com.viewpost.cons.Cons
import com.viewpost.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
/*
* @author Manendra Singh
* This class responsible for providing the dependency of the object
* required for other class
* */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    fun providesUrl() = Cons.BASE_URL

    /*
    * Get the retrofit instance
    * */
    @Provides
    @Singleton
    fun providesApiService(url: String, okHttpClient: OkHttpClient): ApiService =
        Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    /*
    * log request and response details
    * */
    @Provides
    fun provideInterceptor(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level=HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }
}