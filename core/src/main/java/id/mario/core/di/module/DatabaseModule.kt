package id.mario.core.di.module

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.mario.core.local.ProductDao
import id.mario.core.local.ProductDataBase
import id.mario.core.repository.CartRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesRoomDatabase(context: Application): ProductDataBase {
        return Room.databaseBuilder(context, ProductDataBase::class.java, "products.db")
            .allowMainThreadQueries()  // without blocking the main thread
            .fallbackToDestructiveMigration() //  Want database to not be cleared when upgrading versions from 1_2
            // .addMigrations()
            .build()
    }

    @Provides
    @Singleton
    fun providesProductsDao(productDatabase: ProductDataBase): ProductDao {
        return productDatabase.productsDao()
    }


    @Provides
    @Singleton
    fun provideCartRepository(productDatabase: ProductDataBase): CartRepository {
        return CartRepository(productDatabase)
    }

}