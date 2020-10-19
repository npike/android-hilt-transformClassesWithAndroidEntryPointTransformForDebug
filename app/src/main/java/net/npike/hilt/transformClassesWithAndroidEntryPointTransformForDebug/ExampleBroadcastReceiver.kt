package net.npike.hilt.transformClassesWithAndroidEntryPointTransformForDebug

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import dagger.hilt.android.AndroidEntryPoint

/**
 * Compilation error:
 *
 * Execution failed for task ':app:transformClassesWithAndroidEntryPointTransformForDebug'.
 * > javassist.bytecode.BadBytecode: onReceive (Landroid/content/Context;Landroid/content/Intent;)V in net.npike.hilt.transformClassesWithAndroidEntryPointTransformForDebug.ExampleBroadcastReceiver: failed to resolve types
 *
 * Cause: android.net.Uri
 */
@AndroidEntryPoint
class NotWorkingExampleBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        intent.data?.parsePackage()?.let { packageName ->
            Log.d("test", packageName)
        }
    }
}

/**
 * Compiles fine.
 */
@AndroidEntryPoint
class WorkingExampleBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val packageName = intent.data?.parsePackage()

        packageName?.let {
            Log.d("test", packageName)
        }
    }
}

/**
 * Compiles fine.
 */
class AlsoWorkingExampleBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        intent.data?.parsePackage()?.let { packageName ->
            Log.d("test", packageName)
        }
    }githu
}

/**
 * Extension method on Uri that just returns the string "foo".  In my actual
 * project this extension method does things specifically with the Uri it's used on,
 * but doesn't seem relevant to reproduce this problem.
 */
fun Uri?.parsePackage(): String? {
    return "foo"
}
