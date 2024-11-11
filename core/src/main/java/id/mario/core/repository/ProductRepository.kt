package id.mario.core.repository

import androidx.room.withTransaction
import id.mario.core.local.ProductDatabase
import id.mario.core.remote.ApiService
import id.mario.core.util.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val apiService: ApiService,
    private val productsDatabase: ProductDatabase
){
    private val productDao = productsDatabase.productsDao()

    fun getAllProducts() = networkBoundResource(
        query = {
            productDao.getAllProducts()
        },

        fetch = {
            delay(2000)
            apiService.getProducts()
        },

        saveFetchResult = { products ->
            productsDatabase.withTransaction {
                productDao.deleteAllProducts()
                productDao.insertProducts(products)
            }
        },

        shouldFetch = { products ->
            products.isEmpty()
        }

    )
}