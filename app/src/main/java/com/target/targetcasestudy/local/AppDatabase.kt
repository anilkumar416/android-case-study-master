package com.target.targetcasestudy.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.target.targetcasestudy.model.ProductData
import com.target.targetcasestudy.utils.RegularPriceConverter
import com.target.targetcasestudy.utils.SalesPriceConverter

@Database(entities = [ProductData::class], version = 1, exportSchema = false)
@TypeConverters(RegularPriceConverter::class, SalesPriceConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "products")
                .fallbackToDestructiveMigration()
                .build()

    }

}