package com.example.e_commerceapp.ecommerce.domain

import com.example.e_commerceapp.core.domain.util.NetworkError
import com.example.e_commerceapp.core.domain.util.Result

interface ProductService {
    suspend fun getProducts(): Result<List<Product>, NetworkError>
    suspend fun getProductById(productId: Int): Result<Product, NetworkError>
    suspend fun getProductsByCategory(category: String): Result<List<Product>, NetworkError>
    suspend fun searchProducts(query: String): Result<List<Product>, NetworkError>
    suspend fun addProduct(product: Product): Result<Unit, NetworkError>
    suspend fun updateProduct(productId: Int, product: Product): Result<Unit, NetworkError>
    suspend fun deleteProduct(productId: Int) : Result<Unit, NetworkError>

    suspend fun getCategories(): Result<List<Category>, NetworkError>

}