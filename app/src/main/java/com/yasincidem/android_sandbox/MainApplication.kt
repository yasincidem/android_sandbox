package com.yasincidem.android_sandbox

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.yasincidem.android_sandbox.ext.toast

class MainApplication : Application(), LifecycleObserver {

    override fun onCreate() {
        super.onCreate()
        // Class that provides lifecycle for the whole application process.
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun appIsOnStart() {
        toast("appIsOnStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun appIsOnResume() {
        toast("appIsOnResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun appIsOnPause() {
        toast("appIsOnPause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun appIsOnStop() {
        toast("appIsOnStop")
    }

}