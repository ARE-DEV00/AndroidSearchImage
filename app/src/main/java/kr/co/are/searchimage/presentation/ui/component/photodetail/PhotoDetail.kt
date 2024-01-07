package kr.co.are.searchimage.presentation.ui.component.photodetail


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kr.co.are.searchimage.R
import kr.co.are.searchimage.presentation.ui.theme.Gray300
import kr.co.are.searchimage.presentation.ui.theme.Gray500
import kr.co.are.searchimage.presentation.ui.theme.Typography
import kr.co.are.searchimage.presentation.ui.theme.White


@Composable
fun PhotoDetail(
    id: String,
    author: String,
    width: Int,
    height: Int,
    createdAt: String,
    imageUrl: String,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {

        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .padding(top = 20.dp)
                .clip(RoundedCornerShape(15.dp)),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .padding(top = 20.dp, bottom = 100.dp)
                .clip(RoundedCornerShape(10.dp))
        ) {
            Column(
                modifier = Modifier
                    .background(White)


            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Text(
                            style = Typography.titleMedium,
                            modifier = Modifier.align(Alignment.CenterStart),
                            text = stringResource(id = R.string.photo_detail_id)
                        )

                        Text(
                            style = Typography.bodyLarge,
                            modifier = Modifier.align(Alignment.CenterEnd),
                            text = id,
                            color = Gray500
                        )
                    }
                    Divider(
                        color = Gray300,
                        thickness = 0.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
                Column(modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Text(
                            style = Typography.titleMedium,
                            modifier = Modifier.align(Alignment.CenterStart),
                            text = stringResource(id = R.string.photo_detail_author)
                        )

                        Text(
                            style = Typography.bodyLarge,
                            modifier = Modifier.align(Alignment.CenterEnd),
                            text = author,
                            color = Gray500
                        )
                    }
                    Divider(
                        color = Gray300,
                        thickness = 0.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
                Column(modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Text(
                            style = Typography.titleMedium,
                            modifier = Modifier.align(Alignment.CenterStart),
                            text = stringResource(id = R.string.photo_detail_size)
                        )

                        Text(
                            style = Typography.bodyLarge,
                            modifier = Modifier.align(Alignment.CenterEnd),
                            text = "${width} x ${height}",
                            color = Gray500
                        )
                    }
                    Divider(
                        color = Gray300,
                        thickness = 0.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
                Column(modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Text(
                            style = Typography.titleMedium,
                            modifier = Modifier.align(Alignment.CenterStart),
                            text = stringResource(id = R.string.photo_detail_created_at)
                        )

                        Text(
                            style = Typography.bodyLarge,
                            modifier = Modifier.align(Alignment.CenterEnd),
                            text = createdAt,
                            color = Gray500
                        )
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PhotoDetail(
        id = "id",
        author = "author",
        width = 1000,
        height = 4000,
        createdAt = "",
        imageUrl = "https://images.unsplash.com/photo-1682686581660-3693f0c588d2?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w1NDc3NTB8MXwxfGFsbHwxfHx8fHx8Mnx8MTcwNDU0NTYxOXw&ixlib=rb-4.0.3&q=80&w=1080",
    )
}
