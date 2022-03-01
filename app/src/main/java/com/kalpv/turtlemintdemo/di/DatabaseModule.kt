package com.kalpv.turtlemintdemo.di

import android.content.Context
import com.kalpv.turtlemintdemo.data.db.AppDatabase
import com.kalpv.turtlemintdemo.data.db.CommentsDao
import com.kalpv.turtlemintdemo.data.db.IssuesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase(context)
    }

    @Provides
    @Singleton
    fun provideIssuesDao(appDatabase: AppDatabase):IssuesDao{
        return appDatabase.getIssuesDao()
    }

    @Provides
    @Singleton
    fun provideCommentsDao(appDatabase: AppDatabase):CommentsDao{
        return appDatabase.getCommentsDao()
    }
}