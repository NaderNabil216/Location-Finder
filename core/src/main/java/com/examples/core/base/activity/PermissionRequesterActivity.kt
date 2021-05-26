package com.examples.core.base.activity

import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.examples.core.R

abstract class PermissionRequesterActivity : AppCompatActivity() {
    abstract var permissions: Array<String>

    val requestPermissionsLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            // Do something if some permissions granted or denied
            val allGranted = permissions.entries.map {
                checkSelfPermission(it.key)
            }.map { it == PackageManager.PERMISSION_GRANTED }.find { !it } ?: true

            if (!allGranted) {
                Toast.makeText(this, getString(R.string.permission_are_not_granted), Toast.LENGTH_SHORT).show()
            }
        }
}