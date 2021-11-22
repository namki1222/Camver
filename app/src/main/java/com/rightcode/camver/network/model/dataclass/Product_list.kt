package com.rightcode.ai_workplace_accident.network.model.request.result

data class Product_list(
    var id :Int,
    var category : String,
    var diff : String,
    var name : String,
    var memo : String,
    var address : String,
    var addressDetail : String,
    var latitude : String,
    var longitude : String,
    var priceBasic : Int,
    var thumbnail : String,
    var averageRate : Float,
    var reviewCount : Int
)