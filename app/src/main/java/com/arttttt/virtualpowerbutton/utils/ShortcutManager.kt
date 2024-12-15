package com.arttttt.virtualpowerbutton.utils

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import com.arttttt.virtualpowerbutton.PowerButtonActivity

class ShortcutManager(
    context: Context,
) {

    private val packageManager = context.packageManager
    private val shortcutComponentName = ComponentName(context, PowerButtonActivity::class.java)

    fun createShortcut() {
        packageManager.setComponentEnabledSetting(
            shortcutComponentName,
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP,
        )
    }

    fun isShortcutCreated(): Boolean {
        return packageManager.getComponentEnabledSetting(shortcutComponentName) ==
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED
    }

    fun resetShortcutState() {
        packageManager.setComponentEnabledSetting(
            shortcutComponentName,
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP,
        )
    }
}