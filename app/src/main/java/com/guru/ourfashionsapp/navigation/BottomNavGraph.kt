package com.guru.ourfashionsapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.guru.ourfashionsapp.screens.FashionAppUI
import com.guru.ourfashionsapp.screens.ProductDetailScreen
import com.guru.ourfashionsapp.screens.MyCartScreen
import com.guru.ourfashionsapp.R

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            FashionAppUI(navController)
        }
        composable(route = BottomBarScreen.Cart.route) {
        }
        composable(route = BottomBarScreen.Notification.route) {
        }
        composable(route = BottomBarScreen.Profile.route) {
        }
        // Add composable for ProductDetailScreen with ID
        composable(route = "productDetailScreen/{id}/{name}/{price}/{des}/{imageResourceId}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val price = backStackEntry.arguments?.getString("price") ?: ""
            val des = backStackEntry.arguments?.getString("des") ?: ""
            val imageResourceId =
                backStackEntry.arguments?.getString("imageResourceId")?.toIntOrNull() ?: 0
            val image = painterResource(id = imageResourceId)
            ProductDetailScreen(
                id = id,
                name = name,
                price = price,
                des = des,
                image = image,
                navController = navController
            )
        }
        // Add composable for MyCartScreen
        composable(route = "myCartScreen/{id}/{name}/{des}/{price}/{imageResourceId}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val des = backStackEntry.arguments?.getString("des") ?: ""
            val price = backStackEntry.arguments?.getString("price") ?: ""
            val imageResourceId = backStackEntry.arguments?.getString("imageResourceId")?.toIntOrNull() ?: R.drawable.img_02
            val image = painterResource(id = imageResourceId)
            MyCartScreen(
                name = name,
                des = des,
                price = price,
                image = image
            )
        }
    }
}
