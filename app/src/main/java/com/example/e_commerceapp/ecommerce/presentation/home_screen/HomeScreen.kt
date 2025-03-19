package com.example.e_commerceapp.ecommerce.presentation.home_screen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.e_commerceapp.ecommerce.presentation.components.CategorySection
import com.example.e_commerceapp.ecommerce.presentation.components.CustomSearchBar
import com.example.e_commerceapp.ecommerce.presentation.components.ProductsListingSection
import com.example.e_commerceapp.ecommerce.presentation.home_screen.components.DeliveryBanner
import com.example.e_commerceapp.ecommerce.presentation.models.CategoryUi
import com.example.e_commerceapp.ecommerce.presentation.models.capitalizeFirstLetter


@Composable
fun HomeScreen(
    state: ProductState,
    modifier: Modifier = Modifier,
    onAction: (ProductAction) -> Unit,
) {
    val categories = state.categories
    val products = state.products

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8F8F8))
            .padding(bottom = 16.dp)
    ) {
       CustomSearchBar(
            modifier = Modifier.padding(16.dp),
           searchText = state.query,
           onSearchTextChanged = {
               onAction(ProductAction.OnSearchQueryChange(it))
           }
        )

        DeliveryBanner(
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        CategorySection(
            categories = categories,
            modifier = Modifier.padding(top = 24.dp),
            selectedCategory = state.selectedCategory ,
            onCategoryClick = {
                onAction(ProductAction.OnCategorySelected(it))
            }
        )

        ProductsListingSection(
            products = products,
            sectionHeading = state.selectedCategory?.name?.capitalizeFirstLetter() ?: "Flash Sale",
            onProductClick = {
                onAction(ProductAction.OnProductSelected(it))
            },
            modifier = Modifier.padding(top = 24.dp)
        )
    }
}
