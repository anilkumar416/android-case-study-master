package com.target.targetcasestudy.remote

import com.target.targetcasestudy.api.ProductApi
import javax.inject.Inject

class ProductRemoteDataSource @Inject constructor(
    private val productApi: ProductApi
) : BaseDataSource() {

    suspend fun getProductList() = getResult { productApi.getProducts() }

}