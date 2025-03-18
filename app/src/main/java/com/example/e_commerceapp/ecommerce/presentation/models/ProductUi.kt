package com.example.e_commerceapp.ecommerce.presentation.models

import com.example.e_commerceapp.ecommerce.domain.Product
import java.text.NumberFormat
import java.util.Locale

data class ProductUi(
    val productId : Int,
    val name : String,
    val description : String,
    val priceUsd : DisplayableNumber,
    val imageUrl : String,
    val stock : DisplayableNumber,
    val productCategory : String
)
data class DisplayableNumber(
    val value: Double,
    val formatted: String
)

fun Product.toProductUi() : ProductUi {
    return ProductUi(
        productId = productId,
        name = name,
        description = description,
        priceUsd = priceUsd.toDisplayableNumber(),
        imageUrl = imageUrl,
        stock = stock.toDouble().toDisplayableNumber(),
        productCategory = productCategory
    )
}
fun Double.toDisplayableNumber(): DisplayableNumber {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }
    return DisplayableNumber(
        value = this,
        formatted = formatter.format(this)
    )
}