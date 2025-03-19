package com.example.e_commerceapp.ecommerce.presentation.productDetails_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductTopAppBar(
    onBackPressed: () -> Unit,
    isFavorite: Boolean,
    modifier: Modifier = Modifier
) {
    var favoriteState by remember { mutableStateOf(isFavorite) }

    TopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        ),
        title = { },
        navigationIcon = {
            IconButton(
                onClick = onBackPressed,
                modifier = Modifier
                    .padding(start = 9.dp)
                    .clip(CircleShape)
                    .size(40.dp)
                    .background(Color.White)

            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
        },
        actions = {
            IconButton(
                onClick = { favoriteState = !favoriteState },
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
                    .background(Color.White)
            ) {
                Icon(
                    imageVector = if (favoriteState) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = if (favoriteState) "Remove from favorites" else "Add to favorites",
                    tint = if (favoriteState) Color.Red else Color.Black
                )
            }
            Spacer(modifier = Modifier.size(8.dp))
            IconButton(
                onClick = { /* Share product */ },
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
                    .background(Color.White)
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share",
                    tint = Color.Black
                )
            }
        },
    )
}
