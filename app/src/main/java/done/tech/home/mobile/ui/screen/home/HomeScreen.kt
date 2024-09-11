package done.tech.home.mobile.ui.screen.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import done.tech.home.mobile.R
import done.tech.home.mobile.ui.screen.home.model.TempCoffeeMakerDataModel
import done.tech.home.mobile.ui.theme.DoneHomeMobileTheme


@Preview
@Composable
private fun Preview() {

    DoneHomeMobileTheme {
        HomeScreen(false, TempCoffeeMakerDataModel())
    }

}

@Composable
fun HomeScreen(
    isMqttConnected: Boolean = false,
    coffeeMakerState: TempCoffeeMakerDataModel = TempCoffeeMakerDataModel(),
    updateState: (TempCoffeeMakerDataModel) -> Unit = {},
) {

    var showDialogCoffeeMaker by remember { mutableStateOf(false) }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxSize()
                ) {
                    IconButton(
                        modifier = Modifier.size(52.dp),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary.copy(0.3f)
                        ),
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            Icons.Rounded.Home,
                            modifier = Modifier.size(36.dp),
                            contentDescription = ""
                        )
                    }
                    IconButton(
                        modifier = Modifier.size(52.dp),
//                    colors = IconButtonDefaults.iconButtonColors(
//                        containerColor = MaterialTheme.colorScheme.primary.copy(0.3f)
//                    ),
                        onClick = { /*TODO*/ }) {
                        Icon(
                            Icons.Rounded.Settings,
                            modifier = Modifier.size(36.dp),
                            contentDescription = ""
                        )
                    }
                }
            }
        }
    ) { innerPadding ->

        Column(Modifier.padding(innerPadding), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(if (isMqttConnected) Color.Green else Color.Red),
                contentAlignment = Alignment.Center
            ) {
                if (isMqttConnected)
                    Text(text = "!MQTT Connected", color = Color.Black)
                else
                    Text(text = "!MQTT connection lost", color = Color.White)
            }
            CoffeeMakerCard {
                showDialogCoffeeMaker = true
            }
        }

        if (showDialogCoffeeMaker)
            CoffeeMakerDialog(
                coffeeMakerState,
                updateState = updateState,
                onDismiss = { showDialogCoffeeMaker = false },
            )
    }
}

@Composable
private fun CoffeeMakerCard(onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(100.dp),
        onClick = onClick
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Icon(
                modifier = Modifier
                    .fillMaxHeight(),
                painter = painterResource(id = R.drawable.icon_coffee_maker),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.size(16.dp))
            Column {
                Text(text = "قهوه ساز", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.size(16.dp))
                Text(text = "در حال آماده سازی", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

@Composable
private fun CoffeeMakerDialog(
    coffeeMakerState: TempCoffeeMakerDataModel,
    updateState: (TempCoffeeMakerDataModel) -> Unit = {},
    onDismiss: () -> Unit,
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            Modifier
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.large,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)

        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Icon(
                        modifier = Modifier.size(36.dp),
                        painter = painterResource(id = R.drawable.icon_coffee_maker),
                        contentDescription = ""
                    )
                    Spacer(modifier = Modifier.size(14.dp))
                    Column {
                        Text(text = "قهوه ساز", style = MaterialTheme.typography.titleMedium)
                        Text(
                            text = "آماده شروع به کار",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    Text(
                        modifier = Modifier.weight(1f),
                        text =
                        "${(coffeeMakerState.UpdateTime / 60)}:${(coffeeMakerState.UpdateTime % 60)}",
                        style = MaterialTheme.typography.headlineLarge,
                        textAlign = TextAlign.Left
                    )
                }

                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))

                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(), horizontalArrangement = Arrangement.Center
                ) {
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .alpha(0.3f)
                            .aspectRatio(1f),
                        onClick = {}
                    ) {
                        Column(
                            modifier = Modifier.padding(6.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painterResource(id = R.drawable.icon_coffee_beans),
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(6.dp)
                                    .fillMaxWidth(),
                                contentDescription = ""
                            )
                            Text(text = "دانه قهوه")
                        }
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .alpha(if (coffeeMakerState.Coffee) 1f else 0.3f)
                            .aspectRatio(1f),
                        onClick = {
                            updateState(coffeeMakerState.copy(Tea = false, Coffee = true))
                        }
                    ) {
                        Column(
                            modifier = Modifier.padding(6.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painterResource(id = R.drawable.icon_coffee_powder),
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(6.dp)
                                    .fillMaxWidth(),
                                contentDescription = ""
                            )
                            Text(text = "پودر قهوه")
                        }
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .alpha(if (coffeeMakerState.Tea) 1f else 0.3f)
                            .aspectRatio(1f),
                        onClick = {
                            updateState(coffeeMakerState.copy(Tea = true, Coffee = false))
                        }
                    ) {
                        Column(
                            modifier = Modifier.padding(6.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painterResource(id = R.drawable.icon_tea_leaf),
                                modifier = Modifier
                                    .padding(6.dp)
                                    .weight(1f)
                                    .fillMaxWidth(),
                                contentDescription = ""
                            )
                            Text(text = "دمنوش")
                        }
                    }
                }

                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))

                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(), horizontalArrangement = Arrangement.Center
                ) {
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .alpha(if (coffeeMakerState.CoffeeProperty.GinderLevel == 3) 1f else 0.3f)
                            .aspectRatio(1f),
                        onClick = {
                            updateState(
                                coffeeMakerState.copy(
                                    CoffeeProperty = coffeeMakerState.CoffeeProperty.copy(
                                        GinderLevel = 3
                                    )
                                )
                            )
                        }
                    ) {
                        Column(
                            modifier = Modifier.padding(6.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painterResource(id = R.drawable.icon_coffee_grind_lvl_3),
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(6.dp)
                                    .fillMaxWidth(),
                                contentDescription = ""
                            )
                            Text(text = "پودر ریز")
                        }
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .alpha(if (coffeeMakerState.CoffeeProperty.GinderLevel == 2) 1f else 0.3f)
                            .aspectRatio(1f),
                        onClick = {
                            updateState(
                                coffeeMakerState.copy(
                                    CoffeeProperty = coffeeMakerState.CoffeeProperty.copy(
                                        GinderLevel = 2
                                    )
                                )
                            )
                        }
                    ) {
                        Column(
                            modifier = Modifier.padding(6.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painterResource(id = R.drawable.icon_coffee_grind_lvl_2),
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(6.dp)
                                    .fillMaxWidth(),
                                contentDescription = ""
                            )
                            Text(text = "پودر متوسط")
                        }
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .alpha(if (coffeeMakerState.CoffeeProperty.GinderLevel == 1) 1f else 0.3f)
                            .aspectRatio(1f),
                        onClick = {
                            updateState(
                                coffeeMakerState.copy(
                                    CoffeeProperty = coffeeMakerState.CoffeeProperty.copy(
                                        GinderLevel = 1
                                    )
                                )
                            )
                        }
                    ) {
                        Column(
                            modifier = Modifier.padding(6.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painterResource(id = R.drawable.icon_coffee_grind_lvl_1),
                                modifier = Modifier
                                    .padding(6.dp)
                                    .weight(1f)
                                    .fillMaxWidth(),
                                contentDescription = ""
                            )
                            Text(text = "پودر درشت")
                        }
                    }
                }

                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))

                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(), horizontalArrangement = Arrangement.Center
                ) {
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .height(54.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(modifier = Modifier.weight(1f), text = "تعداد لیوان ها")

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                IconButton(onClick = {
                                    updateState(
                                        coffeeMakerState.copy(
                                            CoffeeProperty = coffeeMakerState.CoffeeProperty.copy(
                                                Cup = (coffeeMakerState.CoffeeProperty.Cup + 1).coerceIn(
                                                    1,
                                                    6
                                                )
                                            ),
                                            TeaProperty = coffeeMakerState.TeaProperty.copy(
                                                Cups = (coffeeMakerState.TeaProperty.Cups + 1).coerceIn(
                                                    1,
                                                    6
                                                )
                                            )
                                        )
                                    )
                                }) {
                                    Icon(Icons.Rounded.Add, contentDescription = "")
                                }
                                Text(
                                    text = if (coffeeMakerState.Coffee) coffeeMakerState.CoffeeProperty.Cup.toString() else coffeeMakerState.TeaProperty.Cups.toString(),
                                    style = MaterialTheme.typography.headlineLarge
                                )
                                IconButton(onClick = {
                                    updateState(
                                        coffeeMakerState.copy(
                                            CoffeeProperty = coffeeMakerState.CoffeeProperty.copy(
                                                Cup = (coffeeMakerState.CoffeeProperty.Cup - 1).coerceIn(
                                                    1,
                                                    6
                                                )
                                            ),
                                            TeaProperty = coffeeMakerState.TeaProperty.copy(
                                                Cups = (coffeeMakerState.TeaProperty.Cups - 1).coerceIn(
                                                    1,
                                                    6
                                                )
                                            )
                                        )
                                    )
                                }) {
                                    Icon(Icons.Rounded.Remove, contentDescription = "")
                                }
                            }
                        }
                    }
                }

                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))

                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(), horizontalArrangement = Arrangement.Center
                ) {
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = "شروع")
                        }
                    }

                    Spacer(modifier = Modifier.size(16.dp))

                    Card(
                        modifier = Modifier.size(48.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.icon_power),
                                contentDescription = ""
                            )
                        }
                    }
                }
            }
        }
    }
}