package com.arttttt.screenlocker

import androidx.lifecycle.ViewModel
import com.arttttt.screenlocker.utils.AccessibilityManager
import com.arttttt.screenlocker.utils.DeviceAdminManager
import com.arttttt.screenlocker.utils.ShortcutManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel(
    private val deviceAdminManager: DeviceAdminManager,
    private val accessibilityManager: AccessibilityManager,
    private val shortcutManager: ShortcutManager,
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        MainUiState(
            isAdminActive = false,
            isAccessibilityEnabled = false,
            isShortcutCreated = false,
        )
    )
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        updateState()
    }

    fun requestAdminPermission() {
        deviceAdminManager.requestAdminPermission()
    }

    fun requestAccessibilityPermission() {
        accessibilityManager.requestAccessibilityPermission()
    }

    fun createShortcut() {
        if (canCreateShortcut()) {
            shortcutManager.createShortcut()
            updateState()
        }
    }

    fun updateState() {
        _uiState.update {
            MainUiState(
                isAdminActive = deviceAdminManager.isAdminActive,
                isAccessibilityEnabled = accessibilityManager.isAccessibilityEnabled,
                isShortcutCreated = shortcutManager.isShortcutCreated()
            )
        }
    }

    private fun canCreateShortcut(): Boolean {
        return deviceAdminManager.isAdminActive && accessibilityManager.isAccessibilityEnabled
    }
}