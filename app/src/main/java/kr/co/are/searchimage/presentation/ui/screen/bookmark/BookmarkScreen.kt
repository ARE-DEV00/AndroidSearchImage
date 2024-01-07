package kr.co.are.searchimage.presentation.ui.screen.bookmark

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import kr.co.are.searchimage.R
import kr.co.are.searchimage.presentation.ui.composable.navigation.NavRoutes
import kr.co.are.searchimage.presentation.ui.composable.photolist.PhotoPagingList
import kr.co.are.searchimage.presentation.ui.screen.base.AppHeaderScreen

@Composable
fun BookmarkScreen(
    navController: NavController,
    viewModel: BookmarkScreenViewModel = hiltViewModel()
) {
    val bookmarkInfoList = viewModel.bookmarkInfoListPager.collectAsLazyPagingItems()

    AppHeaderScreen(
        headerTitle = stringResource(id = R.string.screen_bookmark),
        leftIconImageVector = Icons.Default.ArrowBack,
        modifier = Modifier,
        onTabLeftIcon = {
            navController.popBackStack()
        }
    ) {
        PhotoPagingList(lazyPagingItems = bookmarkInfoList,
            onTabImage = { id ->
                navController.navigate("${NavRoutes.Detail.route}/$id") {
                    launchSingleTop = true
                }
            })
    }
}