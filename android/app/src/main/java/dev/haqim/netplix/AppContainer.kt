package dev.haqim.netplix

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dev.haqim.netplix.core.domain.model.Genre
import dev.haqim.netplix.core.domain.model.GenreArgsNavType
import dev.haqim.netplix.core.ui.nav.DiscoverRoute
import dev.haqim.netplix.core.ui.nav.HomeRoute
import dev.haqim.netplix.core.ui.theme.NetplixTheme
import dev.haqim.netplix.feature.discover.ui.DiscoverScreen
import dev.haqim.netplix.feature.home.ui.HomeScreen
import kotlin.reflect.typeOf


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppContainer() {
    val navController = rememberNavController()

    NetplixTheme {
        SharedTransitionLayout {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background,
            ) {
                NavHost(navController = navController, startDestination = HomeRoute){
                    composable<HomeRoute> {
                        HomeScreen(
                            navigateToDiscover = { genre ->
                                navController.navigate(route = DiscoverRoute(genre))
                            }
                        )
                    }
                    composable<DiscoverRoute>(
                        typeMap = mapOf(typeOf<Genre?>() to GenreArgsNavType)
                    ) { backStackEntry ->
                        val param: Genre? by remember {
                            mutableStateOf(backStackEntry.toRoute<Genre?>())
                        }
                        DiscoverScreen(
                            genre = param
                        ) {
                            navController.navigateUp()
                        }
                    }
                }
            }
        }
    }
}