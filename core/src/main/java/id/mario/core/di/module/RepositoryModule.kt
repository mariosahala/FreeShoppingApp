package id.mario.core.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.mario.core.local.ProductDatabase
import id.mario.core.remote.ApiService
import id.mario.core.repository.ProductRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesRepository(
        apiService: ApiService,
        productsDatabase: ProductDatabase
    ): ProductRepository {
        return ProductRepository(apiService, productsDatabase)
    }
}