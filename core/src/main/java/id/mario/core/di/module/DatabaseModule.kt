package id.mario.core.di.module

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.mario.core.local.ProductDao
import id.mario.core.local.ProductDatabase
import id.mario.core.repository.CartRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesRoomDatabase(context: Application): ProductDatabase {
        return Room.databaseBuilder(context, ProductDatabase::class.java, "products.db")
            .allowMainThreadQueries()  // without blocking the main thread
            .fallbackToDestructiveMigration() //  Want database to not be cleared when upgrading versions from 1_2
            // .addMigrations()
            .build()
    }

    @Provides
    @Singleton
    fun providesProductsDao(productDatabase: ProductDatabase): ProductDao {
        return productDatabase.productsDao()
    }


    @Provides
    @Singleton
    fun provideCartRepository(productDatabase: ProductDatabase): CartRepository {
        return CartRepository(productDatabase)
    }

}