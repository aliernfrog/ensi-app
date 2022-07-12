package com.aliernfrog.ensicord.ui.composable

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aliernfrog.ensicord.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EnsicordBaseScaffold(title: String, navController: NavController, state: ScaffoldState = rememberScaffoldState(), onNavigationClick: (() -> Unit)? = null, content: @Composable (ColumnScope.() -> Unit)) {
    Scaffold(
        scaffoldState = state,
        topBar = {
            TopAppBar(backgroundColor = MaterialTheme.colors.secondary, contentPadding = PaddingValues(horizontal = 24.dp)) {
                if (navController.previousBackStackEntry != null) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = LocalContext.current.getString(R.string.action_back), Modifier.padding(end = 24.dp).clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false),
                        onClick = {
                            if (onNavigationClick != null) onNavigationClick()
                            navController.navigateUp()
                        })
                    )
                }
                Text(text = title, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.onSecondary)
            }
        }
    ) {
        Column(Modifier.fillMaxWidth().verticalScroll(rememberScrollState()).padding(horizontal = 24.dp).animateContentSize()) {
            content()
            Spacer(Modifier.height(60.dp))
        }
    }
}