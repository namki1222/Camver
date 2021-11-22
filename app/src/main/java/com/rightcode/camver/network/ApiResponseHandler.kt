package com.rightcode.camver.network


import com.rightcode.camver.network.model.response.CommonResponse

interface ApiResponseHandler<T : CommonResponse?> {
    fun onSuccess(result: T?)
    fun onServerFail(result: CommonResponse?)
    fun onNoResponse(result: T?)
    fun onBadRequest(t: Throwable?)
}