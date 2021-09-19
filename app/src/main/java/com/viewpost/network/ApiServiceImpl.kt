package com.viewpost.network


import com.mostpopular.NyTimesResponse
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiService: ApiService) {


    suspend fun getMostViewed(period: String): NyTimesResponse = apiService.getMostView(period)
}