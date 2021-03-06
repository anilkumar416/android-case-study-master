package com.target.targetcasestudy.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.target.targetcasestudy.model.ProductData

@Dao
interface ProductDao {

    @Query("SELECT * FROM products")
    fun getAllProducts(): LiveData<List<ProductData>>

    @Query("SELECT * FROM products WHERE id = :id")
    fun getproductDetail(id: Int): LiveData<ProductData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<ProductData>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(productData: ProductData)

}