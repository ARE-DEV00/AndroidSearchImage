package kr.co.are.searchimage.presentation.ui.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import kr.co.are.searchimage.R
import kr.co.are.searchimage.presentation.ui.component.navigation.NavRoutes
import kr.co.are.searchimage.presentation.ui.component.photolist.PhotoPagingList
import kr.co.are.searchimage.presentation.ui.screen.base.AppHeaderScreen
import kr.co.are.searchimage.presentation.ui.theme.Gray50
import kr.co.are.searchimage.presentation.ui.theme.Typography


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchScreenViewModel = hiltViewModel()
) {
    val photoListPager = viewModel.photoListPager.collectAsLazyPagingItems()

    AppHeaderScreen(
        headerTitle = stringResource(id = R.string.screen_search),
        rightIconImageVector = Icons.Default.Favorite,
        rightIconImageVectorColor = Color.Red,
        onTabRightIcon = {
            navController.navigate(NavRoutes.Bookmark.route)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Gray50)
        ) {

            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = "검색창", style = Typography.titleLarge)
                }
                PhotoPagingList(lazyPagingItems = photoListPager,
                    onTabImage = { id ->
                        navController.navigate("${NavRoutes.Detail.route}/$id")
                    })
            }
        }
    }
}