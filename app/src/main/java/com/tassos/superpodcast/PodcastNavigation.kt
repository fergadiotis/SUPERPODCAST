package com.tassos.superpodcast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import androidx.navigation.NavController

class PodcastNavigation : ViewModel() {

    private var navController: NavController? = null

    private val _currentDestination = MutableStateFlow("podcast_list")
    val currentDestination: StateFlow<String> = _currentDestination.asStateFlow()

    private val _navigationHistory = MutableStateFlow<List<String>>(emptyList())
    val navigationHistory: StateFlow<List<String>> = _navigationHistory.asStateFlow()

    fun setNavController(controller: NavController) {
        navController = controller
    }

    fun navigateTo(destination: String) {
        viewModelScope.launch {
            navController?.navigate(destination)
            _currentDestination.value = destination

            val history = _navigationHistory.value.toMutableList()
            history.add(destination)
            _navigationHistory.value = history
        }
    }

    fun navigateBack() {
        viewModelScope.launch {
            val canGoBack = navController?.popBackStack() ?: false
            if (canGoBack) {
                val history = _navigationHistory.value.toMutableList()
                if (history.isNotEmpty()) {
                    history.removeLastOrNull()
                    _navigationHistory.value = history
                    _currentDestination.value = history.lastOrNull() ?: "podcast_list"
                }
            }
        }
    }

    fun navigateToEpisodeList(podcastId: String) {
        navigateTo("episode_list/$podcastId")
    }

    fun navigateToPlayer(episodeId: String) {
        navigateTo("player/$episodeId")
    }

    fun navigateToSearch() {
        navigateTo("search")
    }

    fun navigateToSettings() {
        navigateTo("settings")
    }
}