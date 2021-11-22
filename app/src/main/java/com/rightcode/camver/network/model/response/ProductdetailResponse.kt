package com.rightcode.camver.network.model.response

import com.rightcode.ai_workplace_accident.network.model.request.result.Product_detail
import com.rightcode.ai_workplace_accident.network.model.request.result.Product_list


data class ProductdetailResponse(
    var data : Product_detail
): CommonResponse()