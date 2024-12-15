package com.arttttt.screenlocker

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import com.arttttt.screenlocker.utils.DeviceAdminManager

class LockScreenService : Service() {

    companion object {
        const val ACTION = "com.arttttt.screenlocker.LOCK_SCREEN"
    }

    private val deviceAdminManager by lazy { DeviceAdminManager(this) }

    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int,
    ): Int {
        when {
            intent?.action != ACTION -> {}
            deviceAdminManager.isAdminActive -> deviceAdminManager.lockScreen()
            else -> {
                Toast.makeText(
                    this,
                    R.string.admin_permission_required,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        stopSelf()

        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null
}