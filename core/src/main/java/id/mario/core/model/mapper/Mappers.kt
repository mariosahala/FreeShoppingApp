package id.mario.core.model.mapper

import id.mario.core.model.CartEntity
import id.mario.core.model.ProductsItemsModel

fun ProductsItemsModel.toCartEntity(): CartEntity {
    return CartEntity(
        image = image,
        title = title,
        description = description,
        category = category,
        price = price
    )
}