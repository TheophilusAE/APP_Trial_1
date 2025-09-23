package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

val ModernBackground = Color(0xFFF0F2F5)
val ModernCardSurfaceStart = Color(0xFFFFFFFF)
val ModernCardSurfaceEnd = Color(0xFFF8F9FA)
val ModernPrimaryText = Color(0xFF2C3E50)
val ModernSecondaryText = Color(0xFF7F8C8D)
val ModernAccent = Color(0xFF3498DB)
val ModernIconTint = Color(0xFF566573)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                // Call AppNavigation here
                AppNavigation()
            }
        }
    }
}

@Composable
fun ProfileCardModern(
    name: String,
    title: String,
    address: String,
    email: String,
    phone: String,
    bio: String,
    profileImageRes: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(0.92f)
            .padding(vertical = 20.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
            pressedElevation = 5.dp,
            hoveredElevation = 12.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(ModernCardSurfaceStart, ModernCardSurfaceEnd)
                    )
                )
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = profileImageRes),
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape)
                    .border(
                        width = 4.dp,
                        brush = Brush.linearGradient(
                            colors = listOf(ModernAccent, Color(0xFF2980B9))
                        ),
                        shape = CircleShape
                    )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = name,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp,
                    color = ModernPrimaryText
                )
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                    color = ModernAccent
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = bio,
                style = MaterialTheme.typography.bodyLarge.copy(
                    textAlign = TextAlign.Center,
                    color = ModernSecondaryText,
                    lineHeight = 22.sp
                ),
                modifier = Modifier.padding(horizontal = 10.dp)
            )

            Spacer(modifier = Modifier.height(28.dp))

            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 40.dp),
                thickness = 1.dp,
                color = ModernBackground.copy(alpha = 0.8f)
            )

            Spacer(modifier = Modifier.height(28.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                ContactInfoRowModern(
                    icon = Icons.Filled.LocationOn,
                    text = address,
                    contentDescription = "Address"
                )
                ContactInfoRowModern(
                    icon = Icons.Filled.Email,
                    text = email,
                    contentDescription = "Email"
                )
                ContactInfoRowModern(
                    icon = Icons.Filled.Phone,
                    text = phone,
                    contentDescription = "Phone"
                )
            }
        }
    }
}

@Composable
fun ContactInfoRowModern(
    icon: ImageVector,
    text: String,
    contentDescription: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = ModernIconTint,
            modifier = Modifier.size(22.dp)
        )
        Spacer(modifier = Modifier.width(18.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = ModernPrimaryText,
                fontSize = 15.sp
            )
        )
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 800)
@Composable
fun ProfileCardModernPreview() {
    MyApplicationTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(ModernBackground),
            contentAlignment = Alignment.Center
        ) {
            ProfileCardModern(
                name = "Theophilus A.E.",
                title = "Visionary FullStack Architect",
                address = "Stark Tower, New York, NY",
                email = "theophilus.ae@avengers.com",
                phone = "+1 (555) TONY-STARK",
                bio = "Building the future, one line of code at a time. Dedicated to innovative solutions and pushing technological boundaries. Occasional philanthropist.",
                profileImageRes = R.drawable.pfp
            )
        }
    }
}
