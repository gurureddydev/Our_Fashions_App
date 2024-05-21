package com.guru.ourfashionsapp.navigation

import com.guru.ourfashionsapp.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int,
    val icon_focused: Int
) {

    // for home
    object Home: BottomBarScreen(
        route = "home",
        title = "Home",
        icon = R.drawable.ic_home,
        icon_focused = R.drawable.ic_home
    )

    // for report
    object Cart: BottomBarScreen(
        route = "cart",
        title = "Cart",
        icon = R.drawable.ic_cart,
        icon_focused = R.drawable.ic_cart
    )

    // for report
    object Notification: BottomBarScreen(
        route = "notification",
        title = "Notification",
        icon = R.drawable.ic_notifications_24,
        icon_focused = R.drawable.ic_notifications_24
    )

    // for report
    object Profile: BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = R.drawable.ic_person_24,
        icon_focused = R.drawable.ic_person_24
    )

}
