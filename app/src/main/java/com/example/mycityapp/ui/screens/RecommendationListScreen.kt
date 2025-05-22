package com.example.mycityapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel // Импорт для viewModel()
import com.example.mycityapp.data.Recommendation
import com.example.mycityapp.ui.viewmodel.RecommendationDetailViewModel // Импорт вашей ViewModel

@Composable
fun RecommendationDetailScreen(
    modifier: Modifier = Modifier,
    // ViewModel теперь предоставляется по умолчанию
    viewModel: RecommendationDetailViewModel = viewModel()
) {
    // Наблюдаем за LiveData из ViewModel
    val recommendation by viewModel.recommendation.observeAsState(initial = null)

    // Проверяем, что рекомендация не null, прежде чем пытаться получить к ней доступ
    if (recommendation != null) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = stringResource(recommendation!!.nameResourceId), // Используем данные из ViewModel
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Image(
                painter = painterResource(id = recommendation!!.imageResourceId), // Используем данные из ViewModel
                contentDescription = stringResource(recommendation!!.nameResourceId), // Используем данные из ViewModel
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(bottom = 16.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(recommendation!!.descriptionResourceId), // Используем данные из ViewModel
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Justify
            )
        }
    } else {
        // Отобразить сообщение об ошибке или индикатор загрузки
        Text("Recommendation details not found or still loading...", modifier = modifier.padding(16.dp))
    }
}