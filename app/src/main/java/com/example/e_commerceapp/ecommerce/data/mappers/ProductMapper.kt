package com.example.e_commerceapp.ecommerce.data.mappers

import com.example.e_commerceapp.ecommerce.data.networking.dto.ProductDto
import com.example.e_commerceapp.ecommerce.domain.Product

fun ProductDto.toProduct() : Product {
    return Product(
        productId = productId,
        name = name,
        description = description,
        priceUsd = priceUsd,
        imageUrl = image_url ?: "",
        stock = stock,
        productCategory = productCategory
    )

}

fun Product.toProductDto() : ProductDto {
    return ProductDto(
        productId = productId,
        name = name,
        description = description,
        priceUsd = priceUsd,
        image_url = imageUrl,
        stock = stock,
        productCategory = productCategory
    )
}