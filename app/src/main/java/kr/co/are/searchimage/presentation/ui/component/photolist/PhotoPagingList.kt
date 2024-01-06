package kr.co.are.searchimage.presentation.ui.component.photolist


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import kr.co.are.searchimage.domain.entitiy.PhotoDetailEntity

@Composable
fun PhotoPagingList(
    lazyPagingItems: LazyPagingItems<PhotoDetailEntity>,
    onTabImage: (id: String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
    ) {
        items(
            lazyPagingItems.itemCount,
        ) { item ->
            Box(modifier = Modifier.clickable {
                lazyPagingItems[item]?.imageInfo?.id?.let { onTabImage(it) }
            }) {
                AsyncImage(
                    model = lazyPagingItems[item]?.imageUrl?.thumb,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop,
                )

                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(25.dp, 25.dp)
                        .padding(top=5.dp, end=5.dp),
                    tint = Color.Red,
                )

            }
        }
    }
}