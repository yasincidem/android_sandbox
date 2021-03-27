package com.yasincidem.android_sandbox.activity.resultapi

import android.content.Context
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.yasincidem.android_sandbox.ext.toast
import java.lang.ref.WeakReference

class MyLifecycleObserver(
    private val registry : ActivityResultRegistry,
    private val contextRef: WeakReference<Context>
) : LifecycleObserver {

    lateinit var getContent : ActivityResultLauncher<String>

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(lifeCycleOwner: LifecycleOwner) {
        getContent = registry.register("key", lifeCycleOwner, ActivityResultContracts.GetContent()) { uri ->
            contextRef.get()?.toast(uri.path.toString())
        }
    }

    fun selectImage() {
        getContent.launch("image/*")
    }

}