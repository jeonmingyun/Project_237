package com.coram.spy237.ui.country_info

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.coram.spy237.databinding.FragmentCountryDetailBinding
import com.coram.spy237.ui.country_info.adapter.ViewPagerAdapter
import com.coram.spy237.ui.country_info.tabs.*
import com.google.android.material.tabs.TabLayoutMediator

class CountryDetailFragment : Fragment() {
    // view binding
    private var mBinding: FragmentCountryDetailBinding? = null
    private val binding get() = mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // view binding
        mBinding = FragmentCountryDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewPagerTab()

        binding.backBtn.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // view binding
        mBinding = null
    }

    private fun initViewPagerTab() {
        val pagerAdapter = ViewPagerAdapter(requireActivity())
        // Fragment Add
        pagerAdapter.addFragment(IntroTabFragment())
        pagerAdapter.addFragment(PeopleTabFragment())
        pagerAdapter.addFragment(EconomyTabFragment())
        pagerAdapter.addFragment(TravelTabFragment())
        pagerAdapter.addFragment(EvanTabFragment())
        pagerAdapter.addFragment(PrayTabFragment())
        // Adapter
        binding.viewPager.adapter = pagerAdapter

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.e("ViewPagerFragment", "Page ${position + 1}")
            }
        })

        // TabLayout attach
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Intro"
                1 -> "People"
                2 -> "Economy"
                3 -> "Travel"
                4 -> "Evangelization"
                5 -> "Pray"
                else -> "empty"
            }
        }.attach()
    }
}