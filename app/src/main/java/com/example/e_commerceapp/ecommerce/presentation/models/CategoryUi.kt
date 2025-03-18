package com.example.e_commerceapp.ecommerce.presentation.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sports
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.e_commerceapp.ecommerce.domain.Category

data class CategoryUi (
    val id : Int,
    val name : String,
    val icon : ImageVector
)

fun Category.toCategoryUi() : CategoryUi {
    return CategoryUi(
        id = id,
        name = name.capitalizeFirstLetter(),
        icon = categoryIconFromString("")
    )
}
fun categoryIconFromString(icon: String): ImageVector {
    return Icons.Filled.Sports

}

fun String.capitalizeFirstLetter(): String {
    return this.lowercase().replaceFirstChar { it.uppercaseChar() }
}