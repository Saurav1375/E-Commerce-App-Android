package com.example.e_commerceapp.ecommerce.data.mappers

import com.example.e_commerceapp.ecommerce.data.networking.dto.CategoryDto
import com.example.e_commerceapp.ecommerce.domain.Category

fun CategoryDto.toCategory(): Category {
    return Category(
        id = id,
        name = name,
    )

}