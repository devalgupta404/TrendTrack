package com.example.trendtrack.navigation

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.trendtrack.ui.screens.ArticleDetailScreen
import com.example.trendtrack.ui.screens.HomeScreen
import com.example.trendtrack.ui.screens.SplashScreen
import com.example.trendtrack.ui.viewmodel.NewsViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object ArticleDetail : Screen("article_detail/{url}") {
        fun createRoute(url: String) = "article_detail/${URLEncoder.encode(url, StandardCharsets.UTF_8.toString())}"
    }
}
@Composable
private fun ArticleDetailRoute(
    url: String,
    onBackClick: () -> Unit,
    viewModel: NewsViewModel
){
    val articles by viewModel.articles.collectAsState()
    val article = articles.find { it.url == url }
    val context = LocalContext.current
    
    article?.let {
        ArticleDetailScreen(
            article = it,
            onBackClick = onBackClick,
            onShareClick = { url ->
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, url)
                    type = "text/plain"
                }
                val chooser = Intent.createChooser(intent, "Share via")
                chooser.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(chooser)
            }
        )
    }
}
@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Splash.route
){
    val newsViewModel: NewsViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        composable(Screen.Splash.route) {
            SplashScreen(
                onSplashFinished = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Home.route){
            HomeScreen(
                viewModel = newsViewModel,
                onArticleClick = { article ->
                    navController.navigate(Screen.ArticleDetail.createRoute(article.url))
                }
            )
        }
        composable(
            route = Screen.ArticleDetail.route,
            arguments = listOf(
                navArgument("url") { type = NavType.StringType }
            )
        ){ backStackEntry ->
            val url = remember(backStackEntry.arguments?.getString("url")) {
                Uri.decode(backStackEntry.arguments?.getString("url"))
            }
            ArticleDetailRoute(
                url = url ?: "",
                onBackClick = { navController.popBackStack() },
                viewModel = newsViewModel
            )
        }
    }
} 