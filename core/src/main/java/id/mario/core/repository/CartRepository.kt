package id.mario.core.repository

import androidx.lifecycle.LiveData
import id.mario.core.local.ProductDatabase
import id.mario.core.model.CartEntity

class CartRepository(
    private val database: ProductDatabase
) {

    suspend fun deleteAllCartItems() {
        database.cartDao().deleteAllCartItems()
    }

    suspend fun insertProductToCartLineItem(cartEntity: CartEntity) {
        database.cartDao().insertToCartLineItem(cartEntity)
    }

    suspend fun deleteAnItemFromCartLine(cartEntity: CartEntity) {
        database.cartDao().deleteAnItemFromCart(cartEntity)
    }

    fun getCartLineItems(): LiveData<List<CartEntity>> {
        return database.cartDao().getCartItems()
    }

    fun getOnlyOneCartLineItem(id: Int): LiveData<CartEntity?> {
        return database.cartDao().getOneCartItem(id)
    }


}