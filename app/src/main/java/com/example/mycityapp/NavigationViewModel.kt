import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.mycityapp.data.Category
import com.example.mycityapp.ui.components.MyCityScreen
import kotlinx.coroutines.launch

class NavigationViewModel : ViewModel() {
    private var navController: NavHostController? = null

    fun setNavController(controller: NavHostController) {
        navController = controller
    }

    fun navigateToCategoryList() {
        navController?.navigate(MyCityScreen.CategoryList.name) {
            popUpTo(MyCityScreen.CategoryList.name) { inclusive = true }
        }
    }

    fun navigateToRecommendationList(category: Category) {
        viewModelScope.launch {
            navController?.navigate("${MyCityScreen.RecommendationList.name}/${category.nameResourceId}")
        }
    }

    fun navigateToRecommendationDetail(recommendationId: Int) {
        viewModelScope.launch {
            navController?.navigate("${MyCityScreen.RecommendationDetail.name}/${recommendationId}")
        }
    }

    fun onBackPressed() {
        navController?.popBackStack()
    }
}