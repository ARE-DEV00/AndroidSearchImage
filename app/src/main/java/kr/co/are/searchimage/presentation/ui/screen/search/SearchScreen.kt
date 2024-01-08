package kr.co.are.searchimage.presentation.ui.screen.search

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.co.are.searchimage.R
import kr.co.are.searchimage.presentation.ui.composable.navigation.NavRoutes
import kr.co.are.searchimage.presentation.ui.composable.photolist.PhotoPagingList
import kr.co.are.searchimage.presentation.ui.composable.searchtextfield.SearchTextField
import kr.co.are.searchimage.presentation.ui.screen.base.AppHeaderScreen
import kr.co.are.searchimage.presentation.ui.theme.Gray50
import timber.log.Timber

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchScreenViewModel = hiltViewModel()
) {
    val photoListPager = viewModel.photoListPager.collectAsLazyPagingItems()
    val searchPhotoListPager = viewModel.searchPhotoListPager.collectAsLazyPagingItems()
    val searchText = viewModel.searchText
    val focusManager = LocalFocusManager.current

    AppHeaderScreen(
        headerTitle = stringResource(id = R.string.screen_search),
        rightIconImageVector = Icons.Default.Favorite,
        rightIconImageVectorColor = Color.Red,
        modifier = Modifier.noRippleClickable {
            focusManager.clearFocus()
        },
        onTabRightIcon = {
            navController.navigate(NavRoutes.Bookmark.route) {
                launchSingleTop = true
            }
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
                    SearchTextField(
                        text = searchText.value,
                        hint = "Search",
                        onTextChanged = {
                            viewModel.search(it)
                        })
                }
                if (searchText.value.isEmpty()) {
                    PhotoPagingList(lazyPagingItems = photoListPager,
                        onTabImage = { id ->
                            focusManager.clearFocus()

                            navController.navigate("${NavRoutes.Detail.route}/$id") {
                                launchSingleTop = true
                            }
                        })
                } else {
                    PhotoPagingList(lazyPagingItems = searchPhotoListPager,
                        onTabImage = { id ->
                            focusManager.clearFocus()
                            navController.navigate("${NavRoutes.Detail.route}/$id") {
                                launchSingleTop = true
                            }
                        })
                }

            }
        }
    }
    DoubleBackPressHandler()
}

@Composable
fun DoubleBackPressHandler(enabled: Boolean = true) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val isBackPressed = remember { mutableStateOf(false) }
    BackHandler(enabled) {
        if (isBackPressed.value) {
            (context as Activity).finish()
            return@BackHandler
        }
        isBackPressed.value = true

        Toast.makeText(
            context,
            context.getString(R.string.message_toast_exit_app),
            Toast.LENGTH_SHORT
        ).show()
        scope.launch {
            delay(2000L)
            Timber.d("#### TEST ")
            isBackPressed.value = false
        }
    }
}

inline fun Modifier.noRippleClickable(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    crossinline onClick: () -> Unit
): Modifier = composed {
    clickable(
        enabled = enabled,
        indication = null,
        onClickLabel = onClickLabel,
        role = role,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}