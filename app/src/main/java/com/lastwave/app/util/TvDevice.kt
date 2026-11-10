package com.lastwave.app.util

import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.view.KeyEvent

/**
 * Small platform helpers for Android TV / Google TV.
 *
 * Detection is deliberately capability-based rather than tied to a TV brand.
 * Phones and tablets continue to use the existing LastWave experience.
 */
object TvDevice {

    fun isTelevision(context: Context): Boolean {
        val packageManager = context.packageManager
        val hasLeanback = packageManager.hasSystemFeature(PackageManager.FEATURE_LEANBACK)
        val hasTelevisionFeature = packageManager.hasSystemFeature(PackageManager.FEATURE_TELEVISION)
        val isTelevisionUiMode =
            (context.resources.configuration.uiMode and Configuration.UI_MODE_TYPE_MASK) ==
                Configuration.UI_MODE_TYPE_TELEVISION

        return hasLeanback || hasTelevisionFeature || isTelevisionUiMode
    }

    /**
     * True for the directional / select / back keys commonly sent by TV remotes.
     * This helper does not consume events; screens can opt into handling them.
     */
    fun isRemoteNavigationKey(keyCode: Int): Boolean = when (keyCode) {
        KeyEvent.KEYCODE_DPAD_UP,
        KeyEvent.KEYCODE_DPAD_DOWN,
        KeyEvent.KEYCODE_DPAD_LEFT,
        KeyEvent.KEYCODE_DPAD_RIGHT,
        KeyEvent.KEYCODE_DPAD_CENTER,
        KeyEvent.KEYCODE_ENTER,
        KeyEvent.KEYCODE_BACK -> true
        else -> false
    }
}
