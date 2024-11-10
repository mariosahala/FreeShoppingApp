package id.mario.core.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import id.mario.core.model.CartEntity

@Dao
interface CartDao {
    @Insert
    suspend fun insertToCartLineItem(cartLineItem: CartEntity)

    // Delete all cart items
    @Query("DELETE FROM cart")
    suspend fun deleteAllCartItems()

    // Remove an item from the cart
    @Delete
    suspend fun deleteAnItemFromCart(cartEntity: CartEntity)

    // Get access to all cart Items
    @Query("SELECT * FROM cart ORDER BY id DESC")
    fun getCartItems(): LiveData<List<CartEntity>>

    // get access to only one item in the cart
    @Query("SELECT * FROM cart WHERE id  == :id")
    fun getOneCartItem(id: Int): LiveData<CartEntity?>

}