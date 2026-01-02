package com.message.app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.message.app.ui.base.BaseActivity
import com.message.app.databinding.FragmentOnboardingBinding
import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.message.app.R
import com.message.app.data.OnboardingModel

class OnboardingFragment : Fragment() {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!

    companion object {

        private const val ARG_IMAGE = "image"
        private const val ARG_TITLE = "title"
        private const val ARG_MESSAGE = "message"

        fun newInstance(page: OnboardingModel) = OnboardingFragment().apply {
            arguments = bundleOf(
                ARG_IMAGE to page.imageRes,
                ARG_TITLE to page.title,
                ARG_MESSAGE to page.message
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ctx = requireActivity() as BaseActivity

        arguments?.let {
            binding.imageIllustration.setImageResource(it.getInt(ARG_IMAGE))
            binding.tvTitle.text = it.getString(ARG_TITLE)
            binding.tvMessage.text = it.getString(ARG_MESSAGE)

            // Set theming colors dynamically
            binding.tvTitle.setTextColor(ctx.themeTextColor)
            binding.tvMessage.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorTextGrey)) // lighter text or themeTextColor with alpha
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}