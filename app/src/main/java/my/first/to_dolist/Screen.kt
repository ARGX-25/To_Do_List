package my.first.to_dolist

sealed class Screen(val route:String) {
    object homeScreen: Screen("home_screen")
    object addScreen: Screen("add_screen")

}