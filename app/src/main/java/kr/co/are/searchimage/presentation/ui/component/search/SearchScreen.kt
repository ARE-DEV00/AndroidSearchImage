package kr.co.are.searchimage.presentation.ui.component.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import kr.co.are.searchimage.R
import kr.co.are.searchimage.presentation.ui.component.navigation.NavRoutes
import kr.co.are.searchimage.presentation.ui.component.photolist.PhotoList
import kr.co.are.searchimage.presentation.ui.component.photolist.PhotoPagingList
import kr.co.are.searchimage.presentation.ui.component.sample.ButtonTest


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchScreenViewModel = hiltViewModel()
) {
    //var page = 1
    //viewModel.getPhotoInfoList(page++)
    viewModel.getPagingPhotoInfoList()
    val photoListPager = viewModel.photoListPager.collectAsLazyPagingItems()


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.screen_search))
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(NavRoutes.Bookmark.route)
                    }) {
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                    }

                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxHeight()
                .fillMaxWidth()
        ) {

            PhotoPagingList(lazyPagingItems = photoListPager)

            Button(onClick = {
                navController.navigate(NavRoutes.Detail.route)
            }) {
                Text(text = stringResource(id = R.string.screen_search))
            }
        }
    }

}