package com.target.targetcasestudy.api

import com.target.targetcasestudy.model.ProductData
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Path

interface ProductApi {

    companion object {
        const val BASE_URL = "https://api.target.com/mobile_case_study_deals/v1/"
    }

    @GET("deals")
    suspend fun getProducts(): Response<ProductResponse>

    @GET("deals/{id}")
    suspend fun getProductDetails(@Path("id") id: Int): Response<ProductData>

}