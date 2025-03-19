package com.example.e_commerceapp.ecommerce.presentation.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.core.domain.util.onError
import com.example.e_commerceapp.core.domain.util.onSuccess
import com.example.e_commerceapp.ecommerce.domain.ProductService
import com.example.e_commerceapp.ecommerce.presentation.models.CategoryUi
import com.example.e_commerceapp.ecommerce.presentation.models.ProductUi
import com.example.e_commerceapp.ecommerce.presentation.models.toCategoryUi
import com.example.e_commerceapp.ecommerce.presentation.models.toProductUi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductsViewModel(
    private val productService: ProductService
) : ViewModel() {
    private val _products = MutableStateFlow<List<ProductUi>>(emptyList())
    private val _categories = MutableStateFlow<List<CategoryUi>>(emptyList())
    private val _state = MutableStateFlow(ProductState())
    val state = combine(
        _products,
        _categories,
        _state
    ) { products, categories, state ->
        state.copy(
            products = products,
            categories = categories
        )
    }
        .onStart {
            loadCategories()
            loadProducts()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ProductState()
        )


    fun onAction(action: ProductAction) {
        when (action) {
            is ProductAction.OnCategorySelected -> {
                _state.update {
                    it.copy(
                        selectedCategory = action.category
                    )
                }
                loadProductsByCategory(action.category.name)
            }
            is ProductAction.OnSearchQueryChange -> {
                _state.update {
                    it.copy(
                        query = action.query
                    )
                }
            }
            is ProductAction.OnProductSelected -> {
                _state.update { it.copy(selectedProduct = action.product) }
            }

        }

    }
//    private fun loadSelectedProduct(
//        productUi: ProductUi
//    ) {
//        _state.update { it.copy(selectedProduct = productUi) }
//        viewModelScope.launch {
//            productService.getProductById(productUi.productId)
//                .onSuccess { product ->
//                    _state.update {
//                        it.copy(
//                            selectedProduct = product.toProductUi()
//                        )
//                    }
//                }
//                .onError {
//                    _events.send(ProductEvent.Error(it))
//                }
//
//
//        }
//    }

    private fun loadProducts() {
        viewModelScope.launch {
            productService.getProducts()
                .onSuccess { products ->
                    _products.update {
                        products.map { it.toProductUi() }
                    }
                }
                .onError {
                    _events.send(ProductEvent.Error(it))
                }
        }

    }

    private fun loadProductsByCategory(category: String) {
        viewModelScope.launch {
            productService.getProductsByCategory(category)
                .onSuccess { products ->
                    _products.update {
                        products.map { it.toProductUi() }
                    }
                }
                .onError {
                    _events.send(ProductEvent.Error(it))
                }
        }
    }

    private val _events = Channel<ProductEvent>()
    val events = _events.receiveAsFlow()

    private fun loadCategories() {
        viewModelScope.launch {
            productService.getCategories()
                .onSuccess { categories ->
                    _categories.update { categories.map { it.toCategoryUi() } }
                }
                .onError {
                    _events.send(ProductEvent.Error(it))
                }
        }
    }


}