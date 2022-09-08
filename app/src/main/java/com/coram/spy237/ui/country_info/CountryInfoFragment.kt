package com.coram.spy237.ui.country_info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.coram.spy237.R
import com.coram.spy237.databinding.FragmentAlarmBinding
import com.coram.spy237.databinding.FragmentCountryInfoBinding
import com.coram.spy237.databinding.FragmentMyPageBinding
import com.coram.spy237.databinding.FragmentSearchBinding

class CountryInfoFragment : Fragment() {
    // view binding
    private var mBinding: FragmentCountryInfoBinding? = null
    private val binding get() = mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // view binding
        mBinding = FragmentCountryInfoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // view binding
        mBinding = null
    }

}