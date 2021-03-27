package com.yasincidem.android_sandbox.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.yasincidem.android_sandbox.activity.lifecycleobserver.LifecycleWithObserver
import com.yasincidem.android_sandbox.activity.resultapi.ResultAPI
import com.yasincidem.android_sandbox.databinding.ActivityMainActivityBinding

class ActivityMainActivity : AppCompatActivity() {

    private var _binding: ActivityMainActivityBinding? = null
    private val binding get() = _binding!!

    private val buttonList = listOf<Class<*>>(
        BasicLifecycle::class.java,
        LifecycleWithObserver::class.java,
        ResultAPI::class.java
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        for (item in buttonList.sortedBy {
            it.simpleName
        }) {
            binding.buttonContainer.addView(
                Button(this).apply {
                    text = item.simpleName
                    isAllCaps = false
                    setOnClickListener {
                        startActivity(Intent(context, item))
                    }
                }
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}