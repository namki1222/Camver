package com.rightcode.ai_workplace_accident.network.model.request.result

data class join(
    var loginId: String,
    var password: String,
    var tel: String,
    var name: String,
)