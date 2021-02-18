package com.target.targetcasestudy.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.target.targetcasestudy.api.ProductApi
import com.target.targetcasestudy.local.AppDatabase
import com.target.targetcasestudy.local.ProductDao
import com.target.targetcasestudy.remote.ProductRemoteDataSource
import com.target.targetcasestudy.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
            Retrofit.Builder()
                    .baseUrl(ProductApi.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideProductApi(retrofit: Retrofit): ProductApi =
            retrofit.create(ProductApi::class.java)

    @Singleton
    @Provides
    fun provideCharacterRemoteDataSource(characterService: ProductApi) =
            ProductRemoteDataSource(characterService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
            AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: AppDatabase) = db.productDao()

    @Singleton
    @Provides
    fun provideRepository(
            remoteDataSource: ProductRemoteDataSource,
            localDataSource: ProductDao) = ProductRepository(remoteDataSource, localDataSource)

}