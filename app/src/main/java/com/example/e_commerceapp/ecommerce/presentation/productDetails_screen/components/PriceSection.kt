package com.example.e_commerceapp.ecommerce.presentation.productDetails_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PriceSection(
    price: String,
    monthlyPayment: Double,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        // Main price
        Text(
            text = "$ $price",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Monthly payment option
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "from $$monthlyPayment per month",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.width(4.dp))

            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Payment info",
                tint = Color.Gray,
                modifier = Modifier
                    .size(16.dp)
                    .clickable { /* Show payment info */ }
            )
        }
    }
}