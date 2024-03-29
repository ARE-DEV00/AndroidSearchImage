package kr.co.are.searchimage.presentation.ui.composable.navigation

sealed class NavRoutes(val route: String) {
    data object Search : NavRoutes("search")
    data object Detail : NavRoutes("detail")
    data object Bookmark : NavRoutes("bookmark")
    enum class NavParameter {
        PHOTO_ID,
    }
}


