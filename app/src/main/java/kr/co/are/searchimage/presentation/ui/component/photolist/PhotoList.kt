package kr.co.are.searchimage.presentation.ui.component.photolist

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import coil.compose.AsyncImage
import com.orhanobut.logger.Logger
import kr.co.are.searchimage.domain.entitiy.PhotoDetailEntity


@Composable
fun PhotoList(
    list: Array<PhotoDetailEntity>,
    onReachedEnd: () -> Unit
) {
    val lazyListState = rememberLazyListState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier
            .onGloballyPositioned {
                val totalItems = lazyListState.layoutInfo.totalItemsCount
                val lastVisibleItem = lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()

                Logger.d("#### totalItems:${totalItems} / lastVisibleItem:${lastVisibleItem?.index}")

                if (lastVisibleItem != null && lastVisibleItem.index == totalItems - 1) {
                    onReachedEnd()
                }
            }
    ) {
        items(items = list) { item ->
            AsyncImage(
                model = item.thumb,
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