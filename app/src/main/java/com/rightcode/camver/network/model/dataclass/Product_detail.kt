package com.rightcode.ai_workplace_accident.network.model.request.result

data class Product_detail(
    var id : Int,
    var category :String,
    var diff :String,
    var name :String,
    var standardPeople : Int ,
    var maxPeople : Int,
    var memo :String,
    var address :String,
    var addressDetail :String,
    var latitude :String,
    var longitude :String,
    var content :String,
    var contentCancel :String,
    var hashtag :String,
    var url :String,
    var priceBasic : Int ,
    var priceSale : Int,
    var pricePeak : Int ,
    var pricePeakSale : Int,
    var priceSemipeak : Int ,
    var priceSemipeakSale : Int,
    var priceWeek : Int ,
    var priceWeekSale : Int,
    var options : ArrayList<options>,
    var images : ArrayList<images>
)