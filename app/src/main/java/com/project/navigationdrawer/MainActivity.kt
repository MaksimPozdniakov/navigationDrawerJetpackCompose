package com.project.navigationdrawer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import androidx.compose.material.IconButton
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.ListItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.DrawerState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalDrawer
import androidx.compose.material.icons.filled.Menu
import kotlinx.coroutines.CoroutineScope
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color
import com.project.navigationdrawer.ui.theme.AnchorNavy
import com.project.navigationdrawer.ui.theme.SlateGrey

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    MyAppTheme {
        // Родительский Scaffold с TopAppBar
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Navigation Drawer Sample") },
                    //backgroundColor = AnchorNavy, // вручную меняем цвет фона
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                if (drawerState.isClosed) {
                                    drawerState.open()
                                } else {
                                    drawerState.close()
                                }
                            }
                        }) {
                            Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        ) { innerPadding ->
            // ModalDrawer внутри Scaffold
            ModalDrawer(
                drawerState = drawerState,
                drawerContent = {
                    DrawerContent(
                        navController = navController, drawerState = drawerState,
                        coroutineScope = coroutineScope
                    )
                }
            ) {
                // Основной контент приложения
                NavHost(
                    navController = navController,
                    startDestination = "home",
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable("home") { HomeScreen() }
                    composable("profile") { ProfileScreen() }
                    composable("profile2") { ProfileScreen2() }
                    composable("settings") { SettingsScreen() }
                    // Другие маршруты...
                }
            }
        }
    }
}


@Composable
fun DrawerContent(
    navController: NavController,
    drawerState: DrawerState,
    coroutineScope: CoroutineScope
) {
    Column {
        DrawerItem(
            icon = Icons.Filled.Home,
            text = "Home",
            onClick = {
                coroutineScope.launch {
                    drawerState.close() // Закрываем боковую панель
                }
                navController.navigate("home") {
                    // Здесь вы можете добавить логику навигации если нужно
                }
            }
        )
        DrawerItem(
            icon = Icons.Filled.AccountCircle,
            text = "Profile",
            onClick = {
                coroutineScope.launch {
                    drawerState.close() // Закрываем боковую панель
                }
                navController.navigate("profile") {
                    // Здесь вы можете добавить логику навигации если нужно
                }
            }
        )
        DrawerItem(
            icon = Icons.Filled.AccountCircle,
            text = "Profile2",
            onClick = {
                coroutineScope.launch {
                    drawerState.close() // Закрываем боковую панель
                }
                navController.navigate("profile2") {
                    // Здесь вы можете добавить логику навигации если нужно
                }
            }
        )
        DrawerItem(
            icon = Icons.Filled.Settings,
            text = "Settings",
            onClick = {
                coroutineScope.launch {
                    drawerState.close() // Закрываем боковую панель
                }
                navController.navigate("settings") {
                    // Здесь вы можете добавить логику навигации если нужно
                }
            }
        )
        // Добавьте больше элементов DrawerItem для других экранов, если требуется
    }
}


@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize() // Заполнить весь доступный экран
            .background(color = SlateGrey) // Установить цвет фона
    ) {
        Text(text = "Home Screen", modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize() // Заполнить весь доступный экран
            .background(color = SlateGrey) // Установить цвет фона
    ) {
        Text(text = "Profile Screen", modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun ProfileScreen2() {
    Column(
        modifier = Modifier
            .fillMaxSize() // Заполнить весь доступный экран
            .background(color = SlateGrey) // Установить цвет фона
    ) {
        Text(text = "Profile2 Screen", modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize() // Заполнить весь доступный экран
            .background(color = SlateGrey) // Установить цвет фона
    ) {
        Text(text = "Settings Screen", modifier = Modifier.padding(16.dp))
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DrawerItem(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit
) {
    ListItem(
        modifier = Modifier.clickable(onClick = onClick),
        icon = { Icon(icon, contentDescription = text) },
        text = { Text(text) }
    )
}

// разбираемся как менять основной цвет темы приложения
@Composable
fun MyAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = lightColors(primary = AnchorNavy, onPrimary = Color.White) // onPrimary будет использован для текста и иконок на MyCustomColor фоне
    ) {
        content() // Ваше приложение
    }
}

//@Composable
//fun MyAppTopBar() {
//    TopAppBar(
//        title = { Text("Название") },
//        // backgroundColor и contentColor автоматически наследуются из темы
//    )
//}


