package kr.co.are.searchimage.presentation.ui.component.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kr.co.are.searchimage.presentation.ui.component.bookmark.BookmarkScreen
import kr.co.are.searchimage.presentation.ui.component.detail.DetailScreen
import kr.co.are.searchimage.presentation.ui.component.search.SearchScreen


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRoutes.Search.route) {

        composable(NavRoutes.Search.route) {
            SearchScreen(navController = navController)
        }

        composable(NavRoutes.Detail.route) {
            DetailScreen(navController = navController)
        }

        composable(NavRoutes.Bookmark.route) {
            BookmarkScreen(navController = navController)
        }

    }
}