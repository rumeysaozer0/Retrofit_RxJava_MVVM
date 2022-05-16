package com.rumeysaozer.produvtretrofitmvvm.service

import com.rumeysaozer.produvtretrofitmvvm.mod.ProductItem
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ProductAPIService {
    private val BASE_URL = "https://fakestoreapi.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ProductAPI::class.java)
    fun getData(): Single<List<ProductItem>>{
        return api.getData()
    }

}