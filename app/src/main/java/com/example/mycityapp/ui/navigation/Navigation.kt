package com.example.mycityapp.ui.components

import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.mycityapp.R
import com.example.mycityapp.data.Category
import com.example.mycityapp.data.DataRepository
import com.example.mycityapp.ui.screens.CategoryListScreen
import com.example.mycityapp.ui.screens.RecommendationDetailScreen
import com.example.mycityapp.ui.screens.RecommendationListScreen


fun NavGraphBuilder.myCityNavGraph(
    navController: NavHostController,
    categories: List<Category>,
    selectedCategory: androidx.compose.runtime.MutableState<Category?>,
    modifier: Modifier = Modifier
) {
    composable(route = MyCityScreen.CategoryList.name) {
        CategoryListScreen(
            categories = categories,
            onCategoryClick = { category ->
                selectedCategory.value = category
                navController.navigate("${MyCityScreen.RecommendationList.name}/${category.nameResourceId}")
            }
        )
    }

    composable(route = "${MyCityScreen.RecommendationList.name}/{categoryId}") { backStackEntry ->
        val categoryId = backStackEntry.arguments?.getString("categoryId")?.toIntOrNull()
        val categoryName = if (categoryId != null) {
            stringResource(categoryId)
        } else {
            stringResource(R.string.recommendations)
        }
        val recommendations = DataRepository().loadRecommendations(categoryName)

        RecommendationListScreen(
            categoryName = categoryName,
            recommendations = recommendations,
            onRecommendationClick = { recommendation ->
                navController.navigate("${MyCityScreen.RecommendationDetail.name}/${recommendation.nameResourceId}")
            }
        )
    }

    composable(route = "${MyCityScreen.RecommendationDetail.name}/{recommendationId}") { backStackEntry ->
        val recommendationId = backStackEntry.arguments?.getString("recommendationId")?.toIntOrNull()
        val recommendation = if (recommendationId != null) {
            DataRepository().loadAllRecommendations().find { rec -> rec.nameResourceId == recommendationId }
        } else {
            null
        }

        if (recommendation != null) {
            RecommendationDetailScreen(recommendation = recommendation)
        } else {
            Text("Recommendation not found")
        }
    }
}