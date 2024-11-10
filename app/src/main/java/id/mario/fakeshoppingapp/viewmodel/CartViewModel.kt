package id.mario.fakeshoppingapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.mario.core.model.CartEntity
import id.mario.core.model.ProductsItemsModel
import id.mario.core.model.mapper.toCartEntity
import id.mario.core.repository.CartRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository
) : ViewModel() {

    val cartLineItems = cartRepository.getCartLineItems()

    fun insertItemToCartLine(productItem: ProductsItemsModel) = viewModelScope.launch {
        cartRepository.insertProductToCartLineItem(productItem.toCartEntity())  // use of mapper
    }

    fun deleteAllItemsFromCartLine() = viewModelScope.launch {
        cartRepository.deleteAllCartItems()
    }

    fun removeOnlyOneItemFromCartLine(cartEntity: CartEntity) = viewModelScope.launch {
        cartRepository.deleteAnItemFromCartLine(cartEntity)
    }

}