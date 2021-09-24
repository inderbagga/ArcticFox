package com.inderbagga.fox.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.inderbagga.fox.FoxActivity
import com.inderbagga.fox.R
import com.inderbagga.fox.databinding.FragmentHomeBinding

/**
 * This view will contain the dashboard layout and displays the primary content.
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var foxActivity:FoxActivity
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        foxActivity= activity as FoxActivity

        binding.btnNext.setOnClickListener {
            foxActivity.onFragmentChange(R.id.detailFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}