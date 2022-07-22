package com.aliernfrog.ensicord.ui.screen

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.aliernfrog.ensicord.ConfigKey
import com.aliernfrog.ensicord.MainActivity
import com.aliernfrog.ensicord.R
import com.aliernfrog.ensicord.Theme
import com.aliernfrog.ensicord.model.AddonsModel
import com.aliernfrog.ensicord.ui.composable.EnsicordBaseScaffold
import com.aliernfrog.ensicord.ui.composable.EnsicordButton
import com.aliernfrog.ensicord.ui.composable.EnsicordColumnRounded
import com.aliernfrog.ensicord.ui.composable.EnsicordRadioButtons
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

private lateinit var scope: CoroutineScope
private lateinit var scaffoldState: ScaffoldState

@Composable
fun OptionsScreen(navController: NavController, addonsModel: AddonsModel, config: SharedPreferences) {
    val context = LocalContext.current
    scope = rememberCoroutineScope()
    scaffoldState = rememberScaffoldState()
    EnsicordBaseScaffold(title = context.getString(R.string.options), state = scaffoldState, navController = navController) {
        ThemeSelection(config)
        Addons(navController, addonsModel)
    }
}

@Composable
private fun ThemeSelection(config: SharedPreferences) {
    val context = LocalContext.current
    val options = listOf(context.getString(R.string.optionsThemeSystem),context.getString(R.string.optionsThemeLight),context.getString(R.string.optionsThemeDark))
    val chosen = config.getInt(ConfigKey.KEY_APP_THEME, 0)
    EnsicordColumnRounded(color = MaterialTheme.colors.secondary, title = context.getString(R.string.optionsTheme)) {
        EnsicordRadioButtons(options = options, selectedIndex = chosen, columnColor = MaterialTheme.colors.secondaryVariant, onSelect = { option ->
            applyTheme(option, config, context)
        })
    }
}

@Composable
private fun Addons(navController: NavController, addonsModel: AddonsModel) {
    val context = LocalContext.current
    EnsicordButton(title = context.getString(R.string.addons), painter = painterResource(id = R.drawable.download)) {
        addonsModel.fetchAddons()
        navController.navigate("addons")
    }
}

private fun applyTheme(option: String, config: SharedPreferences, context: Context) {
    var theme = Theme.SYSTEM
    if (option == context.getString(R.string.optionsThemeLight)) theme = Theme.LIGHT
    if (option == context.getString(R.string.optionsThemeDark)) theme = Theme.DARK
    config.edit().putInt(ConfigKey.KEY_APP_THEME, theme).apply()
    scope.launch {
        when(scaffoldState.snackbarHostState.showSnackbar(context.getString(R.string.optionsThemeChanged), context.getString(R.string.action_restartNow))) {
            SnackbarResult.ActionPerformed -> { restartApp(context) }
            SnackbarResult.Dismissed -> {  }
        }
    }
}

private fun restartApp(context: Context) {
    val intent = Intent(context, MainActivity::class.java)
    (context as Activity).finish()
    context.startActivity(intent)
}