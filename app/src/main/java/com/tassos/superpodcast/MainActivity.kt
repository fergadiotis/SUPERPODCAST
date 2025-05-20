package com.tassos.superpodcast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tassos.superpodcast.ui.theme.SUPERPODCASTTheme

class MainActivity : ComponentActivity() {

    private val podcastViewModel: PodcastViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SUPERPODCASTTheme {
                SuperPodcastApp(podcastViewModel)
            }
        }
    }
}

@Composable
fun SuperPodcastApp(viewModel: PodcastViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "podcast_list"
    ) {
        composable("podcast_list") {
            PodcastListScreen(
                viewModel = viewModel,
                onPodcastClick = { podcast ->
                    navController.navigate("episode_list/${podcast.title}")
                }
            )
        }

        composable(
            "episode_list/{podcastTitle}",
            arguments = listOf(navArgument("podcastTitle") { type = NavType.StringType })
        ) { backStackEntry ->
            val podcastTitle = backStackEntry.arguments?.getString("podcastTitle") ?: ""
            val podcast = viewModel.podcasts.value.find { it.title == podcastTitle }

            if (podcast != null) {
                EpisodeListScreen(
                    podcast = podcast,
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SuperPodcastAppPreview() {
    SUPERPODCASTTheme {
        WelcomeScreen("SuperPodcast")
    }
}

@Composable
fun WelcomeScreen(appName: String) {
    // Keep this for preview only
}