package com.arttttt.virtualpowerbutton

import android.app.PendingIntent
import android.content.Intent
import android.graphics.drawable.Icon
import android.os.Handler
import android.os.Looper
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import androidx.core.os.postDelayed
import androidx.core.service.quicksettings.PendingIntentActivityWrapper
import androidx.core.service.quicksettings.TileServiceCompat

class LockScreenTileService : TileService() {

    override fun onStartListening() {
        super.onStartListening()

        qsTile ?: return

        qsTile.icon = Icon.createWithResource(applicationContext, R.drawable.ic_lock)
        qsTile.label = getString(R.string.lock_screen)
        qsTile.state = Tile.STATE_INACTIVE
        qsTile.updateTile()
    }

    override fun onClick() {
        super.onClick()

        if (PowerButtonService.instance != null) {
            val intent = Intent(this, InvisibleActivity::class.java)

            val wrapper = PendingIntentActivityWrapper(
                this,
                0,
                intent,
                PendingIntent.FLAG_ONE_SHOT,
                false,
            )

            TileServiceCompat.startActivityAndCollapse(this, wrapper)
            PowerButtonService.instance?.lockScreen()
        } else {
            val intent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            }

            val wrapper = PendingIntentActivityWrapper(
                this,
                0,
                intent,
                PendingIntent.FLAG_ONE_SHOT,
                false
            )

            TileServiceCompat.startActivityAndCollapse(this, wrapper)
        }
    }
}