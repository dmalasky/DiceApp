package com.example.diceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.diceapp.ui.theme.DiceAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var number by remember {
                mutableIntStateOf(0)
            }
            var isDice by remember {
                mutableStateOf(false)
            }

            Column{
                Row {
                    // Dice
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .padding(start = 16.dp, top = 16.dp, end = 8.dp)
                    ) {
                        ImageCard(
                            painter = painterResource(id = R.drawable.dice),
                            contentDescription = "Roll the dice",
                            title = "Roll the dice",
                            onClick = { number = (0..6).random()
                                        isDice = false}
                        )
                    }
                    // Coin
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 16.dp, top = 16.dp, start = 8.dp)
                    ) {
                        ImageCard(
                            painter = painterResource(id = R.drawable.coinflip),
                            contentDescription = "Flip the coin",
                            title = "Flip the coin",
                            onClick = { number = (0..1).random()
                                        isDice = true}
                        )
                    }
                }
                Row (modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),


                    verticalAlignment = Alignment.CenterVertically
                ){
                    ResultsCard(number = number, isDice)
                }


            }



        }
    }
}

@Composable
fun ResultsCard(
    number: Int,
    isDice: Boolean
){
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ) {
        Box(modifier = Modifier.height(100.dp)) { // Everything stacks in a box
            // Image
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.brick_back),
                contentDescription = "pink background",
                contentScale = ContentScale.Crop // Center crop
            )


            //Gradient
            Box(
                modifier = Modifier // using a box to have something to apply modifier to.
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 200f // this is hardcode, could be dynamic based on box size.
                        )
                    )
            )

            // Box for the Text (in a box to make it easier to format).
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (!isDice) "$number" else if (number == 0) "Heads" else "Tails",
                    style = TextStyle(color = Color.White),
                    fontSize = 30.sp
                ) // sp is for font
            }
        }
    }
}


@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit

) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick()

            },
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ) {
        Box(modifier = Modifier.height(200.dp)) { // Everything stacks in a box
            // Image
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop // Center crop
            )
            //Gradient
            Box(
                modifier = Modifier // using a box to have something to apply modifier to.
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 300f // this is hardcode, could be dynamic based on box size.
                        )
                    )
            )

            // Box for the Text (in a box to make it easier to format).
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    title,
                    style = TextStyle(color = Color.White),
                    fontSize = 16.sp
                ) // sp is for font
            }
        }
    }
}

@Composable
fun SecondScreen() {
    Text(text = "This is the second screen")
}

//Row {
//    val navController = rememberNavController()
//
//    NavHost(navController = navController, startDestination = "first_screen") {
//        composable("first_screen") {
//            // Your first screen composable here
//            // Add a button to navigate to the second screen
//            Button(onClick = { navController.navigate("second_screen") }) {
//                Text("Go to second screen")
//            }
//        }
//        composable("second_screen") {
//            SecondScreen()
//        }
//    }
//}

