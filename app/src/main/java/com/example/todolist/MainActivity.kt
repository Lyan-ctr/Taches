package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todolist.ui.theme.TodolistTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodolistTheme {
                // Le conteneur principal
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    val navController = rememberNavController()

                    // Gestion de la navigation
                    NavHost(navController = navController, startDestination = "welcome") {
                        composable("welcome") {
                            HomeScreen(navController)
                        }
                        composable("home") {
                            TaskScreen(navController)
                        }
                    }
                }
            }
        }
    }
}

// --- ÉCRAN D'ACCUEIL ---
@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "karibu",
            style = MaterialTheme.typography.displayLarge,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = { navController.navigate("home") },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text("voir les pays", color = Color.White)
        }
    }
}

// --- ÉCRAN DE LA LISTE (M'BOKAS) ---
@Composable
fun TaskScreen(navController: NavController) {
    val paysListe = listOf("Sénégal", "Congo", "Belgique", "Canada", "France")

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "📍 M'bokas",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {
            items(paysListe) { pays ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Affichage de l'image correspondante
                    Image(
                        painter = painterResource(
                            id = when (pays) {
                                "Sénégal" -> R.drawable.senegal
                                "Congo"   -> R.drawable.congo
                                "Belgique" -> R.drawable.belgique
                                "Canada"  -> R.drawable.canada
                                "France"  -> R.drawable.france
                                else      -> R.drawable.ic_launcher_background
                            }
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .size(55.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        text = pays,
                        modifier = Modifier.padding(start = 20.dp).weight(1f),
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                }
                HorizontalDivider(color = Color.LightGray, thickness = 0.5.dp)
            }
        }
    }
}