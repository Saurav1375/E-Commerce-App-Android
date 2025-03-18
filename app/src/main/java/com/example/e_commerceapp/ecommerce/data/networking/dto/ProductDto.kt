package com.example.e_commerceapp.ecommerce.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    val productId : Int,
    val name : String,
    val description : String,
    val priceUsd : Double,
    val image_url : String?,
    val stock : Int,
    val productCategory : String
)