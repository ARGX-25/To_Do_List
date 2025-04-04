package my.first.to_dolist

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun Navigation( viewModel: todoViewModel = viewModel(),
                navController: NavHostController = rememberNavController()){
    NavHost(navController = navController,
        startDestination = Screen.homeScreen.route
         ){
            composable(Screen.homeScreen.route){
                HomeView(navController, viewModel)
            }
            composable(Screen.addScreen.route){
                AddEditDetailView(id = 0L, viewModel = viewModel, navController = navController )
            }

    }
}