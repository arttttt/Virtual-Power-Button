package com.arttttt.virtualpowerbutton.utils

import android.content.Context
import android.content.Intent
import android.provider.Settings
import com.arttttt.virtualpowerbutton.PowerButtonService

class AccessibilityManager(
    private val context: Context
) {
    val isAccessibilityEnabled: Boolean
        get() {
            val service = "${context.packageName}/${PowerButtonService::class.java.canonicalName}"
            val enabledServices = Settings.Secure.getString(
                context.contentResolver,
                Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
            )

            return enabledServices?.contains(service) == true
        }

    fun requestAccessibilityPermission() {
        val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(intent)
    }
}