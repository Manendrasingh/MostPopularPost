package com.viewpost.repositories


import com.mostpopular.NyTimesResponse
import com.viewpost.network.ApiServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
/*
* get data from api
* */
class MainRepository
@Inject
constructor(private val apiServiceImpl: ApiServiceImpl) {


    fun getMostViewApiData(period:String): Flow<NyTimesResponse> = flow {
        emit(apiServiceImpl.getMostViewed(period))
    }.flowOn(Dispatchers.IO)

}