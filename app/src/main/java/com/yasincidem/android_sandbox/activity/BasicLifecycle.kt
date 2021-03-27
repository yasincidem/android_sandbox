package com.yasincidem.android_sandbox.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.yasincidem.android_sandbox.R
import com.yasincidem.android_sandbox.ext.toast

class BasicLifecycle : AppCompatActivity(R.layout.activity_basic_lifecycle) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toast("onCreate()")
    }

    override fun onResume() {
        super.onResume()
        toast("onResume()")
    }

    override fun onStart() {
        super.onStart()
        toast("onStart()")
    }

    override fun onRestart() {
        super.onRestart()
        toast("onRestart()")
    }

    override fun onPause() {
        super.onPause()
        toast("onPause()")
    }

    override fun onStop() {
        super.onStop()
        toast("onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        toast("onDestroy()")
    }

}