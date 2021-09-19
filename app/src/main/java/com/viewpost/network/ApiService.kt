package com.viewpost.network

import com.mostpopular.NyTimesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

//http://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/7.json?api-key=sample-key
//http://api.nytimes.com/svc/mostpopular/v2/mostviewed/{section}/{period}.json?api-key=sample-key

    @GET("/svc/mostpopular/v2/mostviewed/all-sections/{period}.json?api-key=HaHg7rdRMp1kVpU3GJmdIviaZa3Udk2f")
    suspend fun getMostView(@Path("period")  period:String): NyTimesResponse
}