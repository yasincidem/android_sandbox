package com.yasincidem.android_sandbox.text

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.TextUtilsCompat
import androidx.core.widget.TextViewCompat
import com.yasincidem.android_sandbox.R
import com.yasincidem.android_sandbox.databinding.FragmentJustifyTextBinding
import com.yasincidem.android_sandbox.databinding.FragmentPreComputedTextBinding

class JustifyText : Fragment() {

    private var _binding: FragmentJustifyTextBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJustifyTextBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}