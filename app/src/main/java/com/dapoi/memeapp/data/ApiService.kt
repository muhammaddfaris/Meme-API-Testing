package com.dapoi.memeapp.data

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    /**
     * get end point
     */
    @GET("/api/image")
    fun getMemes(): Call<MemeResponse>
}