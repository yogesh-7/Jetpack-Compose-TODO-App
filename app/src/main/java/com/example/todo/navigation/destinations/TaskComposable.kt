 package com.example.todo.navigation.destinations

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import com.example.todo.ui.screens.tasks.TaskScreen
import com.example.todo.ui.viewmodels.SharedViewModel
import com.example.todo.util.Action
import com.example.todo.util.Constants.TASK_ARGUMENT_KEY
import com.example.todo.util.Constants.TASK_SCREEN
import androidx.compose.runtime.getValue
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument


 @ExperimentalAnimationApi
fun NavGraphBuilder.taskComposable(
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit
){
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY){
            type = NavType.IntType
        })
    ){navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)
        Log.d("TaskComposable","taskId ::$taskId")
        LaunchedEffect(key1 = taskId) {
            sharedViewModel.getSelectedTask(taskId = taskId)
        }

        val selectedTask by sharedViewModel.selectedTask.collectAsState()

        LaunchedEffect(key1 = selectedTask) {
            if(selectedTask!=null || taskId==-1){
                sharedViewModel.updateTaskFields(selectedTask = selectedTask)
            }

        }

        TaskScreen(
            selectedTask = selectedTask,
            sharedViewModel = sharedViewModel,
            navigateToListScreen = navigateToListScreen
        )
    }
}