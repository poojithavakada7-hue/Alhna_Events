package aitam.csm.poojjitha.ahlnaevents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.layout.weight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import java.util.Calendar

// ============================================================
// COLORS
// ============================================================

val Cream = Color(0xFFFAF7F0)
val CreamDark = Color(0xFFF1EBDD)

val Forest = Color(0xFF345C45)
val ForestDark = Color(0xFF274936)
val ForestLight = Color(0xFFE7F0E9)

val Gold = Color(0xFFC49A55)
val GoldLight = Color(0xFFF4E7CA)

val Ink = Color(0xFF252D27)
val Muted = Color(0xFF7C827C)

val White = Color(0xFFFFFFFF)
val SoftWhite = Color(0xFFFFFCF7)

val ErrorRed = Color(0xFFB54B4B)


// ============================================================
// ACTIVITY
// ============================================================

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            AhlnaTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Cream
                ) {

                    AhlnaApp()
                }
            }
        }
    }
}


// ============================================================
// THEME
// ============================================================

@Composable
fun AhlnaTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(

        colorScheme =
            androidx.compose.material3.lightColorScheme(

                primary = Forest,
                onPrimary = White,

                secondary = Gold,
                onSecondary = Ink,

                background = Cream,
                onBackground = Ink,

                surface = White,
                onSurface = Ink
            ),

        content = content
    )
}


// ============================================================
// DATA MODEL
// ============================================================

data class FamilyEvent(

    val title: String,
    val date: String,
    val time: String,
    val venue: String,
    val category: String,
    val image: Int
)


// ============================================================
// SAMPLE EVENTS
// ============================================================

val sampleEvents = listOf(

    FamilyEvent(
        title = "Family Dinner",
        date = "28 September 2026",
        time = "7:30 PM",
        venue = "Ahlna Family Home",
        category = "Dinner",
        image = R.drawable.family_dinner
    ),

    FamilyEvent(
        title = "Birthday Celebration",
        date = "05 October 2026",
        time = "6:00 PM",
        venue = "Garden Hall",
        category = "Birthday",
        image = R.drawable.birthday
    ),

    FamilyEvent(
        title = "Wedding Anniversary",
        date = "15 October 2026",
        time = "7:00 PM",
        venue = "Royal Banquet Hall",
        category = "Wedding",
        image = R.drawable.wedding
    ),

    FamilyEvent(
        title = "Wedding",
        date = "30 September 2026",
        time = "9:00 PM",
        venue = "Novetel Vskp",
        category = "Wedding",
        image = R.drawable.wedding
    ),

    FamilyEvent(
        title = "Funeral Ceremony",
        date = "4 November 2026",
        time = "12:00 PM",
        venue = "GrandMother House",
        category = "Ceremony",
        image = R.drawable.abc
    )
)


// ============================================================
// APP NAVIGATION
// ============================================================

@Composable
fun AhlnaApp() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        // ====================================================
        // LOGIN
        // ====================================================

        composable("login") {

            LoginScreen(

                onLogin = {

                    navController.navigate("home") {

                        popUpTo("login") {
                            inclusive = true
                        }

                        launchSingleTop = true
                    }
                },

                onRegister = {

                    navController.navigate("register")
                }
            )
        }


        // ====================================================
        // REGISTER
        // ====================================================

        composable("register") {

            RegisterScreen(

                onRegister = {

                    navController.navigate("home") {

                        popUpTo("register") {
                            inclusive = true
                        }

                        launchSingleTop = true
                    }
                },

                onBack = {

                    navController.popBackStack()
                }
            )
        }


        // ====================================================
        // HOME
        // ====================================================

        composable("home") {

            MainLayout(
                navController = navController,
                selectedRoute = "home"
            ) {

                HomeScreen(

                    onCreateEvent = {

                        navController.navigate("create")
                    },

                    onEventClick = { event ->

                        navController.currentBackStackEntry
                            ?.savedStateHandle
                            ?.set("event", event)

                        navController.navigate("detail")
                    },

                    onViewAll = {

                        navController.navigate("events")
                    }
                )
            }
        }


        // ====================================================
        // EVENTS
        // ====================================================

        composable("events") {

            MainLayout(
                navController = navController,
                selectedRoute = "events"
            ) {

                EventsScreen(

                    onEventClick = { event ->

                        navController.currentBackStackEntry
                            ?.savedStateHandle
                            ?.set("event", event)

                        navController.navigate("detail")
                    }
                )
            }
        }


        // ====================================================
        // CALENDAR
        // ====================================================

        composable("calendar") {

            MainLayout(
                navController = navController,
                selectedRoute = "calendar"
            ) {

                CalendarScreen()
            }
        }


        // ====================================================
        // INVITATIONS
        // ====================================================

        composable("invites") {

            MainLayout(
                navController = navController,
                selectedRoute = "invites"
            ) {

                InvitationsScreen()
            }
        }


        // ====================================================
        // PROFILE
        // ====================================================

        composable("profile") {

            MainLayout(
                navController = navController,
                selectedRoute = "profile"
            ) {

                ProfileScreen(

                    onLogout = {

                        navController.navigate("login") {

                            popUpTo(0)
                        }
                    }
                )
            }
        }


        // ====================================================
        // CREATE EVENT
        // ====================================================

        composable("create") {

            CreateEventScreen(

                onBack = {

                    navController.popBackStack()
                }
            )
        }


        // ====================================================
        // EVENT DETAIL
        // ====================================================

        composable("detail") {

            val event =
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.get<FamilyEvent>("event")

            if (event != null) {

                EventDetailScreen(

                    event = event,

                    onBack = {

                        navController.popBackStack()
                    }
                )
            }
        }
    }
}


// ============================================================
// MAIN LAYOUT
// ============================================================

@Composable
fun MainLayout(
    navController: NavHostController,
    selectedRoute: String,
    content: @Composable () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Cream)
    ) {

        Box(
            modifier = Modifier.weight(1f)
        ) {

            content()
        }

        AhlnaBottomBar(

            selectedRoute = selectedRoute,

            onNavigate = { route ->

                navController.navigate(route) {

                    popUpTo("home") {

                        saveState = true
                    }

                    launchSingleTop = true

                    restoreState = true
                }
            }
        )
    }
}


// ============================================================
// BOTTOM NAVIGATION
// ============================================================

@Composable
fun AhlnaBottomBar(
    selectedRoute: String,
    onNavigate: (String) -> Unit
) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding(),

        color = White,

        shadowElevation = 10.dp
    ) {

        Row(

            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 8.dp,
                    vertical = 10.dp
                ),

            horizontalArrangement =
                Arrangement.SpaceEvenly,

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            BottomItem(
                icon = Icons.Default.Home,
                label = "Home",
                selected =
                    selectedRoute == "home"
            ) {

                onNavigate("home")
            }

            BottomItem(
                icon = Icons.Default.Event,
                label = "Events",
                selected =
                    selectedRoute == "events"
            ) {

                onNavigate("events")
            }

            BottomItem(
                icon = Icons.Default.CalendarMonth,
                label = "Calendar",
                selected =
                    selectedRoute == "calendar"
            ) {

                onNavigate("calendar")
            }

            BottomItem(
                icon = Icons.Default.Mail,
                label = "Invites",
                selected =
                    selectedRoute == "invites"
            ) {

                onNavigate("invites")
            }

            BottomItem(
                icon = Icons.Default.Person,
                label = "Profile",
                selected =
                    selectedRoute == "profile"
            ) {

                onNavigate("profile")
            }
        }
    }
}


// ============================================================
// BOTTOM ITEM
// ============================================================

@Composable
fun BottomItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    Column(

        modifier = Modifier
            .clip(
                RoundedCornerShape(14.dp)
            )
            .clickable {
                onClick()
            }
            .padding(
                horizontal = 10.dp,
                vertical = 5.dp
            ),

        horizontalAlignment =
            Alignment.CenterHorizontally
    ) {

        Icon(

            imageVector = icon,

            contentDescription = label,

            tint =
                if (selected)
                    Forest
                else
                    Muted,

            modifier =
                Modifier.size(22.dp)
        )

        Text(

            text = label,

            fontSize = 10.sp,

            fontWeight =
                if (selected)
                    FontWeight.Bold
                else
                    FontWeight.Normal,

            color =
                if (selected)
                    Forest
                else
                    Muted
        )
    }
}


// ============================================================
// LOGIN SCREEN
// ============================================================

@Composable
fun LoginScreen(
    onLogin: () -> Unit,
    onRegister: () -> Unit
) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    var emailError by remember {
        mutableStateOf("")
    }

    var passwordError by remember {
        mutableStateOf("")
    }

    var forgotMessage by remember {
        mutableStateOf(false)
    }


    fun validateLogin(): Boolean {

        var valid = true

        emailError = ""
        passwordError = ""

        if (email.isBlank()) {

            emailError = "Email is required"

            valid = false

        } else if (
            !android.util.Patterns.EMAIL_ADDRESS
                .matcher(email.trim())
                .matches()
        ) {

            emailError =
                "Enter a valid email address"

            valid = false
        }

        if (password.isBlank()) {

            passwordError =
                "Password is required"

            valid = false

        } else if (password.length < 6) {

            passwordError =
                "Password must contain at least 6 characters"

            valid = false
        }

        return valid
    }


    val loginEnabled =
        email.isNotBlank() &&
                password.isNotBlank()


    LazyColumn(

        modifier = Modifier
            .fillMaxSize()
            .background(Cream),

        contentPadding = PaddingValues(
            start = 24.dp,
            end = 24.dp,
            top = 20.dp,
            bottom = 30.dp
        ),

        horizontalAlignment =
            Alignment.CenterHorizontally
    ) {

        item {

            // ------------------------------------------------
            // IMAGE
            // ------------------------------------------------

            Box(

                modifier = Modifier
                    .fillMaxWidth()
                    .height(245.dp)
                    .clip(
                        RoundedCornerShape(28.dp)
                    )
            ) {

                Image(

                    painter =
                        painterResource(
                            R.drawable.login_family
                        ),

                    contentDescription =
                        "Ahlna family",

                    modifier =
                        Modifier.fillMaxSize(),

                    contentScale =
                        ContentScale.Crop
                )

                Box(

                    modifier = Modifier
                        .fillMaxSize()
                        .background(

                            Brush.verticalGradient(

                                listOf(
                                    Color.Transparent,
                                    ForestDark.copy(
                                        alpha = 0.75f
                                    )
                                )
                            )
                        )
                )

                Column(

                    modifier = Modifier
                        .align(
                            Alignment.BottomStart
                        )
                        .padding(20.dp)
                ) {

                    Text(

                        text = "AHLNA",

                        color = White,

                        fontSize = 31.sp,

                        fontWeight =
                            FontWeight.ExtraBold
                    )

                    Text(

                        text =
                            "Celebrate together",

                        color = GoldLight,

                        fontSize = 14.sp,

                        fontWeight =
                            FontWeight.SemiBold
                    )
                }
            }


            Spacer(
                modifier =
                    Modifier.height(28.dp)
            )


            // ------------------------------------------------
            // WELCOME
            // ------------------------------------------------

            Column(
                modifier =
                    Modifier.fillMaxWidth()
            ) {

                Text(

                    text =
                        "Welcome back 👋",

                    fontSize = 27.sp,

                    fontWeight =
                        FontWeight.Bold,

                    color = Ink
                )

                Spacer(
                    modifier =
                        Modifier.height(5.dp)
                )

                Text(

                    text =
                        "Sign in to continue planning beautiful family moments.",

                    fontSize = 14.sp,

                    color = Muted,

                    lineHeight = 20.sp
                )
            }


            Spacer(
                modifier =
                    Modifier.height(25.dp)
            )


            // ------------------------------------------------
            // EMAIL
            // ------------------------------------------------

            OutlinedTextField(

                value = email,

                onValueChange = {

                    email = it

                    emailError = ""
                },

                label = {
                    Text("Email address")
                },

                placeholder = {
                    Text("example@email.com")
                },

                leadingIcon = {

                    Icon(

                        imageVector =
                            Icons.Default.Mail,

                        contentDescription =
                            "Email",

                        tint =
                            if (
                                emailError.isNotEmpty()
                            )
                                ErrorRed
                            else
                                Forest
                    )
                },

                modifier =
                    Modifier.fillMaxWidth(),

                shape =
                    RoundedCornerShape(16.dp),

                singleLine = true,

                isError =
                    emailError.isNotEmpty(),

                colors =
                    OutlinedTextFieldDefaults.colors(

                        focusedBorderColor =
                            if (
                                emailError.isNotEmpty()
                            )
                                ErrorRed
                            else
                                Forest,

                        unfocusedBorderColor =
                            if (
                                emailError.isNotEmpty()
                            )
                                ErrorRed
                            else
                                CreamDark,

                        focusedLabelColor =
                            Forest,

                        cursorColor =
                            Forest,

                        focusedContainerColor =
                            White,

                        unfocusedContainerColor =
                            White
                    ),

                supportingText = {

                    if (
                        emailError.isNotEmpty()
                    ) {

                        Text(
                            text = emailError,
                            color = ErrorRed,
                            fontSize = 12.sp
                        )
                    }
                }
            )


            Spacer(
                modifier =
                    Modifier.height(10.dp)
            )


            // ------------------------------------------------
            // PASSWORD
            // ------------------------------------------------

            OutlinedTextField(

                value = password,

                onValueChange = {

                    password = it

                    passwordError = ""
                },

                label = {
                    Text("Password")
                },

                placeholder = {
                    Text("Enter your password")
                },

                leadingIcon = {

                    Icon(

                        imageVector =
                            Icons.Default.Visibility,

                        contentDescription =
                            "Password",

                        tint =
                            if (
                                passwordError.isNotEmpty()
                            )
                                ErrorRed
                            else
                                Forest
                    )
                },

                trailingIcon = {

                    IconButton(

                        onClick = {

                            passwordVisible =
                                !passwordVisible
                        }
                    ) {

                        Icon(

                            imageVector =
                                if (passwordVisible)
                                    Icons.Default.VisibilityOff
                                else
                                    Icons.Default.Visibility,

                            contentDescription =
                                "Password visibility",

                            tint = Muted
                        )
                    }
                },

                modifier =
                    Modifier.fillMaxWidth(),

                shape =
                    RoundedCornerShape(16.dp),

                singleLine = true,

                visualTransformation =
                    if (passwordVisible)
                        VisualTransformation.None
                    else
                        PasswordVisualTransformation(),

                isError =
                    passwordError.isNotEmpty(),

                colors =
                    OutlinedTextFieldDefaults.colors(

                        focusedBorderColor =
                            if (
                                passwordError.isNotEmpty()
                            )
                                ErrorRed
                            else
                                Forest,

                        unfocusedBorderColor =
                            if (
                                passwordError.isNotEmpty()
                            )
                                ErrorRed
                            else
                                CreamDark,

                        focusedLabelColor =
                            Forest,

                        cursorColor =
                            Forest,

                        focusedContainerColor =
                            White,

                        unfocusedContainerColor =
                            White
                    ),

                supportingText = {

                    if (
                        passwordError.isNotEmpty()
                    ) {

                        Text(

                            text =
                                passwordError,

                            color =
                                ErrorRed,

                            fontSize =
                                12.sp
                        )
                    }
                }
            )


            // ------------------------------------------------
            // FORGOT PASSWORD
            // ------------------------------------------------

            Row(

                modifier =
                    Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.End
            ) {

                TextButton(

                    onClick = {

                        forgotMessage = true
                    }
                ) {

                    Text(

                        text =
                            "Forgot password?",

                        color =
                            Forest,

                        fontSize =
                            13.sp,

                        fontWeight =
                            FontWeight.SemiBold
                    )
                }
            }


            if (forgotMessage) {

                Box(

                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(14.dp)
                        )
                        .background(
                            GoldLight
                        )
                        .padding(13.dp)
                ) {

                    Text(

                        text =
                            "Password recovery will be available soon.",

                        color =
                            ForestDark,

                        fontSize =
                            12.sp,

                        fontWeight =
                            FontWeight.SemiBold
                    )
                }

                Spacer(
                    modifier =
                        Modifier.height(12.dp)
                )
            }


            // ------------------------------------------------
            // SIGN IN
            // ------------------------------------------------

            Button(

                onClick = {

                    if (validateLogin()) {

                        onLogin()
                    }
                },

                enabled =
                    loginEnabled,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),

                shape =
                    RoundedCornerShape(16.dp),

                colors =
                    ButtonDefaults.buttonColors(

                        containerColor =
                            Forest,

                        contentColor =
                            White,

                        disabledContainerColor =
                            CreamDark,

                        disabledContentColor =
                            Muted
                    )
            ) {

                Text(

                    text = "Sign In",

                    fontSize = 16.sp,

                    fontWeight =
                        FontWeight.Bold
                )
            }


            Spacer(
                modifier =
                    Modifier.height(18.dp)
            )


            // ------------------------------------------------
            // OR
            // ------------------------------------------------

            Row(

                modifier =
                    Modifier.fillMaxWidth(),

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Box(

                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .background(
                            CreamDark
                        )
                )

                Text(

                    text = "  OR  ",

                    color = Muted,

                    fontSize = 12.sp
                )

                Box(

                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .background(
                            CreamDark
                        )
                )
            }


            Spacer(
                modifier =
                    Modifier.height(16.dp)
            )


            // ------------------------------------------------
            // REGISTER
            // ------------------------------------------------

            Row(

                modifier =
                    Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.Center,

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Text(

                    text =
                        "Don't have an account?",

                    color =
                        Muted,

                    fontSize =
                        13.sp
                )

                TextButton(
                    onClick =
                        onRegister
                ) {

                    Text(

                        text =
                            "Create Account",

                        color =
                            Forest,

                        fontSize =
                            13.sp,

                        fontWeight =
                            FontWeight.Bold
                    )
                }
            }


            Spacer(
                modifier =
                    Modifier.height(8.dp)
            )


            Text(

                text =
                    "Bring your family moments together ✨",

                color =
                    Muted,

                fontSize =
                    11.sp
            )
        }
    }
}


// ============================================================
// REGISTER SCREEN
// ============================================================

@Composable
fun RegisterScreen(
    onRegister: () -> Unit,
    onBack: () -> Unit
) {

    var name by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var confirmPassword by remember {
        mutableStateOf("")
    }


    LazyColumn(

        modifier = Modifier
            .fillMaxSize()
            .background(Cream),

        contentPadding =
            PaddingValues(24.dp)
    ) {

        item {

            IconButton(
                onClick = onBack
            ) {

                Icon(
                    imageVector =
                        Icons.Default.ArrowBack,

                    contentDescription =
                        "Back",

                    tint =
                        ForestDark
                )
            }

            Image(

                painter =
                    painterResource(
                        R.drawable.family_celebration
                    ),

                contentDescription =
                    "Family Celebration",

                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(
                        RoundedCornerShape(24.dp)
                    ),

                contentScale =
                    ContentScale.Crop
            )

            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )

            Text(
                text =
                    "Create Account",

                fontSize =
                    27.sp,

                fontWeight =
                    FontWeight.Bold,

                color =
                    Ink
            )

            Text(
                text =
                    "Start creating beautiful family memories",

                color =
                    Muted,

                fontSize =
                    14.sp
            )

            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )

            AhlnaTextField(
                value = name,
                onValueChange = {
                    name = it
                },
                label = "Full Name"
            )

            Spacer(
                modifier =
                    Modifier.height(12.dp)
            )

            AhlnaTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                label = "Email"
            )

            Spacer(
                modifier =
                    Modifier.height(12.dp)
            )

            AhlnaTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                label = "Password"
            )

            Spacer(
                modifier =
                    Modifier.height(12.dp)
            )

            AhlnaTextField(
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                },
                label = "Confirm Password"
            )

            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )

            PrimaryButton(
                text = "Create Account",
                onClick = onRegister
            )
        }
    }
}


// ============================================================
// HOME SCREEN
// ============================================================

@Composable
fun HomeScreen(
    onCreateEvent: () -> Unit,
    onEventClick: (FamilyEvent) -> Unit,
    onViewAll: () -> Unit
) {

    LazyColumn(

        modifier =
            Modifier.fillMaxSize(),

        contentPadding =
            PaddingValues(
                horizontal = 22.dp,
                vertical = 22.dp
            ),

        verticalArrangement =
            Arrangement.spacedBy(18.dp)
    ) {

        item {

            Text(
                text =
                    "Good morning, Poojitha 👋",

                fontSize =
                    14.sp,

                color =
                    Muted
            )

            Text(
                text =
                    "Let's celebrate together",

                fontSize =
                    27.sp,

                fontWeight =
                    FontWeight.Bold,

                color =
                    Ink
            )

            Spacer(
                modifier =
                    Modifier.height(16.dp)
            )
        }


        item {

            Box(

                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp)
                    .clip(
                        RoundedCornerShape(25.dp)
                    )
            ) {

                Image(

                    painter =
                        painterResource(
                            R.drawable.family_celebration
                        ),

                    contentDescription =
                        "Family Celebration",

                    modifier =
                        Modifier.fillMaxSize(),

                    contentScale =
                        ContentScale.Crop
                )

                Box(

                    modifier = Modifier
                        .fillMaxSize()
                        .background(

                            Brush.verticalGradient(

                                listOf(
                                    Color.Transparent,
                                    ForestDark.copy(
                                        alpha = 0.85f
                                    )
                                )
                            )
                        )
                )

                Column(

                    modifier = Modifier
                        .align(
                            Alignment.BottomStart
                        )
                        .padding(20.dp)
                ) {

                    Text(

                        text =
                            "Make every moment special",

                        color =
                            White,

                        fontSize =
                            23.sp,

                        fontWeight =
                            FontWeight.Bold
                    )

                    Spacer(
                        modifier =
                            Modifier.height(5.dp)
                    )

                    Text(

                        text =
                            "Plan • Invite • Celebrate",

                        color =
                            GoldLight,

                        fontSize =
                            14.sp
                    )
                }
            }
        }


        item {

            Row(

                modifier =
                    Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.SpaceBetween,

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Text(

                    text =
                        "Upcoming Events",

                    fontSize =
                        21.sp,

                    fontWeight =
                        FontWeight.Bold,

                    color =
                        Ink
                )

                TextButton(
                    onClick =
                        onViewAll
                ) {

                    Text(
                        text =
                            "View all",

                        color =
                            Forest
                    )
                }
            }
        }


        items(
            sampleEvents.take(2)
        ) { event ->

            EventCard(

                event = event,

                onClick = {
                    onEventClick(event)
                }
            )
        }


        item {

            PrimaryButton(

                text =
                    "+  Create New Event",

                onClick =
                    onCreateEvent
            )
        }
    }
}


// ============================================================
// EVENTS SCREEN
// ============================================================

@Composable
fun EventsScreen(
    onEventClick: (FamilyEvent) -> Unit
) {

    var selectedCategory by remember {
        mutableStateOf("All")
    }

    val categories =
        listOf(
            "All",
            "Dinner",
            "Birthday",
            "Wedding",
            "Ceremony"
        )

    val filteredEvents =
        if (selectedCategory == "All")
            sampleEvents
        else
            sampleEvents.filter {
                it.category ==
                        selectedCategory
            }


    LazyColumn(

        modifier =
            Modifier.fillMaxSize(),

        contentPadding =
            PaddingValues(22.dp),

        verticalArrangement =
            Arrangement.spacedBy(16.dp)
    ) {

        item {

            ScreenHeader(
                title =
                    "Events",

                subtitle =
                    "Your family celebrations"
            )

            Spacer(
                modifier =
                    Modifier.height(12.dp)
            )


            // =================================================
            // HORIZONTAL CATEGORY BAR
            // =================================================

            Row(

                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(
                        rememberScrollState()
                    ),

                horizontalArrangement =
                    Arrangement.spacedBy(8.dp),

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                categories.forEach { category ->

                    CategoryChip(

                        text =
                            category,

                        selected =
                            selectedCategory ==
                                    category,

                        onClick = {

                            selectedCategory =
                                category
                        }
                    )
                }
            }
        }


        items(filteredEvents) { event ->

            EventCard(

                event = event,

                onClick = {
                    onEventClick(event)
                }
            )
        }
    }
}


// ============================================================
// EVENT CARD
// ============================================================

@Composable
fun EventCard(
    event: FamilyEvent,
    onClick: () -> Unit
) {

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },

        shape =
            RoundedCornerShape(22.dp),

        colors =
            CardDefaults.cardColors(
                containerColor =
                    White
            ),

        elevation =
            CardDefaults.cardElevation(
                defaultElevation =
                    3.dp
            )
    ) {

        Column {

            Image(

                painter =
                    painterResource(
                        event.image
                    ),

                contentDescription =
                    event.title,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
                    .clip(

                        RoundedCornerShape(

                            topStart = 22.dp,
                            topEnd = 22.dp
                        )
                    ),

                contentScale =
                    ContentScale.Crop
            )

            Column(

                modifier =
                    Modifier.padding(16.dp)
            ) {

                Row(

                    modifier =
                        Modifier.fillMaxWidth(),

                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Column(

                        modifier =
                            Modifier.weight(1f)
                    ) {

                        Text(

                            text =
                                event.title,

                            fontSize =
                                19.sp,

                            fontWeight =
                                FontWeight.Bold,

                            color =
                                Ink
                        )

                        Spacer(
                            modifier =
                                Modifier.height(5.dp)
                        )

                        Text(
                            text =
                                event.date,

                            fontSize =
                                13.sp,

                            color =
                                Muted
                        )

                        Text(
                            text =
                                event.time,

                            fontSize =
                                13.sp,

                            color =
                                Muted
                        )
                    }

                    Box(

                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(
                                    12.dp
                                )
                            )
                            .background(
                                GoldLight
                            )
                            .padding(
                                horizontal =
                                    10.dp,

                                vertical =
                                    7.dp
                            )
                    ) {

                        Text(

                            text =
                                event.category,

                            color =
                                ForestDark,

                            fontSize =
                                11.sp,

                            fontWeight =
                                FontWeight.Bold
                        )
                    }
                }

                Spacer(
                    modifier =
                        Modifier.height(10.dp)
                )

                Text(

                    text =
                        event.venue,

                    fontSize =
                        13.sp,

                    color =
                        Muted
                )
            }
        }
    }
}


// ============================================================
// EVENT DETAIL
// ============================================================

@Composable
fun EventDetailScreen(
    event: FamilyEvent,
    onBack: () -> Unit
) {

    var joined by remember {
        mutableStateOf(false)
    }

    LazyColumn(

        modifier =
            Modifier
                .fillMaxSize()
                .background(Cream)
    ) {

        item {

            Box(

                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {

                Image(

                    painter =
                        painterResource(
                            event.image
                        ),

                    contentDescription =
                        event.title,

                    modifier =
                        Modifier.fillMaxSize(),

                    contentScale =
                        ContentScale.Crop
                )

                IconButton(

                    onClick =
                        onBack,

                    modifier = Modifier
                        .padding(16.dp)
                        .clip(CircleShape)
                        .background(
                            White.copy(
                                alpha = 0.9f
                            )
                        )
                ) {

                    Icon(

                        imageVector =
                            Icons.Default.ArrowBack,

                        contentDescription =
                            "Back",

                        tint =
                            ForestDark
                    )
                }
            }
        }


        item {

            Column(

                modifier =
                    Modifier.padding(22.dp)
            ) {

                Text(

                    text =
                        event.title,

                    fontSize =
                        28.sp,

                    fontWeight =
                        FontWeight.Bold,

                    color =
                        Ink
                )

                Spacer(
                    modifier =
                        Modifier.height(16.dp)
                )

                DetailRow(
                    label = "Date",
                    value = event.date
                )

                DetailRow(
                    label = "Time",
                    value = event.time
                )

                DetailRow(
                    label = "Venue",
                    value = event.venue
                )

                DetailRow(
                    label = "Category",
                    value = event.category
                )

                Spacer(
                    modifier =
                        Modifier.height(20.dp)
                )

                PrimaryButton(

                    text =
                        if (joined)
                            "✓ Joined Celebration"
                        else
                            "Join Celebration",

                    onClick = {
                        joined = !joined
                    }
                )
            }
        }
    }
}


// ============================================================
// CALENDAR SCREEN
// ONE MONTH + MONTH DROPDOWN
// ============================================================

@Composable
fun CalendarScreen() {

    var selectedDay by remember {
        mutableStateOf(28)
    }

    // September = 8
    var selectedMonth by remember {
        mutableStateOf(8)
    }

    var monthMenuExpanded by remember {
        mutableStateOf(false)
    }

    val year = 2026


    // ========================================================
    // EVENT DATES
    // ========================================================

    val eventDates =
        setOf(
            "9-28",
            "9-30",
            "10-5",
            "10-15",
            "11-4"
        )


    // ========================================================
    // MONTH NAMES
    // ========================================================

    val monthNames =
        listOf(
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
        )


    LazyColumn(

        modifier =
            Modifier.fillMaxSize(),

        contentPadding =
            PaddingValues(
                horizontal = 18.dp,
                vertical = 22.dp
            ),

        verticalArrangement =
            Arrangement.spacedBy(18.dp)
    ) {

        // ====================================================
        // HEADER
        // ====================================================

        item {

            ScreenHeader(

                title =
                    "Calendar",

                subtitle =
                    "Plan your family celebrations"
            )
        }


        // ====================================================
        // YEAR CARD
        // ====================================================

        item {

            Card(

                modifier =
                    Modifier.fillMaxWidth(),

                shape =
                    RoundedCornerShape(22.dp),

                colors =
                    CardDefaults.cardColors(
                        containerColor =
                            Forest
                    )
            ) {

                Column(

                    modifier =
                        Modifier.padding(20.dp)
                ) {

                    Text(

                        text =
                            "2026",

                        color =
                            White,

                        fontSize =
                            30.sp,

                        fontWeight =
                            FontWeight.Bold
                    )

                    Spacer(
                        modifier =
                            Modifier.height(5.dp)
                    )

                    Text(

                        text =
                            "12 months • 365 days • Family memories",

                        color =
                            GoldLight,

                        fontSize =
                            13.sp
                    )
                }
            }
        }


        // ====================================================
        // MONTH DROPDOWN
        // ====================================================

        item {

            Box(
                modifier =
                    Modifier.fillMaxWidth()
            ) {

                Card(

                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {

                            monthMenuExpanded =
                                true
                        },

                    shape =
                        RoundedCornerShape(18.dp),

                    colors =
                        CardDefaults.cardColors(
                            containerColor =
                                White
                        ),

                    elevation =
                        CardDefaults.cardElevation(
                            defaultElevation =
                                2.dp
                        )
                ) {

                    Row(

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal =
                                    18.dp,

                                vertical =
                                    15.dp
                            ),

                        verticalAlignment =
                            Alignment.CenterVertically,

                        horizontalArrangement =
                            Arrangement.SpaceBetween
                    ) {

                        Column {

                            Text(

                                text =
                                    "Selected Month",

                                fontSize =
                                    11.sp,

                                color =
                                    Muted
                            )

                            Spacer(
                                modifier =
                                    Modifier.height(2.dp)
                            )

                            Text(

                                text =
                                    monthNames[
                                        selectedMonth
                                    ],

                                fontSize =
                                    20.sp,

                                fontWeight =
                                    FontWeight.Bold,

                                color =
                                    ForestDark
                            )
                        }


                        Text(

                            text =
                                "▼",

                            fontSize =
                                16.sp,

                            color =
                                Forest
                        )
                    }
                }


                // =================================================
                // DROPDOWN
                // =================================================

                DropdownMenu(

                    expanded =
                        monthMenuExpanded,

                    onDismissRequest = {

                        monthMenuExpanded =
                            false
                    }
                ) {

                    monthNames.forEachIndexed {

                            index,
                            month ->

                        DropdownMenuItem(

                            text = {

                                Text(

                                    text =
                                        month,

                                    color =
                                        if (
                                            selectedMonth ==
                                            index
                                        )
                                            Forest
                                        else
                                            Ink,

                                    fontWeight =
                                        if (
                                            selectedMonth ==
                                            index
                                        )
                                            FontWeight.Bold
                                        else
                                            FontWeight.Normal
                                )
                            },

                            onClick = {

                                selectedMonth =
                                    index

                                monthMenuExpanded =
                                    false

                                selectedDay =
                                    1
                            }
                        )
                    }
                }
            }
        }


        // ====================================================
        // SINGLE MONTH CALENDAR
        // ====================================================

        item {

            FullMonthCalendar(

                year =
                    year,

                monthIndex =
                    selectedMonth,

                monthName =
                    monthNames[
                        selectedMonth
                    ],

                eventDates =
                    eventDates,

                selectedDay =
                    selectedDay,

                selectedMonth =
                    selectedMonth,

                onDateClick = { day ->

                    selectedDay =
                        day
                }
            )
        }


        // ====================================================
        // SELECTED DATE
        // ====================================================

        item {

            SelectedCalendarEvent(

                year =
                    year,

                monthIndex =
                    selectedMonth,

                day =
                    selectedDay
            )
        }


        // ====================================================
        // UPCOMING EVENTS
        // ====================================================

        item {

            Text(

                text =
                    "Upcoming Events",

                fontSize =
                    21.sp,

                fontWeight =
                    FontWeight.Bold,

                color =
                    Ink
            )
        }


        items(sampleEvents) { event ->

            SmallEventRow(event)
        }
    }
}


// ============================================================
// SINGLE MONTH CALENDAR
// ============================================================

@Composable
fun FullMonthCalendar(

    year: Int,

    monthIndex: Int,

    monthName: String,

    eventDates: Set<String>,

    selectedDay: Int,

    selectedMonth: Int,

    onDateClick: (Int) -> Unit
) {

    val calendar =
        Calendar.getInstance()

    calendar.set(
        year,
        monthIndex,
        1
    )


    // ========================================================
    // FIRST DAY
    // ========================================================

    val firstDay =
        calendar.get(Calendar.DAY_OF_WEEK)


    // ========================================================
    // DAYS IN MONTH
    // ========================================================

    val daysInMonth =
        calendar.getActualMaximum(
            Calendar.DAY_OF_MONTH
        )


    // ========================================================
    // MONDAY-FIRST INDEX
    // ========================================================

    val firstDayMondayIndex =
        (firstDay + 5) % 7


    // ========================================================
    // DATE LIST
    // ========================================================

    val dates =
        mutableListOf<Int?>()


    repeat(firstDayMondayIndex) {

        dates.add(null)
    }


    for (day in 1..daysInMonth) {

        dates.add(day)
    }


    // ========================================================
    // CALENDAR CARD
    // ========================================================

    Card(

        modifier =
            Modifier.fillMaxWidth(),

        shape =
            RoundedCornerShape(22.dp),

        colors =
            CardDefaults.cardColors(
                containerColor =
                    White
            ),

        elevation =
            CardDefaults.cardElevation(
                defaultElevation =
                    3.dp
            )
    ) {

        Column(

            modifier =
                Modifier.padding(16.dp)
        ) {

            // =================================================
            // MONTH HEADER
            // =================================================

            Row(

                modifier =
                    Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.SpaceBetween,

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Column {

                    Text(

                        text =
                            monthName,

                        fontSize =
                            23.sp,

                        fontWeight =
                            FontWeight.Bold,

                        color =
                            ForestDark
                    )

                    Text(

                        text =
                            year.toString(),

                        fontSize =
                            12.sp,

                        color =
                            Muted
                    )
                }


                Box(

                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(
                                12.dp
                            )
                        )
                        .background(
                            GoldLight
                        )
                        .padding(
                            horizontal =
                                11.dp,

                            vertical =
                                7.dp
                        )
                ) {

                    Text(

                        text =
                            "$daysInMonth days",

                        color =
                            ForestDark,

                        fontSize =
                            11.sp,

                        fontWeight =
                            FontWeight.Bold
                    )
                }
            }


            Spacer(
                modifier =
                    Modifier.height(18.dp)
            )


            // =================================================
            // WEEK DAYS
            // =================================================

            Row(

                modifier =
                    Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.SpaceEvenly
            ) {

                listOf(
                    "Mon",
                    "Tue",
                    "Wed",
                    "Thu",
                    "Fri",
                    "Sat",
                    "Sun"
                ).forEach { day ->

                    Box(

                        modifier =
                            Modifier.weight(1f),

                        contentAlignment =
                            Alignment.Center
                    ) {

                        Text(

                            text =
                                day,

                            fontSize =
                                10.sp,

                            fontWeight =
                                FontWeight.Bold,

                            color =
                                Muted
                        )
                    }
                }
            }


            Spacer(
                modifier =
                    Modifier.height(8.dp)
            )


            // =================================================
            // DATES
            // =================================================

            dates.chunked(7).forEach { week ->

                Row(

                    modifier =
                        Modifier.fillMaxWidth(),

                    horizontalArrangement =
                        Arrangement.SpaceEvenly
                ) {

                    repeat(7) { index ->

                        val day =
                            if (
                                index < week.size
                            )
                                week[index]
                            else
                                null


                        Box(

                            modifier =
                                Modifier.weight(1f),

                            contentAlignment =
                                Alignment.Center
                        ) {

                            if (day != null) {

                                val dateKey =
                                    "${monthIndex + 1}-$day"


                                val hasEvent =
                                    eventDates.contains(
                                        dateKey
                                    )


                                val selected =
                                    selectedMonth ==
                                            monthIndex &&
                                            selectedDay ==
                                            day


                                Column(

                                    horizontalAlignment =
                                        Alignment.CenterHorizontally
                                ) {

                                    // =================================
                                    // DATE CIRCLE
                                    // =================================

                                    Box(

                                        modifier =
                                            Modifier
                                                .size(36.dp)
                                                .clip(
                                                    CircleShape
                                                )
                                                .background(

                                                    when {

                                                        selected ->
                                                            Forest

                                                        hasEvent ->
                                                            GoldLight

                                                        else ->
                                                            Color.Transparent
                                                    }
                                                )
                                                .clickable {

                                                    onDateClick(
                                                        day
                                                    )
                                                },

                                        contentAlignment =
                                            Alignment.Center
                                    ) {

                                        Text(

                                            text =
                                                day.toString(),

                                            fontSize =
                                                12.sp,

                                            fontWeight =
                                                if (
                                                    selected ||
                                                    hasEvent
                                                )
                                                    FontWeight.Bold
                                                else
                                                    FontWeight.Normal,

                                            color =
                                                when {

                                                    selected ->
                                                        White

                                                    hasEvent ->
                                                        ForestDark

                                                    else ->
                                                        Ink
                                                }
                                        )
                                    }


                                    // =================================
                                    // EVENT DOT
                                    // =================================

                                    if (hasEvent) {

                                        Box(

                                            modifier =
                                                Modifier
                                                    .padding(
                                                        top = 2.dp
                                                    )
                                                    .size(4.dp)
                                                    .clip(
                                                        CircleShape
                                                    )
                                                    .background(
                                                        Gold
                                                    )
                                        )

                                    } else {

                                        Spacer(

                                            modifier =
                                                Modifier.height(
                                                    6.dp
                                                )
                                        )
                                    }
                                }

                            } else {

                                Spacer(

                                    modifier =
                                        Modifier.height(
                                            42.dp
                                        )
                                )
                            }
                        }
                    }
                }


                Spacer(
                    modifier =
                        Modifier.height(5.dp)
                )
            }
        }
    }
}


// ============================================================
// SELECTED CALENDAR EVENT
// ============================================================

@Composable
fun SelectedCalendarEvent(

    year: Int,

    monthIndex: Int,

    day: Int
) {

    val monthNames =
        listOf(
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
        )


    val selectedKey =
        "${monthIndex + 1}-$day"


    val event =
        when (selectedKey) {

            "9-28" ->
                "Family Dinner"

            "9-30" ->
                "Wedding"

            "10-5" ->
                "Birthday Celebration"

            "10-15" ->
                "Wedding Anniversary"

            "11-4" ->
                "Funeral Ceremony"

            else ->
                ""
        }


    Card(

        modifier =
            Modifier.fillMaxWidth(),

        shape =
            RoundedCornerShape(20.dp),

        colors =
            CardDefaults.cardColors(
                containerColor =
                    ForestLight
            )
    ) {

        Row(

            modifier =
                Modifier.padding(16.dp),

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Box(

                modifier = Modifier
                    .size(50.dp)
                    .clip(
                        RoundedCornerShape(
                            14.dp
                        )
                    )
                    .background(
                        Forest
                    ),

                contentAlignment =
                    Alignment.Center
            ) {

                Text(

                    text =
                        day.toString(),

                    color =
                        White,

                    fontSize =
                        17.sp,

                    fontWeight =
                        FontWeight.Bold
                )
            }


            Spacer(
                modifier =
                    Modifier.width(14.dp)
            )


            Column {

                Text(

                    text =
                        "${monthNames[monthIndex]} $day, $year",

                    fontSize =
                        14.sp,

                    color =
                        Muted
                )

                Spacer(
                    modifier =
                        Modifier.height(3.dp)
                )

                Text(

                    text =
                        if (event.isNotEmpty())
                            event
                        else
                            "No event scheduled",

                    fontSize =
                        16.sp,

                    fontWeight =
                        FontWeight.Bold,

                    color =
                        Ink
                )

                Text(

                    text =
                        if (event.isNotEmpty())
                            "Family celebration"
                        else
                            "Enjoy your day!",

                    fontSize =
                        12.sp,

                    color =
                        Muted
                )
            }
        }
    }
}


// ============================================================
// INVITATIONS
// ============================================================

@Composable
fun InvitationsScreen() {

    var accepted by remember {
        mutableStateOf(setOf<Int>())
    }

    val invitations =
        listOf(
            "Family Reunion",
            "Cousin's Birthday",
            "Wedding"
        )


    LazyColumn(

        modifier =
            Modifier.fillMaxSize(),

        contentPadding =
            PaddingValues(22.dp),

        verticalArrangement =
            Arrangement.spacedBy(16.dp)
    ) {

        item {

            ScreenHeader(
                title =
                    "Invitations",

                subtitle =
                    "Celebrate together"
            )
        }


        items(
            invitations.indices.toList()
        ) { index ->

            Card(

                modifier =
                    Modifier.fillMaxWidth(),

                shape =
                    RoundedCornerShape(22.dp),

                colors =
                    CardDefaults.cardColors(
                        containerColor =
                            White
                    ),

                elevation =
                    CardDefaults.cardElevation(
                        defaultElevation =
                            2.dp
                    )
            ) {

                Column(

                    modifier =
                        Modifier.padding(12.dp)
                ) {

                    Image(

                        painter =
                            painterResource(
                                R.drawable.invitation
                            ),

                        contentDescription =
                            "Invitation",

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(145.dp)
                            .clip(
                                RoundedCornerShape(
                                    17.dp
                                )
                            ),

                        contentScale =
                            ContentScale.Crop
                    )

                    Spacer(
                        modifier =
                            Modifier.height(12.dp)
                    )

                    Text(

                        text =
                            invitations[index],

                        fontSize =
                            18.sp,

                        fontWeight =
                            FontWeight.Bold,

                        color =
                            Ink
                    )

                    Spacer(
                        modifier =
                            Modifier.height(4.dp)
                    )

                    Text(

                        text =
                            "You are invited to celebrate!",

                        fontSize =
                            13.sp,

                        color =
                            Muted
                    )

                    Spacer(
                        modifier =
                            Modifier.height(12.dp)
                    )

                    Button(

                        onClick = {

                            accepted =
                                if (
                                    accepted.contains(
                                        index
                                    )
                                ) {

                                    accepted - index

                                } else {

                                    accepted + index
                                }
                        },

                        modifier =
                            Modifier.fillMaxWidth(),

                        shape =
                            RoundedCornerShape(14.dp),

                        colors =
                            ButtonDefaults.buttonColors(

                                containerColor =
                                    if (
                                        accepted.contains(
                                            index
                                        )
                                    )
                                        Gold
                                    else
                                        Forest
                            )
                    ) {

                        Icon(

                            imageVector =
                                if (
                                    accepted.contains(
                                        index
                                    )
                                )
                                    Icons.Default.Check
                                else
                                    Icons.Default.Mail,

                            contentDescription =
                                null
                        )

                        Spacer(
                            modifier =
                                Modifier.width(7.dp)
                        )

                        Text(

                            text =
                                if (
                                    accepted.contains(
                                        index
                                    )
                                )
                                    "Accepted"
                                else
                                    "Accept Invitation"
                        )
                    }
                }
            }
        }
    }
}


// ============================================================
// PROFILE
// ============================================================

@Composable
fun ProfileScreen(
    onLogout: () -> Unit
) {

    var selectedOption by remember {
        mutableStateOf("")
    }


    LazyColumn(

        modifier =
            Modifier.fillMaxSize(),

        contentPadding =
            PaddingValues(22.dp),

        horizontalAlignment =
            Alignment.CenterHorizontally
    ) {

        item {

            Spacer(
                modifier =
                    Modifier.height(10.dp)
            )

            Image(

                painter =
                    painterResource(
                        R.drawable.profile
                    ),

                contentDescription =
                    "Profile",

                modifier = Modifier
                    .size(125.dp)
                    .clip(CircleShape),

                contentScale =
                    ContentScale.Crop
            )

            Spacer(
                modifier =
                    Modifier.height(14.dp)
            )

            Text(

                text =
                    "Poojitha",

                fontSize =
                    25.sp,

                fontWeight =
                    FontWeight.Bold,

                color =
                    Ink
            )

            Text(

                text =
                    "Family Event Organizer",

                fontSize =
                    14.sp,

                color =
                    Muted
            )

            Spacer(
                modifier =
                    Modifier.height(25.dp)
            )
        }


        item {

            ProfileOption(

                title =
                    "My Events",

                subtitle =
                    "View your created events",

                onClick = {

                    selectedOption =
                        "My Events"
                }
            )
        }


        item {

            ProfileOption(

                title =
                    "Notifications",

                subtitle =
                    "Manage your notifications",

                onClick = {

                    selectedOption =
                        "Notifications"
                }
            )
        }


        item {

            ProfileOption(

                title =
                    "Settings",

                subtitle =
                    "Customize your Ahlna experience",

                onClick = {

                    selectedOption =
                        "Settings"
                }
            )
        }


        item {

            if (
                selectedOption.isNotEmpty()
            ) {

                Box(

                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(
                                15.dp
                            )
                        )
                        .background(
                            ForestLight
                        )
                        .padding(14.dp)
                ) {

                    Text(

                        text =
                            "$selectedOption selected",

                        color =
                            ForestDark,

                        fontWeight =
                            FontWeight.SemiBold,

                        fontSize =
                            13.sp
                    )
                }
            }
        }


        item {

            Spacer(
                modifier =
                    Modifier.height(15.dp)
            )

            Button(

                onClick =
                    onLogout,

                modifier =
                    Modifier.fillMaxWidth(),

                shape =
                    RoundedCornerShape(15.dp),

                colors =
                    ButtonDefaults.buttonColors(
                        containerColor =
                            ForestDark
                    )
            ) {

                Text(

                    text =
                        "Logout",

                    fontWeight =
                        FontWeight.Bold
                )
            }
        }
    }
}


// ============================================================
// CREATE EVENT
// ============================================================

@Composable
fun CreateEventScreen(
    onBack: () -> Unit
) {

    var title by remember {
        mutableStateOf("")
    }

    var venue by remember {
        mutableStateOf("")
    }

    var date by remember {
        mutableStateOf("")
    }

    var time by remember {
        mutableStateOf("")
    }

    var created by remember {
        mutableStateOf(false)
    }


    LazyColumn(

        modifier = Modifier
            .fillMaxSize()
            .background(Cream),

        contentPadding =
            PaddingValues(22.dp),

        verticalArrangement =
            Arrangement.spacedBy(14.dp)
    ) {

        item {

            Row(

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                IconButton(
                    onClick =
                        onBack
                ) {

                    Icon(

                        imageVector =
                            Icons.Default.ArrowBack,

                        contentDescription =
                            "Back",

                        tint =
                            ForestDark
                    )
                }

                Text(

                    text =
                        "Create Event",

                    fontSize =
                        24.sp,

                    fontWeight =
                        FontWeight.Bold,

                    color =
                        Ink
                )
            }
        }


        item {

            Image(

                painter =
                    painterResource(
                        R.drawable.family_celebration
                    ),

                contentDescription =
                    "Create Event",

                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
                    .clip(
                        RoundedCornerShape(23.dp)
                    ),

                contentScale =
                    ContentScale.Crop
            )
        }


        item {

            Text(

                text =
                    "Create a beautiful memory",

                fontSize =
                    21.sp,

                fontWeight =
                    FontWeight.Bold,

                color =
                    ForestDark
            )
        }


        item {

            AhlnaTextField(
                value =
                    title,

                onValueChange = {
                    title = it
                },

                label =
                    "Event Name"
            )
        }


        item {

            AhlnaTextField(
                value =
                    date,

                onValueChange = {
                    date = it
                },

                label =
                    "Date"
            )
        }


        item {

            AhlnaTextField(
                value =
                    time,

                onValueChange = {
                    time = it
                },

                label =
                    "Time"
            )
        }


        item {

            AhlnaTextField(
                value =
                    venue,

                onValueChange = {
                    venue = it
                },

                label =
                    "Venue"
            )
        }


        item {

            PrimaryButton(

                text =
                    if (created)
                        "✓ Event Created"
                    else
                        "Create Event",

                onClick = {

                    if (
                        title.isNotBlank() &&
                        date.isNotBlank() &&
                        time.isNotBlank() &&
                        venue.isNotBlank()
                    ) {

                        created = true
                    }
                }
            )
        }


        item {

            if (created) {

                TextButton(

                    onClick =
                        onBack,

                    modifier =
                        Modifier.fillMaxWidth()
                ) {

                    Text(

                        text =
                            "Return to Events",

                        color =
                            Forest,

                        fontWeight =
                            FontWeight.Bold
                    )
                }
            }
        }
    }
}


// ============================================================
// SCREEN HEADER
// ============================================================

@Composable
fun ScreenHeader(
    title: String,
    subtitle: String
) {

    Column {

        Text(

            text =
                title,

            fontSize =
                28.sp,

            fontWeight =
                FontWeight.Bold,

            color =
                ForestDark
        )

        Spacer(
            modifier =
                Modifier.height(4.dp)
        )

        Text(

            text =
                subtitle,

            fontSize =
                14.sp,

            color =
                Muted
        )
    }
}


// ============================================================
// TEXT FIELD
// ============================================================

@Composable
fun AhlnaTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {

    OutlinedTextField(

        value =
            value,

        onValueChange =
            onValueChange,

        label = {
            Text(label)
        },

        modifier =
            Modifier.fillMaxWidth(),

        shape =
            RoundedCornerShape(15.dp),

        singleLine =
            true,

        colors =
            OutlinedTextFieldDefaults.colors(

                focusedBorderColor =
                    Forest,

                unfocusedBorderColor =
                    CreamDark,

                focusedLabelColor =
                    Forest,

                cursorColor =
                    Forest,

                focusedContainerColor =
                    White,

                unfocusedContainerColor =
                    White
            )
    )
}


// ============================================================
// PRIMARY BUTTON
// ============================================================

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit
) {

    Button(

        onClick =
            onClick,

        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),

        shape =
            RoundedCornerShape(15.dp),

        colors =
            ButtonDefaults.buttonColors(
                containerColor =
                    Forest
            )
    ) {

        Text(

            text =
                text,

            fontSize =
                15.sp,

            fontWeight =
                FontWeight.Bold
        )
    }
}


// ============================================================
// CATEGORY CHIP
// ============================================================

@Composable
fun CategoryChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    Box(

        modifier = Modifier
            .clip(
                RoundedCornerShape(50.dp)
            )
            .background(

                if (selected)
                    Forest
                else
                    White
            )
            .clickable {
                onClick()
            }
            .padding(
                horizontal =
                    15.dp,

                vertical =
                    9.dp
            )
    ) {

        Text(

            text =
                text,

            color =
                if (selected)
                    White
                else
                    ForestDark,

            fontSize =
                12.sp,

            fontWeight =
                FontWeight.SemiBold
        )
    }
}


// ============================================================
// DETAIL ROW
// ============================================================

@Composable
fun DetailRow(
    label: String,
    value: String
) {

    Row(

        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical =
                    9.dp
            ),

        verticalAlignment =
            Alignment.CenterVertically
    ) {

        Text(

            text =
                label,

            modifier =
                Modifier.weight(0.35f),

            color =
                Muted,

            fontSize =
                14.sp
        )

        Text(

            text =
                value,

            modifier =
                Modifier.weight(0.65f),

            color =
                Ink,

            fontSize =
                15.sp,

            fontWeight =
                FontWeight.SemiBold
        )
    }
}


// ============================================================
// SMALL EVENT ROW
// ============================================================

@Composable
fun SmallEventRow(
    event: FamilyEvent
) {

    Card(

        modifier =
            Modifier.fillMaxWidth(),

        shape =
            RoundedCornerShape(18.dp),

        colors =
            CardDefaults.cardColors(
                containerColor =
                    White
            )
    ) {

        Row(

            modifier =
                Modifier.padding(10.dp),

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Image(

                painter =
                    painterResource(
                        event.image
                    ),

                contentDescription =
                    event.title,

                modifier = Modifier
                    .size(75.dp)
                    .clip(
                        RoundedCornerShape(
                            14.dp
                        )
                    ),

                contentScale =
                    ContentScale.Crop
            )

            Spacer(
                modifier =
                    Modifier.width(14.dp)
            )

            Column(

                modifier =
                    Modifier.weight(1f)
            ) {

                Text(

                    text =
                        event.title,

                    fontWeight =
                        FontWeight.Bold,

                    color =
                        Ink
                )

                Text(

                    text =
                        event.date,

                    fontSize =
                        12.sp,

                    color =
                        Muted
                )
            }

            Icon(

                imageVector =
                    Icons.Default.ChevronRight,

                contentDescription =
                    null,

                tint =
                    Forest
            )
        }
    }
}


// ============================================================
// PROFILE OPTION
// ============================================================

@Composable
fun ProfileOption(
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical =
                    6.dp
            )
            .clickable {
                onClick()
            },

        shape =
            RoundedCornerShape(17.dp),

        colors =
            CardDefaults.cardColors(
                containerColor =
                    White
            )
    ) {

        Row(

            modifier =
                Modifier.padding(17.dp),

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Box(

                modifier = Modifier
                    .size(44.dp)
                    .clip(
                        RoundedCornerShape(
                            13.dp
                        )
                    )
                    .background(
                        ForestLight
                    ),

                contentAlignment =
                    Alignment.Center
            ) {

                Icon(

                    imageVector =
                        Icons.Default.Person,

                    contentDescription =
                        null,

                    tint =
                        Forest
                )
            }

            Spacer(
                modifier =
                    Modifier.width(14.dp)
            )

            Column(

                modifier =
                    Modifier.weight(1f)
            ) {

                Text(

                    text =
                        title,

                    fontWeight =
                        FontWeight.Bold,

                    color =
                        Ink
                )

                Text(

                    text =
                        subtitle,

                    fontSize =
                        12.sp,

                    color =
                        Muted
                )
            }

            Icon(

                imageVector =
                    Icons.Default.ChevronRight,

                contentDescription =
                    null,

                tint =
                    Muted
            )
        }
    }

}