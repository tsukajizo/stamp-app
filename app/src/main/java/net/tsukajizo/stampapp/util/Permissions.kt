package net.tsukajizo.stampapp.util

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat

fun checkPermission(activity: Activity, requestCode: Int, callback: PermissionCallback) {
    val permissionCheck = ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
    if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.CAMERA), requestCode)
    } else {
        callback.onGranted()
    }
}

interface PermissionCallback {
    fun onGranted()
}