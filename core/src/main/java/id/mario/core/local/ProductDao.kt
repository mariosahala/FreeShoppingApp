package id.mario.core.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.mario.core.model.ProductsItemsModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<ProductsItemsModel>)

    @Query("SELECT * FROM products")
    fun getAllProducts(): Flow<List<ProductsItemsModel>>

    @Query("DELETE FROM products")
    suspend fun deleteAllProducts()

    @Query("SELECT * FROM products WHERE title LIKE :searchQuery OR price  LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<ProductsItemsModel>>

}