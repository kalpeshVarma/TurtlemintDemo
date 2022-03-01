package com.kalpv.turtlemintdemo.di

import android.content.Context
import com.kalpv.turtlemintdemo.util.TurtlemintApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): TurtlemintApplication {
        return app as TurtlemintApplication
    }

}