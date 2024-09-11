package done.tech.home.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import done.tech.home.mobile.ui.screen.home.HomeScreen
import done.tech.home.mobile.ui.screen.home.HomeViewModel
import done.tech.home.mobile.ui.screen.home.model.TempCoffeeMakerDataModel
import done.tech.home.mobile.ui.theme.DoneHomeMobileTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewmodel = hiltViewModel<HomeViewModel>()
            val coffeeState by viewmodel.getCoffeeMakerState()
                .collectAsState(initial = TempCoffeeMakerDataModel())
            val mqttConnection by viewmodel.mqttConnection.collectAsState(initial = false)
            DoneHomeMobileTheme {
                HomeScreen(mqttConnection, coffeeState, viewmodel::updateCoffeeMakerStatus)
            }
        }
    }
}
