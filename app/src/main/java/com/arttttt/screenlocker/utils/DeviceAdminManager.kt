package com.arttttt.screenlocker.utils

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import androidx.core.content.getSystemService
import com.arttttt.screenlocker.LockDeviceAdminReceiver
import com.arttttt.screenlocker.R

class DeviceAdminManager(
    private val context: Context
) {
    private val devicePolicyManager by lazy { context.getSystemService<DevicePolicyManager>()!! }
    private val componentName by lazy { ComponentName(context, LockDeviceAdminReceiver::class.java) }

    val isAdminActive: Boolean
        get() {
            return devicePolicyManager.isAdminActive(componentName)
        }

    fun requestAdminPermission() {
        val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN).apply {
            putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName)
            putExtra(
                DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                context.getString(R.string.admin_permission_explanation)
            )
        }

        context.startActivity(intent)
    }

    fun lockScreen() {
        if (!isAdminActive) return

        devicePolicyManager.lockNow()
    }
}