package kr.co.are.searchimage.presentation.ui.component.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kr.co.are.searchimage.presentation.ui.screen.bookmark.BookmarkScreen
import kr.co.are.searchimage.presentation.ui.screen.detail.DetailScreen
import kr.co.are.searchimage.presentation.ui.screen.search.SearchScreen


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val slideInOut = fadeIn() + slideInHorizontally(
        initialOffsetX = { 300 },
        animationSpec = tween(300)
    )

    NavHost(navController = navController, startDestination = NavRoutes.Search.route,
        enterTransition = { slideInOut },
        popEnterTransition = { slideInOut }) {

        composable(route = NavRoutes.Search.route) {
            SearchScreen(navController = navController)
        }

        composable(route = NavRoutes.Detail.route + "/{${NavRoutes.NavParameter.PHOTO_ID}}") {
            val id = it.arguments?.getString("${NavRoutes.NavParameter.PHOTO_ID}")
            if (id != null) {
                DetailScreen(navController = navController, id = id)
            }
        }

        composable(route = NavRoutes.Bookmark.route) {
            BookmarkScreen(navController = navController)
        }

    }
}