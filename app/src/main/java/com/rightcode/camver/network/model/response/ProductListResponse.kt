package com.rightcode.camver.network.model.response

import com.rightcode.ai_workplace_accident.network.model.request.result.Product_list


data class ProductListResponse(
    var list : ArrayList<Product_list>
): CommonResponse()