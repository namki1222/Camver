package com.rightcode.camver.network.model.response

open class CommonResponse(
    val statusCode:Int = -1,
    val result: Boolean = false,
    val message: String = ""
)
