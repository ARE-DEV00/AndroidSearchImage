/*
package kr.co.are.searchimage.presentation.ui.component.photolist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.orhanobut.logger.Logger
import kr.co.are.searchimage.domain.entitiy.PhotoDetailEntity


@Composable
fun PhotoList(
    list: Array<PhotoDetailEntity>,
    onTabImage: () -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
    ) {
        items(items = list) { item ->
            Button(onClick = {
                onTabImage()
            }) {
                Box {
                    AsyncImage(
                        model = item.imageUrl.thumb,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .aspectRatio(1f),
                        contentScale = ContentScale.Crop
                    )

                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(25.dp),
                        tint = Color.Red
                    )
                }
            }


        }

    }
}*/
