package com.target.targetcasestudy.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.target.targetcasestudy.getOrAwaitValue
import com.target.targetcasestudy.model.ProductData
import com.target.targetcasestudy.model.RegularPrice
import com.target.targetcasestudy.model.SalePrice
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class ProductDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var dao: ProductDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.productDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertProductDetails() = runBlockingTest {
        val regularPrice = RegularPrice(12, "$12", "$12")
        val salePrice = SalePrice(10, "10$", "10$")
        val productItem = ProductData(
            1, "test1", "b1", "Testing room db",
            "url", regularPrice, salePrice
        )
        dao.insert(productItem)

        val allproductItems = dao.getAllProducts().getOrAwaitValue()
        assertThat(allproductItems).contains(productItem)
    }

    @Test
    fun getOneProductDetails() = runBlockingTest {
        val regularPrice = RegularPrice(12, "$12", "$12")
        val salePrice = SalePrice(10, "10$", "10$")
        val productItem = ProductData(
            1, "test1", "b1", "Testing room db",
            "url", regularPrice, salePrice
        )
        dao.insert(productItem)

        val allproductItems = dao.getproductDetail(1).getOrAwaitValue()
        assertThat(allproductItems).isEqualTo(productItem)
    }

}