package com.yasincidem.android_sandbox.text

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.yasincidem.android_sandbox.R
import com.yasincidem.android_sandbox.databinding.FragmentTextMainBinding

class TextMainFragment : Fragment() {

    private var _binding: FragmentTextMainBinding? = null
    private val binding get() = _binding!!

    private val navController by lazy {
        findNavController()
    }

    private val buttonList = listOf<Pair<Class<*>, Int>>(
        JustifyText::class.java to R.id.action_textMainFragment_to_justifyText,
        PreComputedText::class.java to R.id.action_textMainFragment_to_PreComputedText,
        SmartLinkify::class.java to R.id.action_textMainFragment_to_smartLinkify,
        Magnifier::class.java to R.id.action_textMainFragment_to_magnifier
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTextMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        for (item in buttonList.sortedBy {
            it.first.simpleName
        }) {
            binding.buttonContainer.addView(
                Button(context).apply {
                    text = item.first.simpleName
                    isAllCaps = false
                    setOnClickListener {
                        navController.navigate(item.second)
                    }
                }
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}