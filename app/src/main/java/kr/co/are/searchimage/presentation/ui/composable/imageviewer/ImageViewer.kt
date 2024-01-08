package kr.co.are.searchimage.presentation.ui.composable.imageviewer

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import kr.co.are.searchimage.presentation.ui.theme.Black
import kr.co.are.searchimage.presentation.ui.theme.White


@Composable
fun ImageViewer(
    imageUrl: String,
    onTabClose: () -> Unit
) {
    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset(0f, 0f)) }
    var previousOffset by remember { mutableStateOf(Offset(0f, 0f)) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    scale *= zoom
                    offset = if (scale > 1f) {
                        Offset(offset.x + pan.x, offset.y + pan.y)
                    } else {
                        Offset(0f, 0f)
                    }
                }
            }
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
                .graphicsLayer(
                    scaleX = maxOf(1f, minOf(3f, scale)),
                    scaleY = maxOf(1f, minOf(3f, scale)),
                    translationX = offset.x,
                    translationY = offset.y
                ),
            contentScale = ContentScale.Crop
        )

        IconButton(
            onClick = {
                onTabClose()
            },
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                modifier = Modifier.size(30.dp, 30.dp),
                tint = White
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ImageViewerPreview() {
    ImageViewer(
        imageUrl = "https://images.unsplash.com/photo-1682686581660-3693f0c588d2?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w1NDc3NTB8MXwxfGFsbHwxfHx8fHx8Mnx8MTcwNDU0NTYxOXw&ixlib=rb-4.0.3&q=80&w=1080",
        onTabClose = {}
    )
}
