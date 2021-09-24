package com.inderbagga.fox.ui.main.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.inderbagga.fox.BuildConfig
import com.inderbagga.fox.ui.main.FoxActivity
import com.inderbagga.fox.R
import com.inderbagga.fox.databinding.FragmentLoginBinding
import com.inderbagga.fox.util.*

/**
 * This view will contain the login layout.
 */
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private lateinit var foxActivity: FoxActivity

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        foxActivity=activity as FoxActivity

        binding.tvVersion.text=getString(R.string.version,BuildConfig.VERSION_NAME)
        binding.btnLogin.setOnClickListener {
            foxActivity.onFragmentChange(R.id.homeFragment)
        }

        binding.etEmail.validate(validator = EMAIL_VALIDATOR,container = binding.signInEmailLayout,message = getString(R.string.error_email))
        binding.etPassword.validate(validator = PASSWORD_VALIDATOR,container = binding.signInPasswordLayout,message = getString(R.string.error_password))
    }

    /*
    * ViewBinding will generate a custom ViewBinding class which will keep references to all your views inside Fragment,
    * if ViewBinding is not cleared or set to null, it won't be eligible for GC, thereby holding all the views in memory
    * even though you are not using it, leading to memory leaks. So yes, it is always better to set it to null at the end
    * of life cycle.
    * */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
