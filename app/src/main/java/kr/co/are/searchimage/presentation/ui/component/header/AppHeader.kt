package kr.co.are.searchimage.presentation.ui.component.header

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.are.searchimage.presentation.ui.theme.Typography


@Composable
fun AppHeader(
    headerTitle: String,
    leftIconImageVector: ImageVector? = null,
    leftIconImageVectorColor: Color = Color.Black,
    rightIconImageVector: ImageVector? = null,
    rightIconImageVectorColor: Color = Color.Black,
    onTabRightIcon: (() -> Unit)? = null,
    onTabLeftIcon: (() -> Unit)? = null,
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
    ) {

        if (leftIconImageVector != null) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
            ) {
                IconButton(
                    onClick = {
                        onTabLeftIcon?.invoke()
                    },
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = leftIconImageVector,
                        contentDescription = null,
                        tint = leftIconImageVectorColor
                    )
                }
            }
        }


        Text(
            text = headerTitle,
            modifier = Modifier
                .align(Alignment.Center),
            textAlign = TextAlign.Center,
            style = Typography.titleLarge
        )

        if (rightIconImageVector != null) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd),
            ) {
                IconButton(
                    onClick = {
                        onTabRightIcon?.invoke()
                    },
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Icon(
                        imageVector = rightIconImageVector,
                        contentDescription = null,
                        tint = rightIconImageVectorColor
                    )
                }
            }
        }

    }


}

@Preview(showBackground = true)
@Composable
fun AppHeaderPreview() {
    AppHeader(
        headerTitle = "TEST",
        leftIconImageVector = Icons.Default.ArrowBack,
        leftIconImageVectorColor = Color.Black,
        rightIconImageVector = Icons.Default.Favorite,
        rightIconImageVectorColor = Color.Red
    )
}
