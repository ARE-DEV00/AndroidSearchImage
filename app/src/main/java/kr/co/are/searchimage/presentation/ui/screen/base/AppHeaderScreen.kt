package kr.co.are.searchimage.presentation.ui.screen.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import kr.co.are.searchimage.presentation.ui.component.header.AppHeader
import kr.co.are.searchimage.presentation.ui.theme.Gray300

@Composable
fun AppHeaderScreen(
    headerTitle: String,
    rightIconImageVector: ImageVector? = null,
    rightIconImageVectorColor: Color = Color.Black,
    leftIconImageVector: ImageVector? = null,
    leftIconImageVectorColor: Color = Color.Black,
    onTabRightIcon: (() -> Unit)? = null,
    onTabLeftIcon: (() -> Unit)? = null,
    composable: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AppHeader(
            headerTitle = headerTitle,
            leftIconImageVector = leftIconImageVector,
            leftIconImageVectorColor = leftIconImageVectorColor,
            rightIconImageVector = rightIconImageVector,
            rightIconImageVectorColor = rightIconImageVectorColor,
            onTabRightIcon = onTabRightIcon,
            onTabLeftIcon = onTabLeftIcon
        )
        Divider(
            color = Gray300,
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
        )

        composable()

    }

}