package com.example.e_commerceapp.ecommerce.presentation.productDetails_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.e_commerceapp.ecommerce.presentation.home_screen.ProductAction
import com.example.e_commerceapp.ecommerce.presentation.home_screen.ProductState
import com.example.e_commerceapp.ecommerce.presentation.productDetails_screen.components.ExpandableText
import com.example.e_commerceapp.ecommerce.presentation.productDetails_screen.components.ImageCarouselWithIndicators
import com.example.e_commerceapp.ecommerce.presentation.productDetails_screen.components.PriceSection
import com.example.e_commerceapp.ecommerce.presentation.productDetails_screen.components.ProductRatingSection
import com.example.e_commerceapp.ecommerce.presentation.productDetails_screen.components.ProductTopAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProductDetailScreen(
    state: ProductState,
    onAction: (ProductAction) -> Unit,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    val product = state.selectedProduct
    println(product)
    Scaffold(
        topBar = {
            ProductTopAppBar(
                onBackPressed = onBackPressed,
                isFavorite = true
            )
        }
    ) {
        if(state.isLoading) {
            Box(
                modifier = modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        else if (product != null) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .verticalScroll(rememberScrollState())
            ) {
                // Image carousel with indicators
                ImageCarouselWithIndicators(
                    imageUrls = listOf(product.imageUrl),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )

                // Product Info
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    // Title
                    Text(
                        text = product.name,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.surface
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Ratings and review section
                    ProductRatingSection(
                        rating = 4.5f,
                        reviewCount = 1000,
                        recommendationPercentage = 87,
                        questionCount = 34
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Price section
                    PriceSection(
                        price = product.priceUsd.formatted,
                        monthlyPayment = 200.12
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Product description

                    ExpandableText(
                        text = product.description,
                        textColor = MaterialTheme.colorScheme.surface
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Add to cart button
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFD0E612)
                        )
                    ) {
                        Text(
                            text = "Add to cart",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Delivery date
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Delivery on 26 October",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}
