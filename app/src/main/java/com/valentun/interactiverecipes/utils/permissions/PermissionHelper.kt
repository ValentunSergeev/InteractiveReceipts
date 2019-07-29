package com.valentun.interactiverecipes.utils.permissions

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent


typealias GrantedCallback = () -> Unit
typealias DeniedCallback = () -> Unit

class PermissionActionHolder(
    var grantedAction: GrantedCallback? = null,
    var deniedAction: DeniedCallback? = null
)

class PermissionActionContext {
    internal val permissionActionHolder = PermissionActionHolder()

    fun onGranted(grantedCallback: GrantedCallback) {
        permissionActionHolder.grantedAction = grantedCallback
    }

    fun onDenied(deniedCallback: DeniedCallback) {
        permissionActionHolder.deniedAction = deniedCallback
    }
}

fun AppCompatActivity.withPermission(
    helper: PermissionHelper,
    permission: String,
    block: PermissionActionContext.(String) -> Unit
) {
    val actionContext = PermissionActionContext()
    block.invoke(actionContext, permission)
    val actionHolder = actionContext.permissionActionHolder

    processPermissionRequest(this, permission, actionHolder, helper)
}

fun AppCompatActivity.withPermission(helper: PermissionHelper,
                                            permission: String,
                                            onGranted: GrantedCallback) {
    val actionHolder = PermissionActionHolder(grantedAction = onGranted)

    processPermissionRequest(this, permission, actionHolder, helper)
}

private fun processPermissionRequest(
    activity: AppCompatActivity,
    permission: String,
    actionHolder: PermissionActionHolder,
    helper: PermissionHelper
) {
    if (activity.isPermissionGranted(permission)) {
        actionHolder.grantedAction?.invoke()
    } else {
        val code = permission.hashCode() % (2 shl 16) // code should be 16 bits

        ActivityCompat.requestPermissions(activity, arrayOf(permission), code)

        helper.addObserver(code, actionHolder)
    }
}

fun AppCompatActivity.isPermissionGranted(permission: String) =
    ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

class PermissionHelper(activity: AppCompatActivity) : LifecycleObserver {
    private val permissionBlockMapper: MutableMap<Int, PermissionActionHolder> = mutableMapOf()

    init {
        attachToLifecycle(activity)
    }

    fun onPermissionResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) : Boolean {
        val actionHolder = permissionBlockMapper[requestCode]

        return if (actionHolder != null) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                actionHolder.grantedAction?.invoke()
            } else {
                actionHolder.deniedAction?.invoke()
            }

            true
        } else {
            false
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        permissionBlockMapper.clear()
    }

    private fun attachToLifecycle(activity: AppCompatActivity) {
        activity.lifecycle.addObserver(this)
    }

    internal fun addObserver(code: Int, permissionActionHolder: PermissionActionHolder) {
        permissionBlockMapper[code] = permissionActionHolder
    }
}