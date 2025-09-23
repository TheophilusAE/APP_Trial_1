package com.example.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountTree // Added for Page 3
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// Assuming ProfileCardModern, ContactInfoRowModern, R.drawable.pfp, 
// ModernIconTint, ModernPrimaryText are accessible from MainActivity.kt (same package)

object AppScreen {
    const val SCREEN_1 = "screen1"
    const val SCREEN_2 = "screen2"
    const val SCREEN_3 = "screen3"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreen.SCREEN_1) {
        composable(AppScreen.SCREEN_1) {
            Page1Screen(navController = navController)
        }
        composable(AppScreen.SCREEN_2) {
            Page2Screen(navController = navController)
        }
        composable(AppScreen.SCREEN_3) {
            Page3Screen(navController = navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Page1Screen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Page 1 - Profile") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 8.dp), // Overall horizontal padding
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            ProfileCardModern(
                name = "Theophilus A.E.",
                title = "Visionary FullStack Architect",
                address = "Stark Tower, New York, NY",
                email = "theophilus.ae@avengers.com",
                phone = "+1 (555) TONY-STARK",
                bio = "Building the future, one line of code at a time. Dedicated to innovative solutions and pushing technological boundaries. Occasional philanthropist.",
                profileImageRes = R.drawable.pfp // Ensure R.drawable.pfp exists
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = { navController.navigate(AppScreen.SCREEN_2) }) {
                Text("Go to Contact Details")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = { navController.navigate(AppScreen.SCREEN_3) }) {
                Text("Go to Project Portfolio")
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Page2Screen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Page 2 - Contact Details") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text("Detailed Contact Information", style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(bottom = 12.dp))
            ContactInfoRowModern(
                icon = Icons.Filled.Email,
                text = "theophilus.ae.pro@example.com",
                contentDescription = "Professional Email"
            )
            ContactInfoRowModern(
                icon = Icons.Filled.Phone,
                text = "+1 (555) 123-4567",
                contentDescription = "Work Phone"
            )
            ContactInfoRowModern(
                icon = Icons.Filled.LocationOn,
                text = "123 Innovation Drive, Tech City, CA",
                contentDescription = "Office Address"
            )
            Spacer(modifier = Modifier.weight(1f)) // Pushes buttons to bottom
            Button(onClick = { navController.navigate(AppScreen.SCREEN_1) }, modifier = Modifier.fillMaxWidth()) {
                Text("Back to Profile")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = { navController.navigate(AppScreen.SCREEN_3) }, modifier = Modifier.fillMaxWidth()) {
                Text("Go to Project Portfolio")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Page3Screen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Page 3 - Project Portfolio") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("My Awesome Projects", style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(bottom = 16.dp))
            
            ProjectItem(projectName = "AI-Powered Task Manager", description = "A smart task manager that learns your habits.")
            Spacer(modifier = Modifier.height(12.dp))
            ProjectItem(projectName = "Eco-Friendly Route Planner", description = "Calculates the most environmentally friendly routes.")
            Spacer(modifier = Modifier.height(12.dp))
            ProjectItem(projectName = "Interactive Storytelling App", description = "An app where users can create and share interactive stories.")
            
            Spacer(modifier = Modifier.weight(1f)) // Pushes buttons to bottom
            Button(onClick = { navController.navigate(AppScreen.SCREEN_1) }, modifier = Modifier.fillMaxWidth()) {
                Text("Back to Profile")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = { navController.navigate(AppScreen.SCREEN_2) }, modifier = Modifier.fillMaxWidth()) {
                Text("Go to Contact Details")
            }
        }
    }
}

@Composable
fun ProjectItem(projectName: String, description: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(projectName, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
            Spacer(modifier = Modifier.height(4.dp))
            Text(description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
