package com.arttttt.screenlocker

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity

class LockActivity : ComponentActivity() {

    companion object {
        const val ACTION_LOCK_SCREEN = "com.arttttt.screenlocker.LOCK_SCREEN"
        const val ACTION_POWER_MENU = "com.arttttt.screenlocker.POWER_MENU"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        when (intent.action) {
            ACTION_POWER_MENU -> handlePowerMenu()
            else -> handleLockScreen()
        }

        finish()
    }

    private fun handleLockScreen() {
        startService(
            Intent(this, LockScreenService::class.java).apply {
                action = LockScreenService.ACTION
            }
        )
    }

    private fun handlePowerMenu() {
        PowerButtonService.instance?.showPowerDialog() ?: run {
            Toast
                .makeText(
                    this,
                    R.string.accessibility_permission_required,
                    Toast.LENGTH_LONG
                ).show()

            startActivity(
                Intent(this, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                }
            )
        }
    }
}