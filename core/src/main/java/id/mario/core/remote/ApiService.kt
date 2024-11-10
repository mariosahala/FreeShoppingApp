package id.mario.core.remote

import id.mario.core.model.ProductsItemsModel
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getProducts(): ArrayList<ProductsItemsModel>

}