package com.kalpv.turtlemintdemo.di

import com.kalpv.turtlemintdemo.data.db.CommentsDao
import com.kalpv.turtlemintdemo.data.db.IssuesDao
import com.kalpv.turtlemintdemo.data.repositories.MainRepository
import com.kalpv.turtlemintdemo.network.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun provideMainRepository(
        apiInterface: ApiInterface,
        issuesDao: IssuesDao,
        commentsDao: CommentsDao
    ): MainRepository =
        MainRepository(apiInterface, issuesDao,commentsDao)

}