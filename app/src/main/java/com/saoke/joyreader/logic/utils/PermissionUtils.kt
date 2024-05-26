package com.saoke.joyreader.logic.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionUtils {
    companion object {
        val isSdkUnder33 = Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU

        fun checkPermissionsGroup(context: Context, permissions: Array<String>): Boolean {
            for (permission in permissions) {
                if (ContextCompat.checkSelfPermission(
                        context,
                        permission
                    ) == PackageManager.PERMISSION_DENIED
                ) return false
            }
            return true
        }

        fun requestPermissions(activity: Activity, permissions: Array<String>, requestCode: Int) {
            if (!checkPermissionsGroup(activity, permissions)) {
                ActivityCompat.requestPermissions(activity, permissions, requestCode)
            }
        }
    }
}