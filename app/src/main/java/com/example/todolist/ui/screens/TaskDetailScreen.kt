package com.example.todolist.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todolist.viewmodel.TaskViewModel
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TaskDetailScreen(navController: NavController, taskId: Int, viewModel: TaskViewModel) {
    
    // On observe la tâche spécifique depuis la DB
    val task by viewModel.getTaskById(taskId).collectAsState(initial = null)
    val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Détails de la tâche") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Retour")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp)
        ) {
            task?.let { currentTask ->
                Text(
                    text = "Nom de la tâche :",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.Gray
                )
                Text(
                    text = currentTask.name,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(24.dp))

                DetailRow(label = "Date de début", value = currentTask.dateDebut.format(formatter))
                Spacer(modifier = Modifier.height(16.dp))
                DetailRow(label = "Date de fin", value = currentTask.dateFin.format(formatter))

                Spacer(modifier = Modifier.height(32.dp))

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = if (currentTask.isDone) Color(0xFFE8F5E9) else Color(0xFFFFF3E0)
                    )
                ) {
                    Text(
                        text = if (currentTask.isDone) "Statut : Terminée" else "Statut : En cours",
                        modifier = Modifier.padding(16.dp),
                        fontWeight = FontWeight.Bold,
                        color = if (currentTask.isDone) Color(0xFF2E7D32) else Color(0xFFE65100)
                    )
                }
            } ?: run {
                // Si la tâche n'est pas trouvée (chargement)
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Column {
        Text(text = label, style = MaterialTheme.typography.labelLarge, color = Color.Gray)
        Text(text = value, style = MaterialTheme.typography.bodyLarge, fontSize = 18.sp)
    }
}
