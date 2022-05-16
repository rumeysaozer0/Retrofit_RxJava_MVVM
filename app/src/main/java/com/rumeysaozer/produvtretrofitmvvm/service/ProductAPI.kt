package com.rumeysaozer.produvtretrofitmvvm.service

import com.rumeysaozer.produvtretrofitmvvm.mod.ProductItem
import io.reactivex.Single
import retrofit2.http.GET

interface ProductAPI {
    @GET("products")
    fun getData(): Single<List<ProductItem>>
}