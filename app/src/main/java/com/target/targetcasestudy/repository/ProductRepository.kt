package com.target.targetcasestudy.repository

import com.target.targetcasestudy.api.ProductApi
import com.target.targetcasestudy.local.ProductDao
import com.target.targetcasestudy.remote.ProductRemoteDataSource
import com.target.targetcasestudy.utils.performGetOperation
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(
    private val remoteDataSource: ProductRemoteDataSource,
    private val localDataSource: ProductDao
) {

    fun getProducts() = performGetOperation(
        databaseQuery = { localDataSource.getAllProducts() },
        networkCall = { remoteDataSource.getProductList() },
        saveCallResult = { localDataSource.insertAll(it.products) }
    )

}