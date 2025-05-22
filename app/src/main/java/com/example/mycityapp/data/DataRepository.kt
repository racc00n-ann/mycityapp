package com.example.mycityapp.data

import com.example.mycityapp.R

class DataRepository {
    fun loadCategories(): List<Category> {
        return listOf(
            Category(R.string.category_cafe),
            Category(R.string.category_shopping),
            Category(R.string.category_parks),
            Category(R.string.category_fitness)
        )
    }

    fun loadRecommendations(categoryName: String): List<Recommendation> {
        return when (categoryName) {
            "Кафе и рестораны" -> loadCafes()
            "Торговые центры" -> loadShoppingCenters()
            "Парки" -> loadParks()
            "Фитнес клубы" -> loadFitnessClubs()
            else -> emptyList()
        }
    }

    fun loadAllRecommendations(): List<Recommendation> {
        return loadCafes() + loadShoppingCenters() + loadParks() + loadFitnessClubs()
    }

    private fun loadCafes(): List<Recommendation> {
        return listOf(
            Recommendation(
                R.string.cafe_name_1,
                R.string.cafe_description_1,
                R.drawable.cafe_1,
                "Кафе и рестораны"
            ),
            Recommendation(
                R.string.cafe_name_2,
                R.string.cafe_description_2,
                R.drawable.cafe_2,
                "Кафе и рестораны"
            ),
            Recommendation(
                R.string.cafe_name_3,
                R.string.cafe_description_3,
                R.drawable.cafe_3,
                "Кафе и рестораны"
            ),
            Recommendation(
                R.string.cafe_name_4,
                R.string.cafe_description_4,
                R.drawable.cafe_4,
                "Кафе и рестораны"
            )
        )
    }

    private fun loadShoppingCenters(): List<Recommendation> {
        return listOf(
            Recommendation(
                R.string.shopping_name_1,
                R.string.shopping_description_1,
                R.drawable.shopping_1,
                "Торговые центры"
            ),
            Recommendation(
                R.string.shopping_name_2,
                R.string.shopping_description_2,
                R.drawable.shopping_2,
                "Торговые центры"
            ),
            Recommendation(
                R.string.shopping_name_3,
                R.string.shopping_description_3,
                R.drawable.shopping_3,
                "Торговые центры"
            ),
            Recommendation(
                R.string.shopping_name_4,
                R.string.shopping_description_4,
                R.drawable.shopping_4,
                "Торговые центры"
            )
        )
    }

    private fun loadParks(): List<Recommendation> {
        return listOf(
            Recommendation(
                R.string.park_name_1,
                R.string.park_description_1,
                R.drawable.park_1,
                "Парки"
            ),
            Recommendation(
                R.string.park_name_2,
                R.string.park_description_2,
                R.drawable.park_2,
                "Парки"
            ),
            Recommendation(
                R.string.park_name_3,
                R.string.park_description_3,
                R.drawable.park_3,
                "Парки"
            ),
            Recommendation(
                R.string.park_name_4,
                R.string.park_description_4,
                R.drawable.park_4,
                "Парки"
            )
        )
    }

    private fun loadFitnessClubs(): List<Recommendation> {
        return listOf(
            Recommendation(
                R.string.fitness_name_1,
                R.string.fitness_description_1,
                R.drawable.fitness_1,
                "Фитнес клубы"
            ),
            Recommendation(
                R.string.fitness_name_2,
                R.string.fitness_description_2,
                R.drawable.fitness_2,
                "Фитнес клубы"
            ),
            Recommendation(
                R.string.fitness_name_3,
                R.string.fitness_description_3,
                R.drawable.fitness_3,
                "Фитнес клубы"
            ),
            Recommendation(
                R.string.fitness_name_4,
                R.string.fitness_description_4,
                R.drawable.fitness_4,
                "Фитнес клубы"
            )
        )
    }




}

