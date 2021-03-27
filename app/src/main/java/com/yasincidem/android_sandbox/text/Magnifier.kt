package com.yasincidem.android_sandbox.text

import android.annotation.SuppressLint
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Magnifier
import androidx.fragment.app.Fragment
import com.yasincidem.android_sandbox.databinding.FragmentMagnifierBinding

class Magnifier : Fragment() {

    private var _binding: FragmentMagnifierBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMagnifierBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.P) {
            binding.topTextView.apply {
                val magnifier = Magnifier(binding.topTextView)
                val outRect = Rect()
                val viewPosition = IntArray(2)

                post {
                    getDrawingRect(outRect)
                    getLocationOnScreen(viewPosition)
                    // Setting offset to compare the rect with touch event raw values
                    outRect.offset(viewPosition[0], viewPosition[1])
                }

                setOnTouchListener { _, event ->
                    when (event.action) {
                        MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                            if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                                magnifier.dismiss()
                                return@setOnTouchListener false
                            }
                            magnifier.show(event.rawX - viewPosition[0], event.rawY - viewPosition[1])
                        }
                        MotionEvent.ACTION_UP -> {
                            magnifier.dismiss()
                        }
                    }
                    true
                }
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            binding.topTextView.apply {
                val magnifier = Magnifier.Builder(this)
                    .setCornerRadius(24f)
                    // .setDefaultSourceToMagnifierOffset()
                    // .setClippingEnabled()
                    // .setElevation()
                    // .setOverlay()
                    // .setInitialZoom()
                    // .setSize()
                    // .setSourceBounds()
                    .build()
                val outRect = Rect()
                val viewPosition = IntArray(2)

                post {
                    getDrawingRect(outRect)
                    getLocationOnScreen(viewPosition)
                    // Setting offset to compare the rect with touch event raw values
                    outRect.offset(viewPosition[0], viewPosition[1])
                }

                setOnTouchListener { _, event ->
                    when (event.action) {
                        MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                            if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                                magnifier.dismiss()
                                return@setOnTouchListener false
                            }
                            magnifier.show(event.rawX - viewPosition[0], event.rawY - viewPosition[1])
                        }
                        MotionEvent.ACTION_UP -> {
                            magnifier.dismiss()
                        }
                    }
                    true
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}