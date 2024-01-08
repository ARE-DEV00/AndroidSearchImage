package kr.co.are.searchimage.presentation.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kr.co.are.searchimage.R
import kr.co.are.searchimage.presentation.ui.composable.imageviewer.ImageViewer
import kr.co.are.searchimage.presentation.ui.composable.photodetail.PhotoDetail
import kr.co.are.searchimage.presentation.ui.screen.base.AppHeaderScreen
import kr.co.are.searchimage.presentation.ui.theme.Gray50
import kr.co.are.searchimage.presentation.ui.theme.White
import kr.co.are.searchimage.presentation.utils.DateUtil
import timber.log.Timber

@Composable
fun DetailScreen(
    navController: NavController,
    viewModel: DetailScreenViewModel = hiltViewModel(),
    id: String
) {
    Timber.d("#### DetailScreen-ID:${id}")

    viewModel.getPhotoDetailInfo(id)
    val photoDetailEntity = viewModel.loadPhotoDetailEntity.observeAsState().value
    val isBookmark = viewModel.isBookmark.observeAsState().value ?: false
    val isImageViewer by viewModel.isImageViewer

    if (!isImageViewer) {
        AppHeaderScreen(
            headerTitle = stringResource(id = R.string.screen_detail),
            leftIconImageVector = Icons.Default.ArrowBack,
            modifier = Modifier,
            onTabLeftIcon = {
                navController.popBackStack()
            }
        ) {
            if (photoDetailEntity != null) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Gray50)
                ) {
                    Box(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
                        PhotoDetail(
                            id = photoDetailEntity.imageInfo.id,
                            author = photoDetailEntity.imageInfo.author,
                            width = photoDetailEntity.imageInfo.width,
                            height = photoDetailEntity.imageInfo.height,
                            createdAt = DateUtil.convertUtcToLocal(photoDetailEntity.imageInfo.createdAt),
                            imageUrl = photoDetailEntity.imageUrl.regular,
                            onTabImage = {
                                viewModel.setImageViewerVisibility(true)
                            }
                        )
                    }


                    FloatingActionButton(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(bottom = 50.dp, end = 20.dp),
                        onClick = {
                            if (isBookmark) {
                                viewModel.deleteBookmarkInfo(id = id)
                            } else {
                                viewModel.addBookmarkInfo(id = id)
                            }
                        },
                        shape = CircleShape,
                        containerColor = White
                    ) {
                        if (isBookmark) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = null,
                                tint = Color.Red
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.FavoriteBorder,
                                contentDescription = null,
                                tint = Color.Red
                            )
                        }
                    }
                }
            }
        }
    } else {
        if (photoDetailEntity?.imageUrl?.regular?.isNotEmpty() == true) {
            ImageViewer(imageUrl = photoDetailEntity.imageUrl.regular) {
                viewModel.setImageViewerVisibility(false)
            }
        }
    }


}