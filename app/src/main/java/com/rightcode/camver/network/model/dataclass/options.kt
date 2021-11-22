package com.rightcode.ai_workplace_accident.network.model.request.result

data class options(
    var id: Int,
    var sortCode: Int,
    var name: String,
    var categories: ArrayList<categories>
)