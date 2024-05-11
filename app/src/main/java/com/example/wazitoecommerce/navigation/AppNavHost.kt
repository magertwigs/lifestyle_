package com.example.wazitoecommerce.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wazitoecommerce.ui.theme.screens.entry.AddEntriesScreen
import com.example.wazitoecommerce.ui.theme.screens.entry.ViewEntriesScreen
import com.example.wazitoecommerce.ui.theme.screens.home.HomeScreen
import com.example.wazitoecommerce.ui.theme.screens.house.AddHousesScreen
import com.example.wazitoecommerce.ui.theme.screens.house.ViewHousesScreen
import com.example.wazitoecommerce.ui.theme.screens.login.LoginScreen
import com.example.wazitoecommerce.ui.theme.screens.products.AddProductsScreen
import com.example.wazitoecommerce.ui.theme.screens.products.ViewProductsScreen
import com.example.wazitoecommerce.ui.theme.screens.signup.SignupScreen
import com.example.wazitoecommerce.ui.theme.screens.splash.SplashScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController:NavHostController = rememberNavController(),
    startDestination:String = SPLASH_URL
){
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier){
        composable(LOGIN_URL){
            LoginScreen(navController = navController)
        }
        composable(SIGNUP_URL){
            SignupScreen(navController = navController)
        }
        composable(HOME_URL){
            HomeScreen(navController = navController, title = "The Lifestyle Chronicles" , description = "Daily journal for daily events happening in life. A digital and safer way to update private memories.")
        }
        composable(ADD_PRODUCTS_URL){
            AddProductsScreen(navController = navController)
        }
        composable(ADD_HOUSES_URL){
            AddHousesScreen(navController = navController)
        }
        composable(ADD_ENTRY_URL){
            AddEntriesScreen(navController = navController)
        }
        composable(VIEW_PRODUCTS_URL){
            ViewProductsScreen(navController = navController)
        }
        composable(VIEW_HOUSES_URL){
            ViewHousesScreen(navController = navController)
        }
        composable(VIEW_ENTRIES_URL){
            ViewEntriesScreen(navController = navController)
        }
        composable(SPLASH_URL){
            SplashScreen(navController = navController)
        }
    }
}