package com.rightcode.camver.network.model.response

import com.rightcode.ai_workplace_accident.network.model.request.result.Findid

data class PasswordChange(
    var loginId :String,
    var password :String,
    var tel :String
)