package com.yasincidem.android_sandbox

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.yasincidem.android_sandbox.activity.ActivityMainActivity
import com.yasincidem.android_sandbox.activity.BasicLifecycle
import com.yasincidem.android_sandbox.databinding.ActivityMainBinding
import com.yasincidem.android_sandbox.text.TextMainActivity

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val buttonList = listOf<Class<*>>(
        ActivityMainActivity::class.java,
        TextMainActivity::class.java,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
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