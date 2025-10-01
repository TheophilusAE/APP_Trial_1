package com.example.myapplication

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountTree
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay

// Assuming ProfileCardModern, ContactInfoRowModern, R.drawable.pfp, 
// ModernIconTint, ModernPrimaryText are accessible from MainActivity.kt (same package)

object AppScreen {
    const val LOGIN = "login"
    const val SCREEN_1 = "screen1"
    const val SCREEN_2 = "screen2"
    const val SCREEN_3 = "screen3"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreen.LOGIN) {
        composable(AppScreen.LOGIN) {
            LoginScreen(navController = navController)
        }
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
fun LoginScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var showErrorDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var showContent by remember { mutableStateOf(false) }
    
    val focusManager = LocalFocusManager.current
    
    // Animations
    val infiniteTransition = rememberInfiniteTransition(label = "background")
    val animatedOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(20000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ), label = "rotate"
    )
    
    // Show content with delay for smooth entry animation
    LaunchedEffect(Unit) {
        delay(300)
        showContent = true
    }
    
    // Login credentials
    val correctUsername = "admin"
    val correctPassword = "password123"
    
    suspend fun performLogin() {
        isLoading = true
        delay(1500) // Simulate network call
        
        when {
            username.isBlank() -> {
                errorMessage = "Please enter a username"
                showErrorDialog = true
            }
            password.isBlank() -> {
                errorMessage = "Please enter a password"
                showErrorDialog = true
            }
            username != correctUsername -> {
                errorMessage = "Invalid username. Please try again."
                showErrorDialog = true
            }
            password != correctPassword -> {
                errorMessage = "Invalid password. Please try again."
                showErrorDialog = true
            }
            else -> {
                // Successful login
                navController.navigate(AppScreen.SCREEN_1) {
                    popUpTo(AppScreen.LOGIN) { inclusive = true }
                }
            }
        }
        isLoading = false
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xFF667eea),
                        Color(0xFF764ba2),
                        Color(0xFF6B73FF),
                        Color(0xFF000DFF)
                    ),
                    center = androidx.compose.ui.geometry.Offset(
                        x = 500f + animatedOffset,
                        y = 500f
                    ),
                    radius = 1500f
                )
            )
    ) {
        // Floating decorative elements
        repeat(6) { index ->
            val scale = remember { Animatable(0.5f) }
            LaunchedEffect(Unit) {
                delay(index * 200L)
                scale.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(1000, easing = FastOutSlowInEasing)
                )
            }
            
            Box(
                modifier = Modifier
                    .size((20 + index * 8).dp)
                    .offset(
                        x = (50 + index * 60).dp,
                        y = (100 + index * 120).dp
                    )
                    .scale(scale.value)
                    .graphicsLayer {
                        rotationZ = animatedOffset + index * 60
                        alpha = 0.3f
                    }
                    .background(
                        color = Color.White.copy(alpha = 0.2f),
                        shape = CircleShape
                    )
            )
        }
        
        AnimatedVisibility(
            visible = showContent,
            enter = fadeIn(animationSpec = tween(800)) + slideInVertically(
                animationSpec = tween(800),
                initialOffsetY = { it / 2 }
            ),
            modifier = Modifier.align(Alignment.Center)
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                shape = RoundedCornerShape(24.dp),
                color = Color.White.copy(alpha = 0.95f),
                shadowElevation = 20.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    // Animated logo/title area
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .background(
                                    brush = Brush.linearGradient(
                                        colors = listOf(
                                            Color(0xFF667eea),
                                            Color(0xFF764ba2)
                                        )
                                    ),
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(40.dp)
                            )
                        }
                        
                        Text(
                            text = "Welcome Back",
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF2C3E50)
                            ),
                            textAlign = TextAlign.Center
                        )
                        
                        Text(
                            text = "Sign in to continue your journey",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = Color(0xFF64748B)
                            ),
                            textAlign = TextAlign.Center
                        )
                    }
                    
                    // Form fields
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        OutlinedTextField(
                            value = username,
                            onValueChange = { username = it },
                            label = { Text("Username") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = "Username Icon",
                                    tint = Color(0xFF667eea)
                                )
                            },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                            keyboardActions = KeyboardActions(
                                onNext = { focusManager.moveFocus(FocusDirection.Down) }
                            ),
                            shape = RoundedCornerShape(16.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFF667eea),
                                focusedLabelColor = Color(0xFF667eea)
                            )
                        )
                        
                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            label = { Text("Password") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Lock,
                                    contentDescription = "Password Icon",
                                    tint = Color(0xFF667eea)
                                )
                            },
                            trailingIcon = {
                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    Icon(
                                        imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                        contentDescription = if (passwordVisible) "Hide password" else "Show password",
                                        tint = Color(0xFF64748B)
                                    )
                                }
                            },
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                            keyboardActions = KeyboardActions(
                                onDone = { 
                                    focusManager.clearFocus()
                                    // performLogin()
                                }
                            ),
                            shape = RoundedCornerShape(16.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFF667eea),
                                focusedLabelColor = Color(0xFF667eea)
                            )
                        )
                    }
                    
                    // Login Button with loading state
                    Button(
                        onClick = { 
                            if (!isLoading) {
                                isLoading = true
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF667eea)
                        ),
                        enabled = !isLoading
                    ) {
                        if (isLoading) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(16.dp)
                                        .background(
                                            Color.White,
                                            CircleShape
                                        )
                                        .graphicsLayer {
                                            rotationZ = animatedOffset * 2
                                        }
                                )
                                Text(
                                    text = "Signing In...",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                )
                            }
                        } else {
                            Text(
                                text = "Sign In",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            )
                        }
                    }
                    
                    // Demo credentials with modern styling
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = Color(0xFFF1F5F9),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = null,
                                    tint = Color(0xFFFBBF24),
                                    modifier = Modifier.size(16.dp)
                                )
                                Text(
                                    text = "Demo Credentials",
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF374151)
                                    )
                                )
                            }
                            Text(
                                text = "Username: admin",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = Color(0xFF6B7280)
                                )
                            )
                            Text(
                                text = "Password: password123",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = Color(0xFF6B7280)
                                )
                            )
                        }
                    }
                }
            }
        }
    }
    
    // Enhanced Error Dialog
    if (showErrorDialog) {
        AlertDialog(
            onDismissRequest = { showErrorDialog = false },
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(
                                Color(0xFFEF4444),
                                CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "!",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                    Text(
                        text = "Authentication Failed",
                        color = Color(0xFF374151)
                    )
                }
            },
            text = {
                Text(
                    text = errorMessage,
                    color = Color(0xFF6B7280)
                )
            },
            confirmButton = {
                Button(
                    onClick = { showErrorDialog = false },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF667eea)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Try Again", color = Color.White)
                }
            },
            shape = RoundedCornerShape(16.dp)
        )
    }
    
    // Handle login with LaunchedEffect
    if (isLoading) {
        LaunchedEffect(Unit) {
            performLogin()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Page1Screen(navController: NavController) {
    var showContent by remember { mutableStateOf(false) }
    
    LaunchedEffect(Unit) {
        delay(200)
        showContent = true
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "Profile",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF667eea),
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(
                        onClick = { 
                            navController.navigate(AppScreen.LOGIN) {
                                popUpTo(0) { inclusive = true }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Logout,
                            contentDescription = "Logout",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(AppScreen.SCREEN_2) },
                containerColor = Color(0xFF667eea),
                contentColor = Color.White,
                modifier = Modifier
                    .clip(CircleShape)
            ) {
                Icon(Icons.Default.Email, contentDescription = "Contact")
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFF8FAFC),
                            Color(0xFFE2E8F0)
                        )
                    )
                )
                .padding(innerPadding)
        ) {
            AnimatedVisibility(
                visible = showContent,
                enter = fadeIn(animationSpec = tween(600)) + slideInVertically(
                    animationSpec = tween(600),
                    initialOffsetY = { it / 3 }
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    ProfileCardModern(
                        name = "Theophilus A.E.",
                        title = "Visionary FullStack Architect",
                        address = "Stark Tower, New York, NY",
                        email = "theophilus.ae@avengers.com",
                        phone = "+1 (555) TONY-STARK",
                        bio = "Building the future, one line of code at a time. Dedicated to innovative solutions and pushing technological boundaries. Occasional philanthropist.",
                        profileImageRes = R.drawable.pfp
                    )
                    
                    // Modern navigation cards
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        ModernNavigationCard(
                            title = "Contact",
                            subtitle = "Get in touch",
                            icon = Icons.Default.Email,
                            gradient = listOf(Color(0xFF667eea), Color(0xFF764ba2)),
                            modifier = Modifier.weight(1f)
                        ) {
                            navController.navigate(AppScreen.SCREEN_2)
                        }
                        
                        ModernNavigationCard(
                            title = "Projects",
                            subtitle = "View portfolio",
                            icon = Icons.Default.AccountTree,
                            gradient = listOf(Color(0xFF11998e), Color(0xFF38ef7d)),
                            modifier = Modifier.weight(1f)
                        ) {
                            navController.navigate(AppScreen.SCREEN_3)
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Page2Screen(navController: NavController) {
    var showContent by remember { mutableStateOf(false) }
    
    LaunchedEffect(Unit) {
        delay(200)
        showContent = true
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "Contact Details",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF11998e),
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(
                        onClick = { 
                            navController.navigate(AppScreen.LOGIN) {
                                popUpTo(0) { inclusive = true }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Logout,
                            contentDescription = "Logout",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFF0FDF4),
                            Color(0xFFDCFCE7)
                        )
                    )
                )
                .padding(innerPadding)
        ) {
            AnimatedVisibility(
                visible = showContent,
                enter = fadeIn(animationSpec = tween(600)) + slideInVertically(
                    animationSpec = tween(600),
                    initialOffsetY = { it / 3 }
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Text(
                        text = "Get In Touch",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF065F46)
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    
                    Text(
                        text = "Feel free to reach out through any of these channels",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = Color(0xFF047857)
                        ),
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    
                    ModernContactCard(
                        icon = Icons.Default.Email,
                        title = "Professional Email",
                        value = "theophilus.ae.pro@example.com",
                        description = "For business inquiries",
                        gradient = listOf(Color(0xFF667eea), Color(0xFF764ba2))
                    )
                    
                    ModernContactCard(
                        icon = Icons.Default.Phone,
                        title = "Work Phone",
                        value = "+1 (555) 123-4567",
                        description = "Available Mon-Fri, 9AM-5PM EST",
                        gradient = listOf(Color(0xFF11998e), Color(0xFF38ef7d))
                    )
                    
                    ModernContactCard(
                        icon = Icons.Default.LocationOn,
                        title = "Office Location",
                        value = "123 Innovation Drive, Tech City, CA",
                        description = "Visit by appointment",
                        gradient = listOf(Color(0xFFf093fb), Color(0xFFf5576c))
                    )
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    // Navigation buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Button(
                            onClick = { navController.navigate(AppScreen.SCREEN_1) },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF065F46)
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("← Profile", color = Color.White)
                        }
                        
                        Button(
                            onClick = { navController.navigate(AppScreen.SCREEN_3) },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF11998e)
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Projects →", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Page3Screen(navController: NavController) {
    var showContent by remember { mutableStateOf(false) }
    
    LaunchedEffect(Unit) {
        delay(200)
        showContent = true
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "Project Portfolio",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFf093fb),
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(
                        onClick = { 
                            navController.navigate(AppScreen.LOGIN) {
                                popUpTo(0) { inclusive = true }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Logout,
                            contentDescription = "Logout",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFFDF2F8),
                            Color(0xFFFCE7F3)
                        )
                    )
                )
                .padding(innerPadding)
        ) {
            AnimatedVisibility(
                visible = showContent,
                enter = fadeIn(animationSpec = tween(600)) + slideInVertically(
                    animationSpec = tween(600),
                    initialOffsetY = { it / 3 }
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Text(
                        text = "Featured Projects",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF831843)
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    
                    Text(
                        text = "Showcasing innovation and technical excellence",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = Color(0xFFBE185D)
                        ),
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    
                    ModernProjectCard(
                        projectName = "AI-Powered Task Manager",
                        description = "A smart task manager that learns your habits and optimizes your workflow using machine learning algorithms.",
                        technologies = listOf("Kotlin", "TensorFlow", "Firebase"),
                        gradient = listOf(Color(0xFF667eea), Color(0xFF764ba2)),
                        status = "Live"
                    )
                    
                    ModernProjectCard(
                        projectName = "Eco-Friendly Route Planner",
                        description = "Calculates the most environmentally friendly routes while considering traffic patterns and carbon footprint.",
                        technologies = listOf("React Native", "Maps API", "Node.js"),
                        gradient = listOf(Color(0xFF11998e), Color(0xFF38ef7d)),
                        status = "Beta"
                    )
                    
                    ModernProjectCard(
                        projectName = "Interactive Storytelling App",
                        description = "An immersive platform where users can create, share, and experience interactive digital stories.",
                        technologies = listOf("Flutter", "GraphQL", "MongoDB"),
                        gradient = listOf(Color(0xFFf093fb), Color(0xFFf5576c)),
                        status = "Development"
                    )
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    // Navigation buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Button(
                            onClick = { navController.navigate(AppScreen.SCREEN_1) },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF831843)
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("← Profile", color = Color.White)
                        }
                        
                        Button(
                            onClick = { navController.navigate(AppScreen.SCREEN_2) },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFf093fb)
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Contact →", color = Color.White)
                        }
                    }
                }
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

@Composable
fun ModernNavigationCard(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    gradient: List<Color>,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .height(120.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 8.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(colors = gradient)
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
                
                Column {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    )
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun ModernContactCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    value: String,
    description: String,
    gradient: List<Color>
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 6.dp,
        color = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(
                        brush = Brush.linearGradient(colors = gradient),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
            
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1F2937)
                    )
                )
                Text(
                    text = value,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color(0xFF4B5563)
                    )
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color(0xFF9CA3AF)
                    )
                )
            }
        }
    }
}

@Composable
fun ModernProjectCard(
    projectName: String,
    description: String,
    technologies: List<String>,
    gradient: List<Color>,
    status: String
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        shadowElevation = 12.dp,
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = projectName,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1F2937)
                    ),
                    modifier = Modifier.weight(1f)
                )
                
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = when (status) {
                        "Live" -> Color(0xFF10B981)
                        "Beta" -> Color(0xFFF59E0B)
                        else -> Color(0xFF6366F1)
                    }
                ) {
                    Text(
                        text = status,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        ),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(0xFF6B7280),
                    lineHeight = 20.sp
                )
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Technologies
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                technologies.take(3).forEach { tech ->
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color(0xFFF3F4F6)
                    ) {
                        Text(
                            text = tech,
                            style = MaterialTheme.typography.labelMedium.copy(
                                color = Color(0xFF374151)
                            ),
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Gradient accent bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .background(
                        brush = Brush.horizontalGradient(colors = gradient),
                        shape = RoundedCornerShape(2.dp)
                    )
            )
        }
    }
}
