package com.example.e_commerceapp.ecommerce.presentation.home_screen

import androidx.compose.runtime.Immutable
import com.example.e_commerceapp.ecommerce.presentation.models.CategoryUi
import com.example.e_commerceapp.ecommerce.presentation.models.ProductUi

@Immutable
data class ProductState(
    val isLoading: Boolean = false,
    val products: List<ProductUi> = emptyList(),
    val selectedCategory: CategoryUi? = null,
    val categories: List<CategoryUi> = emptyList(),
    val selectedProduct: ProductUi? = null,
    val query: String = "",
)