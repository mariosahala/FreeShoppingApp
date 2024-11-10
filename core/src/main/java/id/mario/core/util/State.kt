package id.mario.core.util

import id.mario.core.model.ProductsItemsModel


data class State(
    val isLoading: Boolean = false,
    val error: String? = null,
    val products: List<ProductsItemsModel> = emptyList()
)