package com.example.ctf240521.ui.screens

import androidx.annotation.StringRes
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.ctf240521.R
import com.example.ctf240521.ui.component.*
import com.example.ctf240521.ui.screens.post.PostViewModel
import com.example.ctf240521.viewmodel.RegisterViewModel
import kotlinx.coroutines.launch

sealed class BottomNavigationScreens(
    val route:String,
    @StringRes val resourceId:Int,
    val icon: Int
){
    object Home:BottomNavigationScreens("Home",R.string.home_screen_route, R.drawable.home)
    object Party:BottomNavigationScreens("Party",R.string.party_screen_route, R.drawable.party)
    object Add:BottomNavigationScreens("Add",R.string.add_screen_route, R.drawable.add)
    object Trading:BottomNavigationScreens("Trading",R.string.trading_screen_route, R.drawable.trading)
    object Profile:BottomNavigationScreens("Profile",R.string.profile_screen_route, R.drawable.profile)
    object Search:BottomNavigationScreens("Search",R.string.search_screen_route, R.drawable.search)
    object MyProfile:BottomNavigationScreens("MyProfile",R.string.myprofile_screen_route, R.drawable.profile)
    object Calculation:BottomNavigationScreens("Calculation",R.string.calculation_screen_route, R.drawable.calculator)
    object TipsTricks:BottomNavigationScreens("TipsTricks",R.string.tipstricks_screen_route, R.drawable.tipsandtrick)
    object Dictionary:BottomNavigationScreens("Dictionary",R.string.dictionary_screen_route, R.drawable.dictionary)
    object Notification:BottomNavigationScreens("Notification",R.string.notification_screen_route, R.drawable.notification)
    object Sale:BottomNavigationScreens("Section",R.string.sale,R.drawable.sale)
}


@Composable
fun MainScreen(){
    val navController = rememberNavController()
    val scaffoldState:ScaffoldState= rememberScaffoldState()
    val coroutineScope= rememberCoroutineScope()
    val bottomNavigationItems= listOf(
        BottomNavigationScreens.Home,
        BottomNavigationScreens.Party,
        BottomNavigationScreens.Add,
        BottomNavigationScreens.Trading,
        BottomNavigationScreens.Profile
    )
    val drawerNavigationItems= listOf(
        BottomNavigationScreens.MyProfile,
        BottomNavigationScreens.Calculation,
        BottomNavigationScreens.TipsTricks,
        BottomNavigationScreens.Dictionary,
        BottomNavigationScreens.Notification,
        BottomNavigationScreens.Sale
    )
    Scaffold (
        topBar={
            CTFAppTopNavigation(
                onIconClick = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.open()
                    }
                },
                navController,
            )
        },
        scaffoldState=scaffoldState,
        drawerContent={
                CTFAppDrawerNavigation (
                    closeDrawerAction = {
                        coroutineScope.launch {
                            scaffoldState.drawerState.close()
                        }
                    },
                    navController =navController,
                    items = drawerNavigationItems
                )


        },
        bottomBar ={
            CTFAppBottomNavigation(navController , bottomNavigationItems )
        }
    ){
        MainScreenNavigationConfiguration(navController)
    }
}




@Composable
fun MainScreenNavigationConfiguration(
    navController: NavHostController
){
    NavHost(navController, startDestination = BottomNavigationScreens.Home.route){
        composable(BottomNavigationScreens.Home.route){
            val registerViewModel = hiltViewModel<RegisterViewModel>()
            val postViewModel = hiltViewModel<PostViewModel>()
            HomeScreen(registerViewModel,postViewModel)
        }
        composable(BottomNavigationScreens.Party.route){
            PartyScreen()
        }
        composable(BottomNavigationScreens.Add.route){
            AddScreen()
        }
        composable(BottomNavigationScreens.Trading.route){
            TradingScreen()
        }
        composable(BottomNavigationScreens.Profile.route){
            ProfileScreen()
        }
        composable(BottomNavigationScreens.Search.route){
            SearchScreen()
        }
        composable(BottomNavigationScreens.MyProfile.route){
            MyProfileScreen()
        }
        composable(BottomNavigationScreens.Calculation.route){
            CalculationScreen()
        }
        composable(BottomNavigationScreens.TipsTricks.route){
            TipsTricksScreen()
        }
        composable(BottomNavigationScreens.Dictionary.route){
            DictionaryScreen()
        }
        composable(BottomNavigationScreens.Notification.route){
            NotificationScreen()
        }
        composable(BottomNavigationScreens.Sale.route){
            SectionScreen()
        }
        composable("LoginRoute"){
            val registerViewModel = hiltViewModel<RegisterViewModel>()
            LoginScreen(navController,registerViewModel)
        }
        composable("RegisterRoute"){
            val registerViewModel = hiltViewModel<RegisterViewModel>()
            RegisterScreen(navController,registerViewModel)
        }
    }
}
