package com.example.mycityapp.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.mycityapp.ui.viewmodel.MyCityAppViewModel
import kotlinx.coroutines.launch

@Composable
fun MyCityApp(
    viewModel: MyCityAppViewModel = viewModel()
) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val categories by viewModel.categories.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(12.dp))
                categories.forEach { category ->
                    NavigationDrawerItem(
                        label = { Text(stringResource(category.nameResourceId)) },
                        selected = category == selectedCategory,
                        onClick = {
                            scope.launch { drawerState.close() }
                            viewModel.setSelectedCategory(category)
                            navController.navigate("${MyCityScreen.RecommendationList.name}/${category.nameResourceId}")
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                com.example.mycityapp.ui.navigation.TopAppBar(
                    onNavigationIconClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    },onTitleClick = { navController.navigate(MyCityScreen.CategoryList.name) }

                )
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = MyCityScreen.CategoryList.name,
                modifier = Modifier.padding(innerPadding)
            ) {
                myCityNavGraph(
                    navController = navController
                )
            }
        }
    }
}