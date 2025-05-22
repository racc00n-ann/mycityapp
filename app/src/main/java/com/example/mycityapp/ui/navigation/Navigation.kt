package com.example.mycityapp.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import androidx.lifecycle.viewmodel.compose.viewModel // Import viewModel helper
import com.example.mycityapp.R
import com.example.mycityapp.data.Category
import com.example.mycityapp.data.DataRepository
import com.example.mycityapp.ui.screens.CategoryListScreen
import com.example.mycityapp.ui.screens.RecommendationDetailScreen
import com.example.mycityapp.ui.screens.RecommendationListScreen
import com.example.mycityapp.ui.viewmodel.CategoryListViewModel
import com.example.mycityapp.ui.viewmodel.RecommendationDetailViewModel
import com.example.mycityapp.ui.viewmodel.RecommendationListViewModel


fun NavGraphBuilder.myCityNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    composable(route = MyCityScreen.CategoryList.name) {
        val categoryListViewModel: CategoryListViewModel = viewModel()
        CategoryListScreen(
            onCategoryClick = { category ->
                    navController.navigate("${MyCityScreen.RecommendationList.name}/${category.nameResourceId}")
            }
        )
    }

    composable(route = "${MyCityScreen.RecommendationList.name}/{categoryId}") { backStackEntry ->
        val categoryId = backStackEntry.arguments?.getString("categoryId")?.toIntOrNull()

        if (categoryId != null) {
            val recommendationListViewModel: RecommendationListViewModel = viewModel() // Получаем ViewModel
            recommendationListViewModel.loadRecommendationsForCategory(categoryId) // Загружаем данные

            RecommendationListScreen(
                onRecommendationClick = { recommendation ->
                    navController.navigate("${MyCityScreen.RecommendationDetail.name}/${recommendation.nameResourceId}")
                }
            )
        } else {
            Text("Error: Category ID not found")
        }
    }

    composable(route = "${MyCityScreen.RecommendationDetail.name}/{recommendationId}") { backStackEntry ->
        val recommendationId = backStackEntry.arguments?.getString("recommendationId")?.toIntOrNull()

        if (recommendationId != null) {
            val recommendationDetailViewModel: RecommendationDetailViewModel = viewModel() // Получаем ViewModel
            recommendationDetailViewModel.loadRecommendation(recommendationId) // Загружаем данные

            RecommendationDetailScreen(
            )
        } else {
            Text("Error: Recommendation ID not found")
        }
    }
}