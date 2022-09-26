package com.coram.spy237.ui.country_info

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.coram.spy237.databinding.FragmentCountryDetailBinding
import com.coram.spy237.ui.country_info.adapter.ViewPagerAdapter
import com.coram.spy237.ui.country_info.tabs.*
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class CountryDetailFragment : Fragment() {
    // view binding
    private var mBinding: FragmentCountryDetailBinding? = null
    private val binding get() = mBinding!!

    fun newInstance(isPray: Boolean): CountryDetailFragment {
        val args = Bundle()

        args.putBoolean("isPray", isPray)
        val fragment = CountryDetailFragment()
        fragment.arguments = args
        return fragment
    }

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

        val isPray = arguments?.getBoolean("isPray", false) ?: false
        if (isPray) {
            Handler().postDelayed(
                Runnable {
                    scrollToPrayTab()

                    val tab: TabLayout.Tab = binding.tabLayout.getTabAt(5)!!
                    tab.select()
                }, 200
            )
        }

        binding.backBtn.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.countryTag.setOnClickListener {
            startActivity(Intent(context, ConnectModalActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // view binding
        mBinding = null
    }

    private fun scrollToPrayTab() {
        val viewLocation = IntArray(2)
        val scrollLocation = IntArray(2)
        binding.tabLayout.getLocationOnScreen(viewLocation)
        binding.rootSv.getLocationOnScreen(scrollLocation)
        binding.rootSv.scrollTo(viewLocation[0], viewLocation[1])
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
        binding.viewPager.offscreenPageLimit = 5

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

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val view = (binding.viewPager[0] as RecyclerView).layoutManager?.findViewByPosition(
                    position
                )
                view?.post {
                    val wMeasureSpec =
                        View.MeasureSpec.makeMeasureSpec(view.width, View.MeasureSpec.EXACTLY)
                    val hMeasureSpec =
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
                    view.measure(wMeasureSpec, hMeasureSpec)
                    if (binding.viewPager.layoutParams.height != view.measuredHeight) {
                        binding.viewPager.layoutParams =
                            (binding.viewPager.layoutParams).also { lp ->
                                lp.height = view.measuredHeight
                            }
                    }
                }
            }
        })

    }


}