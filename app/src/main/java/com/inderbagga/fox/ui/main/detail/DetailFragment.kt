package com.inderbagga.fox.ui.main.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.inderbagga.fox.ui.main.FoxActivity
import com.inderbagga.fox.R
import com.inderbagga.fox.databinding.FragmentDetailBinding

/**
 * This will have detailed information about the item being selected in homeFragment.
 */
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private lateinit var foxActivity: FoxActivity
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        foxActivity=activity as FoxActivity

        binding.btnPrevious.setOnClickListener {
            foxActivity.onFragmentChange(R.id.homeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}