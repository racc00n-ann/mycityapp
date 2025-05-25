package com.example.mycityapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel // Импорт для viewModel()
import com.example.mycityapp.data.Recommendation
import com.example.mycityapp.ui.viewmodel.RecommendationListViewModel // Импорт вашей ViewModel

@Composable
fun RecommendationListScreen(
    onRecommendationClick: (Recommendation) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RecommendationListViewModel = viewModel()
) {
    val recommendations by viewModel.recommendations.collectAsState()
    val categoryName by viewModel.categoryName.collectAsState()

    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = categoryName,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        LazyColumn(
            contentPadding = PaddingValues(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(recommendations) { recommendation ->
                RecommendationItem(
                    recommendation = recommendation,
                    onRecommendationClick = onRecommendationClick
                )
            }
        }
    }
}

@Composable
fun RecommendationItem(
    recommendation: Recommendation,
    onRecommendationClick: (Recommendation) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier
        .fillMaxWidth()
        .clickable { onRecommendationClick(recommendation) }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = stringResource(recommendation.nameResourceId),
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
            Image(
                painter = painterResource(id = recommendation.imageResourceId),
                contentDescription = stringResource(recommendation.nameResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(bottom = 8.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(recommendation.descriptionResourceId),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify
            )
        }
    }
}