package com.arttttt.virtualpowerbutton.utils

import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.core.content.getSystemService
import kotlin.properties.Delegates

object VibrationManager {

    sealed class Effect {

        abstract fun Vibrator.vibrate()

        data object LockScreen : Effect() {

            private val timings = longArrayOf(0, 25, 100, 100)
            private val amplitudes = intArrayOf(0, 128, 0, 255)

            override fun Vibrator.vibrate() {
                when {
                    hasAmplitudeControl() -> vibrate(VibrationEffect.createWaveform(timings, amplitudes, -1))
                    else -> vibrate(VibrationEffect.createWaveform(timings, -1))
                }
            }
        }
        data object Click : Effect() {
            override fun Vibrator.vibrate() {
                vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK))
            }
        }
    }

    private var context: Context by Delegates.notNull()

    private val vibrator by lazy {
        context.getSystemService<Vibrator>()!!
    }

    fun init(context: Context) {
        this.context = context
    }

    fun vibrate(effect: Effect) {
        if (!vibrator.hasVibrator()) return

        with(effect) {
            vibrator.vibrate()
        }
    }
}