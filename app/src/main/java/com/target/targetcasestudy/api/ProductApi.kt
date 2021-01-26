package com.target.targetcasestudy.api

import retrofit2.http.GET
import retrofit2.Response

interface ProductApi {

    companion object {
        const val BASE_URL = "https://api.target.com/"
    }

    @GET("mobile_case_study_deals/v1/deals")
    suspend fun getProducts(): Response<ProductResponse>

}