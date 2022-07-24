package com.aliernfrog.ensicord.ui.screen.chatScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aliernfrog.ensicord.NavDestinations
import com.aliernfrog.ensicord.R
import com.aliernfrog.ensicord.ui.composable.EnsicordChannel
import com.aliernfrog.ensicord.ui.composable.EnsicordBorderlessButton
import com.aliernfrog.ensicord.ui.composable.EnsicordUser
import com.aliernfrog.ensicord.model.ChatModel
import com.xinto.overlappingpanels.OverlappingPanelsState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun channelsPanel(chatModel: ChatModel, panelsState: OverlappingPanelsState, navController: NavController): @Composable (BoxScope.() -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    return {
        Column(Modifier.clip(RoundedCornerShape(topEnd = 20.dp)).fillMaxSize().background(MaterialTheme.colors.secondary)) {
            LazyColumn(Modifier.weight(1f)) {
                items(chatModel.channels) { channel ->
                    EnsicordChannel(channel, chosen = chatModel.chosenChannel == channel) {
                        if (panelsState.isStartPanelOpen) {
                            chatModel.chosenChannel = channel
                            scope.launch { panelsState.closePanels() }
                        }
                    }
                }
            }
            Column(Modifier.background(MaterialTheme.colors.secondary).clickable{ navController.navigate(NavDestinations.PROFILE) }) {
                Box(modifier = Modifier.alpha(0.2f).fillMaxWidth().height(1.dp).background(MaterialTheme.colors.onBackground))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    EnsicordUser(user = chatModel.userUser, Modifier.weight(1f))
                    EnsicordBorderlessButton(painterLight = painterResource(id = R.drawable.gear_black), painterDark = painterResource(id = R.drawable.gear_white), contentDescription = context.getString(R.string.options)) {
                        navController.navigate(NavDestinations.OPTIONS)
                    }
                }
            }
        }
    }
}