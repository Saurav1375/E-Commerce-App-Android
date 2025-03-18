package com.example.e_commerceapp.ecommerce.presentation.home_screen

import com.example.e_commerceapp.ecommerce.presentation.models.CategoryUi
import com.example.e_commerceapp.ecommerce.presentation.models.ProductUi

sealed interface ProductAction {
    data class OnCategorySelected(val category: CategoryUi) : ProductAction
    data class OnSearchQueryChange(val query: String) : ProductAction
    data class OnProductSelected(val product: ProductUi) : ProductAction
}