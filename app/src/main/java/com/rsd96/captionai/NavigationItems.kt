package com.rsd96.captionai

sealed class NavigationItem(val route: String) {

    object Home: NavigationItem("home")
    object GenerateCaption: NavigationItem("caption/{uri}")
}
