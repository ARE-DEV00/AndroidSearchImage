package kr.co.are.searchimage.presentation.ui.screen.bookmark

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import kr.co.are.searchimage.R
import kr.co.are.searchimage.presentation.ui.component.navigation.NavRoutes
import kr.co.are.searchimage.presentation.ui.screen.base.AppHeaderScreen

@Composable
fun BookmarkScreen(navController: NavController) {
    AppHeaderScreen(
        headerTitle = stringResource(id = R.string.screen_bookmark),
        leftIconImageVector = Icons.Default.ArrowBack,
        onTabLeftIcon = {
            navController.popBackStack()
        }
    ) {

    }
}