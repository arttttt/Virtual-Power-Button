package com.arttttt.screenlocker

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity

class LockActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startService(
            Intent(this, LockScreenService::class.java).apply {
                action = LockScreenService.ACTION
            }
        )

        finish()
    }
}