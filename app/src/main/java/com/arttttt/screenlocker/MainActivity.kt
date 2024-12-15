package com.arttttt.screenlocker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arttttt.screenlocker.ui.theme.ScreenLockerTheme
import com.arttttt.screenlocker.utils.AccessibilityManager
import com.arttttt.screenlocker.utils.ShortcutManager

class MainActivity : ComponentActivity() {
    private val accessibilityManager by lazy { AccessibilityManager(this) }
    private val shortcutHelper by lazy { ShortcutManager(this) }
    private val viewModel: MainViewModel by viewModels {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainViewModel(accessibilityManager, shortcutHelper) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScreenLockerTheme {
                MainScreen()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateState()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun MainScreen() {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(R.string.app_name)) }
                )
            }
        ) { paddingValues ->
            MainContent(
                modifier = Modifier.padding(paddingValues)
            )
        }
    }

    @Composable
    private fun MainContent(modifier: Modifier = Modifier) {
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (!uiState.isAccessibilityEnabled) {
                PermissionsRequest()
            } else {
                ShortcutSection(uiState)
            }
        }
    }

    @Composable
    private fun PermissionsRequest() {
        Text(
            text = stringResource(R.string.accessibility_permission_explanation),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Button(
            onClick = { viewModel.requestAccessibilityPermission() },
            modifier = Modifier.width(200.dp)
        ) {
            Text(text = stringResource(R.string.request_accessibility))
        }
    }

    @Composable
    private fun ShortcutSection(uiState: MainUiState) {
        Button(
            onClick = { viewModel.createShortcut() },
            enabled = !uiState.isShortcutCreated,
            modifier = Modifier.width(200.dp)
        ) {
            Text(text = stringResource(R.string.create_shortcut))
        }

        if (uiState.isShortcutCreated) {
            Text(
                text = stringResource(R.string.shortcut_already_exists),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}