package com.arttttt.virtualpowerbutton

import android.app.Application
import com.arttttt.virtualpowerbutton.utils.VibrationManager

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        VibrationManager.init(this)
    }
}