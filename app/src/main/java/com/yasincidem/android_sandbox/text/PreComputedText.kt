package com.yasincidem.android_sandbox.text

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.PrecomputedTextCompat
import androidx.core.widget.TextViewCompat
import androidx.fragment.app.Fragment
import com.yasincidem.android_sandbox.R
import com.yasincidem.android_sandbox.databinding.FragmentPreComputedTextBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

class PreComputedText : Fragment() {

    private var _binding: FragmentPreComputedTextBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPreComputedTextBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val text = getString(R.string.lorem_ipsum)
        binding.apply {
            topTextView.setTextWithCoroutines(text)
            bottomTextView.setTextWithFuture(text)
        }
    }

    private fun TextView.setTextWithCoroutines(text: String) {
        val mParams: PrecomputedTextCompat.Params = TextViewCompat.getTextMetricsParams(this)
        val ref = WeakReference(this)
        GlobalScope.launch(Dispatchers.Default) {
            // worker thread
            val pText = PrecomputedTextCompat.create(text, mParams)
            launch(Dispatchers.Main) {
                // main thread
                ref.get()?.let { textView ->
                    textView.text = pText
                }
            }
        }
    }

    private fun TextView.setTextWithFuture(text: String) {
        (this as AppCompatTextView).setTextFuture(
            PrecomputedTextCompat.getTextFuture(
                text,
                TextViewCompat.getTextMetricsParams(this),
                null
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}