package com.arttttt.screenlocker

import androidx.lifecycle.ViewModel
import com.arttttt.screenlocker.utils.DeviceAdminManager
import com.arttttt.screenlocker.utils.ShortcutManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel(
    private val deviceAdminManager: DeviceAdminManager,
    private val shortcutManager: ShortcutManager,
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        MainUiState(
            isAdminActive = false,
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

    fun createShortcut() {
        shortcutManager.createShortcut()
        updateState()
    }

    fun updateState() {
        _uiState.update {
            MainUiState(
                isAdminActive = deviceAdminManager.isAdminActive,
                isShortcutCreated = shortcutManager.isShortcutCreated()
            )
        }
    }
}