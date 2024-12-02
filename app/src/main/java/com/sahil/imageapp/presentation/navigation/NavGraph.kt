package com.sahil.imageapp.presentation.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.sahil.imageapp.presentation.favorites_screen.FavoritesScreen
import com.sahil.imageapp.presentation.full_image_screen.FullImageScreen
import com.sahil.imageapp.presentation.full_image_screen.FullImageViewModel
import com.sahil.imageapp.presentation.home_screen.HomeScreen
import com.sahil.imageapp.presentation.home_screen.HomeViewModel
import com.sahil.imageapp.presentation.profile_screen.ProfileScreen
import com.sahil.imageapp.presentation.search_screen.SearchScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraphSetup(
    navController: NavHostController,
    scrollBehavior: TopAppBarScrollBehavior,


    ) {
    NavHost(
        navController = navController,
        startDestination = Routes.HomeScreen
    ) {
        composable<Routes.HomeScreen> {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen(
                scrollBehavior = scrollBehavior,
                images = viewModel.images,
                onImageClick = { imageId ->
                    navController.navigate(Routes.FullImageScreen(imageId = imageId))
                },
                onSearchClick = { navController.navigate(Routes.SearchScreen) },
                onFABClick = { navController.navigate(Routes.FavoritesScreen) }
            )
        }

        composable<Routes.SearchScreen> {
            SearchScreen(onBackClick = { navController.navigateUp() })
        }

        composable<Routes.FavoritesScreen> {
            FavoritesScreen(onBackClick = { navController.navigateUp() })
        }

        composable<Routes.FullImageScreen> {
            val fullImageViewModel: FullImageViewModel = hiltViewModel()
            FullImageScreen(
                image = fullImageViewModel.image,
                onPhotoGraphNameClick = { profileLink ->
                    navController.navigate(Routes.ProfileScreen(profileLink = profileLink))
                },
                onBackClick = { navController.navigateUp() })
        }

        composable<Routes.ProfileScreen> { backStackEntry ->
            val profileLink = backStackEntry.toRoute<Routes.ProfileScreen>().profileLink
            ProfileScreen(profileLink = profileLink, onBackClick = { navController.navigateUp() })
        }
    }
}