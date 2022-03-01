package com.kalpv.turtlemintdemo.di

import android.content.Context
import com.kalpv.turtlemintdemo.BuildConfig
import com.kalpv.turtlemintdemo.network.ApiInterface
import com.kalpv.turtlemintdemo.network.interceptors.NetworkConnectionInterceptor
import com.kalpv.turtlemintdemo.network.interceptors.TimeoutConnectionInterceptor
import com.kalpv.turtlemintdemo.util.Constants
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
object ApiModule {

    @Singleton
    @Provides
    fun providesTimeoutInterception(): TimeoutConnectionInterceptor {
        return TimeoutConnectionInterceptor()
    }

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
        }
    }


    @Singleton
    @Provides
    fun providesNetworkConnectionInterceptor(@ApplicationContext context: Context): NetworkConnectionInterceptor {
        return NetworkConnectionInterceptor(context)
    }



    @Singleton
    @Provides
    fun providesOkHttpClient(
        networkConnectionInterceptor: NetworkConnectionInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        timeoutConnectionInterceptor: TimeoutConnectionInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(networkConnectionInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(timeoutConnectionInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitClient(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiInterface(retrofit: Retrofit): ApiInterface =
        retrofit.create(ApiInterface::class.java)
}