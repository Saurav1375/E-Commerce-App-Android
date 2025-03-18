package com.example.e_commerceapp.ecommerce.data.networking

import com.example.e_commerceapp.core.data.networking.constructUrl
import com.example.e_commerceapp.core.data.networking.safeCall
import com.example.e_commerceapp.core.domain.util.NetworkError
import com.example.e_commerceapp.core.domain.util.Result
import com.example.e_commerceapp.core.domain.util.map
import com.example.e_commerceapp.ecommerce.data.mappers.toCategory
import com.example.e_commerceapp.ecommerce.data.mappers.toProduct
import com.example.e_commerceapp.ecommerce.data.mappers.toProductDto
import com.example.e_commerceapp.ecommerce.data.networking.dto.CategoryDto
import com.example.e_commerceapp.ecommerce.data.networking.dto.ProductDto
import com.example.e_commerceapp.ecommerce.domain.Category
import com.example.e_commerceapp.ecommerce.domain.Product
import com.example.e_commerceapp.ecommerce.domain.ProductService
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ProductServiceImpl(
    private val httpClient: HttpClient
) : ProductService {
    override suspend fun getProducts(): Result<List<Product>, NetworkError> {
        return safeCall<List<ProductDto>> {
            httpClient.get(constructUrl("/products"))
        }.map {
            it.map(ProductDto::toProduct)
        }
    }

    override suspend fun getProductById(productId: Int): Result<Product, NetworkError> {
        return safeCall<ProductDto> {
            httpClient.get(constructUrl("/products/id/$productId"))
        }.map(ProductDto::toProduct)
    }

    override suspend fun getProductsByCategory(category: String): Result<List<Product>, NetworkError> {
        return safeCall<List<ProductDto>> {
            httpClient.get(constructUrl("/products/category/$category"))
        }.map {
            it.map(ProductDto::toProduct)
        }
    }

    override suspend fun searchProducts(query: String): Result<List<Product>, NetworkError> {
        return safeCall<List<ProductDto>> {
            httpClient.get(constructUrl("/products/search/$query"))
        }.map {
            it.map(ProductDto::toProduct)
        }
    }

    override suspend fun addProduct(product: Product): Result<Unit, NetworkError> {
        return safeCall<Unit> {
            httpClient.post(constructUrl("/products")) {
                contentType(ContentType.Application.Json)
                setBody(product.toProductDto())
            }
        }
    }

    override suspend fun updateProduct(
        productId: Int,
        product: Product
    ): Result<Unit, NetworkError> {
        return safeCall<Unit> {
            httpClient.post(constructUrl("/products/$productId")) {
                contentType(ContentType.Application.Json)
                setBody(product.toProductDto())
            }
        }
    }

    override suspend fun deleteProduct(productId: Int): Result<Unit, NetworkError> {
        return safeCall<Unit> {
            httpClient.post(constructUrl("/products/$productId"))
        }
    }

    override suspend fun getCategories(): Result<List<Category>, NetworkError> {
        return safeCall<List<CategoryDto>> {
            httpClient.get(constructUrl("/products/categories"))
        }.map {
            it.map(CategoryDto::toCategory)
        }
    }
}