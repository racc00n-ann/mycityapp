package com.example.mycityapp.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.mycityapp.data.DataRepository
import kotlinx.coroutines.launch

import androidx.compose.material3.Scaffold


@Composable
fun MyCityApp() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val categories = DataRepository().loadCategories()
    val selectedCategory = remember { mutableStateOf(categories.firstOrNull()) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(12.dp))
                categories.forEach { category ->
                    NavigationDrawerItem(
                        label = { Text(stringResource(category.nameResourceId)) },
                        selected = category == selectedCategory.value,
                        onClick = {
                            scope.launch { drawerState.close() }
                            selectedCategory.value = category
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
                    }
                )
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = MyCityScreen.CategoryList.name,
                modifier = Modifier.padding(innerPadding)
            ) {
                myCityNavGraph(
                    navController = navController,
                    categories = categories,
                    selectedCategory = selectedCategory
                )
            }
        }
    }
}