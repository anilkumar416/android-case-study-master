package com.target.targetcasestudy.ui.productList

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.target.targetcasestudy.repository.ProductRepository

class ProductListViewModel @ViewModelInject constructor(
    private val repository: ProductRepository
) : ViewModel() {
    
    val products = repository.getProducts()

}
