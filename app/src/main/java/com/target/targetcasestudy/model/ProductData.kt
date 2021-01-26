package com.target.targetcasestudy.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "products")
data class ProductData(
    @PrimaryKey
    val id: Int?,
    val title: String?,
    val aisle: String?,
    val description: String?,
    val image_url: String?,
    val regular_price: RegularPrice?,
    val sale_price: SalePrice?
)

data class RegularPrice(
    val amount_in_cents: Int?,
    val currency_symbol: String?,
    val display_string: String?
)

data class SalePrice(
    val amount_in_cents: Int?,
    val currency_symbol: String?,
    val display_string: String?
)