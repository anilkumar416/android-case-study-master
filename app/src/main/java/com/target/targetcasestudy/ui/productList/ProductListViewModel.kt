package com.target.targetcasestudy.ui.productList

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.api.ProductResponse
import com.target.targetcasestudy.model.ProductData
import com.target.targetcasestudy.repository.ProductRepository
import com.target.targetcasestudy.utils.Resource
import kotlinx.coroutines.launch

class ProductListViewModel @ViewModelInject constructor(
    private val repository: ProductRepository
) : ViewModel() {
    
    val products = repository.getProducts()

}
