package com.coram.spy237.ui.country_info.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.coram.spy237.databinding.FragmentPrayTabBinding
import com.coram.spy237.ui.alarm.AlarmAddActivity

class PrayTabFragment : Fragment() {
    // view binding
    private var mBinding: FragmentPrayTabBinding? = null
    private val binding get() = mBinding!!

    private var bundleCountryName = "마다가스카르"

    companion object {
        fun newInstance(countryName: String): EvanTabFragment {
            val args = Bundle()

            val fragment = EvanTabFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private fun setBundleData() {
        bundleCountryName = arguments?.getString(AlarmAddActivity.BUNDLE_KEY_FLAG).toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // view binding
        mBinding = FragmentPrayTabBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBundleData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // view binding
        mBinding = null
    }
}