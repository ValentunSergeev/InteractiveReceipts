package com.valentun.interactiverecipes.utils.permissions

import com.valentun.interactiverecipes.ui.mvp.MvpAppCompatActivity

abstract class PermissionActivity : MvpAppCompatActivity() {
    private val permissionHelper = PermissionHelper(this)

    fun askPermission(permission: String, block: PermissionActionContext.(String) -> Unit) {
        withPermission(permissionHelper, permission, block)
    }

    fun requiresPermission(permission: String, block: GrantedCallback) {
        withPermission(permissionHelper, permission, block)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (!permissionHelper.onPermissionResult(requestCode, permissions, grantResults)) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }
}