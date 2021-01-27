package com.target.targetcasestudy.ui.productDetails

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.target.targetcasestudy.model.ProductData
import com.target.targetcasestudy.repository.ProductRepository
import com.target.targetcasestudy.utils.Resource

class ProductDetailViewModel @ViewModelInject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _id = MutableLiveData<Int>()

    private val _product = _id.switchMap { id ->
        productRepository.getProductDetails(id)
    }

    val product: LiveData<Resource<ProductData>> = _product

    fun start(id: Int) {
        _id.value = id
    }

}