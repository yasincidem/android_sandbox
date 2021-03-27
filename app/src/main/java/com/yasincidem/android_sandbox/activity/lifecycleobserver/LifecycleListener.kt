package com.yasincidem.android_sandbox.activity.lifecycleobserver

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import java.lang.ref.WeakReference

class LifecycleListener(
    private val context: WeakReference<Context>
) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        "onCreate".toast()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        "onStart".toast()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun connectListener() {
        "onResume".toast()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun disconnectListener() {
        "onPause".toast()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        "onStop".toast()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        "onDestroy".toast()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun onAny() {

    }

    private fun String.toast() {
        Toast.makeText(context.get(), this, Toast.LENGTH_SHORT).show()
    }

}