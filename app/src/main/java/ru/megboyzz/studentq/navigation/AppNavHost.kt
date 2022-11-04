package ru.megboyzz.studentq.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.megboyzz.studentq.screens.*

sealed class AppNavRoute(val route: String){
    object Auth: AppNavRoute("auth_screen")
    object Queues: AppNavRoute("all_queues_screen")
    object Queue: AppNavRoute("in_queue_screen")
    object QueueCreate: AppNavRoute("queue_creation_screen")
    object QueueInvitation: AppNavRoute("queue_invitation_screen")
}

@Composable
fun AppNavHost(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppNavRoute.Auth.route
    ){
        composable(AppNavRoute.Auth.route)              { Auth(navController) }
        composable(AppNavRoute.Queues.route)            { Queues(navController) }
        composable(AppNavRoute.Queue.route)             { Queue(navController) }
        composable(AppNavRoute.QueueCreate.route)       { QueueCreate(navController) }
        composable(AppNavRoute.QueueInvitation.route)   { QueueInvitation(navController) }
    }
}