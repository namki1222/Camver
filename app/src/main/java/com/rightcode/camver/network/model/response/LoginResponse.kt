package com.rightcode.camver.network.model.response

data class LoginResponse(
    var token: String,
    var role: String
): CommonResponse()