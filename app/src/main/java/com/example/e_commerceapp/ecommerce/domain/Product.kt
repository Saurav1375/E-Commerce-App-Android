package com.example.e_commerceapp.ecommerce.domain

data class Product(
    val productId : Int,
    val name : String,
    val description : String,
    val priceUsd : Double,
    val imageUrl : String,
    val stock : Int,
    val productCategory : String
)