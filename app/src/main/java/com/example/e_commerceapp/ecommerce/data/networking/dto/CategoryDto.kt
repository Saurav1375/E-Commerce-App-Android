package com.example.e_commerceapp.ecommerce.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto (
    val id : Int,
    val name: String,
)