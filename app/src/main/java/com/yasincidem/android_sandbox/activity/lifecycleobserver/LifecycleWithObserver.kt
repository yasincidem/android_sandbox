package com.yasincidem.android_sandbox.activity.lifecycleobserver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yasincidem.android_sandbox.R
import java.lang.ref.WeakReference

class LifecycleWithObserver : AppCompatActivity(R.layout.activity_lifecycle_with_observer) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(LifecycleListener(WeakReference(this)))
    }

}