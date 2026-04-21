package com.example.todolist.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todolist.ui.screens.TaskScreen
import com.example.todolist.ui.screens.TaskDetailScreen
import com.example.todolist.viewmodel.TaskViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationGraph(viewModel: TaskViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "task_list") {
        composable("task_list") {
            TaskScreen(navController = navController, viewModel = viewModel)
        }

        // Route pour les détails de la tâche
        composable(
            route = "task_detail/{taskId}",
            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getInt("taskId") ?: 0
            TaskDetailScreen(navController = navController, taskId = taskId, viewModel = viewModel)
        }
    }
}
