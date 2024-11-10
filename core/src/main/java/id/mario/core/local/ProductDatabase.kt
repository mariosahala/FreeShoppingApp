package id.mario.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import id.mario.core.model.CartEntity
import id.mario.core.model.ProductsItemsModel

@Database(
    exportSchema = false,
    version = 2,
    entities = [ProductsItemsModel::class, CartEntity::class]
)
@TypeConverters
abstract class ProductDataBase : RoomDatabase() {

    abstract fun productsDao(): ProductDao

    abstract fun cartDao(): CartDao
}