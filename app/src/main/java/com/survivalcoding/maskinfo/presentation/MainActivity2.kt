package com.survivalcoding.maskinfo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.survivalcoding.maskinfo.presentation.component.PhotoItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = viewModel<MainViewModel>()

            val state by viewModel.state.collectAsState()

            Column {
                OutlinedTextField(
                    value = state.query,
                    onValueChange = viewModel::changeQuery,
                    placeholder = { Text("Search") },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    trailingIcon = {
                        IconButton(onClick = {
                            viewModel.fetchPhotos()
                        }) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "search icon",
                            )
                        }
                    }
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(items = state.photos) { photo ->
                        PhotoItem(photo)
                    }
                }
            }
        }
    }
}
