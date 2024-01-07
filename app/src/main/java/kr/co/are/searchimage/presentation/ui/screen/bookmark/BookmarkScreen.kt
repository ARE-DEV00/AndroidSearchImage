package kr.co.are.searchimage.presentation.ui.screen.bookmark

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import kr.co.are.searchimage.R
import kr.co.are.searchimage.presentation.ui.screen.base.AppHeaderScreen

@Composable
fun BookmarkScreen(navController: NavController) {
    AppHeaderScreen(
        headerTitle = stringResource(id = R.string.screen_bookmark),
        leftIconImageVector = Icons.Default.ArrowBack,
        modifier = Modifier,
        onTabLeftIcon = {
            navController.popBackStack()
        }
    ) {

    }
}