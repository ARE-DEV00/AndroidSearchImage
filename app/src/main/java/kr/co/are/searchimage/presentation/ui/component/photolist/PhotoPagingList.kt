package kr.co.are.searchimage.presentation.ui.component.photolist


import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import kr.co.are.searchimage.domain.entitiy.PhotoDetailEntity

@Composable
fun PhotoPagingList(
    lazyPagingItems: LazyPagingItems<PhotoDetailEntity>,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
    ) {
        items(
            lazyPagingItems.itemCount,
        ) { item ->
            AsyncImage(
                model = lazyPagingItems[item]?.imageUrl?.thumb,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop

            )
        }
    }
}