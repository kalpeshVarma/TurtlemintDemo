package com.kalpv.turtlemintdemo.network

import com.kalpv.turtlemintdemo.data.model.Comment
import com.kalpv.turtlemintdemo.data.model.Issues
import com.kalpv.turtlemintdemo.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiInterface {
    @GET(Constants.ISSUES)
    suspend fun fetchIssues(): Response<List<Issues>>

    @GET
    suspend fun fetchComments(@Url commentUrl: String): Response<List<Comment>>
}