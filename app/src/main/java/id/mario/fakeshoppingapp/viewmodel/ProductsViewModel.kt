package id.mario.fakeshoppingapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.mario.core.repository.ProductRepository
import id.mario.core.util.ApiStates
import id.mario.core.util.State
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsRepository: ProductRepository
) : ViewModel() {

    init {
        getProducts()
    }

    private val _products = MutableSharedFlow<State>()
    val products: SharedFlow<State> = _products

    fun getProducts(searchQuery: String = "") {
        viewModelScope.launch {
            _products.emit(
                State(
                    isLoading = true,
                    error = null,
                    products = emptyList()
                )
            )

            productsRepository.getAllProducts().collectLatest { result ->
                when (result) {
                    is ApiStates.Error -> {
                        _products.emit(
                            State(
                                isLoading = false,
                                error = result.error?.message ?: "Unknown Error Occurred"
                            )
                        )
                    }

                    is ApiStates.Success -> {
                        _products.emit(
                            State(
                                isLoading = false,
                                products = if (searchQuery != "") {
                                    result.data!!.filter {
                                        it.title.contains(searchQuery, ignoreCase = true)
                                    }
                                } else {
                                    result.data ?: emptyList()
                                }
                            )
                        )
                    }

                    else -> {}
                }
            }
        }
    }
}