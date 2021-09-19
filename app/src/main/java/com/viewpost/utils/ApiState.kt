package com.viewpost.utils

import com.mostpopular.NyTimesResponse


sealed class ApiState {

    class Failure(val msg: Throwable) : ApiState()
    class Success(val nyData: NyTimesResponse) : ApiState()

    object Loading : ApiState()
    object Empty : ApiState()
}
