package com.kalpv.turtlemintdemo.network.interceptors

import com.kalpv.turtlemintdemo.util.ConnectionTimedOutException
import okhttp3.Interceptor
import okhttp3.Response
import java.net.SocketTimeoutException

class TimeoutConnectionInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            return chain.proceed(chain.request())
        } catch (e: SocketTimeoutException) {
            throw ConnectionTimedOutException("Connection Timed Out")
        }
    }
}