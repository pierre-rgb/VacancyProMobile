package com.project.vacancypromobile

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.project.vacancypromobile.ui.screens.Screen
import com.project.vacancypromobile.viewModel.LoginViewModel
import com.project.vacancypromobile.viewModel.MainViewModel
import com.project.vacancypromobile.viewModel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()
    private val registerViewModel: RegisterViewModel by viewModels()
    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            navController = rememberNavController()
            SetupNavGraph(navController = navController, loginViewModel, registerViewModel)
            mainViewModel.loadUser()
            val user = mainViewModel.getCurrentUser()
            if (user == null) navController.navigate(route = Screen.Login.route) else navController.navigate(route = Screen.Home.route)
        }
    }
}

/*
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VacancypromobileTheme {
        Greeting("Android")
    }
}*/
