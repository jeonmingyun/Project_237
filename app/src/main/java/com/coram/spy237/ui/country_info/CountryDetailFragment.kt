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
import com.coram.spy237.R
import com.coram.spy237.databinding.FragmentCountryDetailBinding
import com.coram.spy237.ui.country_info.adapter.ViewPagerAdapter
import com.coram.spy237.ui.country_info.tabs.*
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class CountryDetailFragment : Fragment() {
    // view binding
    private var mBinding: FragmentCountryDetailBinding? = null
    private val binding get() = mBinding!!

    private var bundleCountryName = "마다가스카르"
    private var isPray = false
    val BUNDLE_KEY_COUNTRY_NAME = "BUNDLE_KEY_COUNTRY_NAME"

    fun newInstance(isPray: Boolean, countryName: String): CountryDetailFragment {
        val args = Bundle()

        args.putBoolean("isPray", isPray)
        args.putString(BUNDLE_KEY_COUNTRY_NAME, countryName)
        val fragment = CountryDetailFragment()
        fragment.arguments = args
        return fragment
    }

    private fun setBundleData() {
        isPray = arguments?.getBoolean("isPray", false) ?: false
        bundleCountryName = arguments?.getString(BUNDLE_KEY_COUNTRY_NAME).toString()
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

        setBundleData()
        initLayout()
        initViewPagerTab()

        if (isPray) {
            Handler().postDelayed(
                Runnable {
                    scrollToPrayTab()

                    val tab: TabLayout.Tab = binding.tabLayout.getTabAt(5)!!
                    tab.select()
                }, 200
            )
        }

        binding.countryName.text = bundleCountryName

        binding.backBtn.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.countryTag.setOnClickListener {
            startActivity(Intent(context, ConnectModalActivity::class.java))
        }
    }

    // TEST_DATA 국가 > 국가상세 > INTRO 탭
    private fun initLayout() {
        when (bundleCountryName) {
            "마다가스카르" -> {
                binding.headerViewPager.setImageResource(R.drawable.country_img_mada)
                binding.continent.text = "아프리카"
                binding.countryName.text = bundleCountryName
                binding.countryComment.text = "희귀 동식물의 천국"
            }
            "우크라이나" -> {
                binding.headerViewPager.setImageResource(R.drawable.country_img02)
                binding.continent.text = "유럽"
                binding.countryName.text = bundleCountryName
                binding.countryComment.text = "동유럽의 비옥하고 우거진 땅, 우크라이나"
            }
            "필리핀" -> {
                binding.headerViewPager.setImageResource(R.drawable.phil_intro_img)
                binding.continent.text = "아시아"
                binding.countryName.text = bundleCountryName
                binding.countryComment.text = "자연재해, 치안 불안"
            }
            "미국" -> {
                binding.headerViewPager.setImageResource(R.drawable.country_img03)
                binding.continent.text = "아메리카"
                binding.countryName.text = bundleCountryName
                binding.countryComment.text = "팁은 필수!"
            }
            else -> {
                binding.headerViewPager.setImageResource(R.drawable.country_img_mada)
                binding.continent.text = "아프리카"
                binding.countryName.text = bundleCountryName
                binding.countryComment.text = "희귀 동식물의 천국"
            }
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
        pagerAdapter.addFragment(IntroTabFragment.newInstance(bundleCountryName))
        pagerAdapter.addFragment(PeopleTabFragment.newInstance(bundleCountryName))
        pagerAdapter.addFragment(EconomyTabFragment.newInstance(bundleCountryName))
        pagerAdapter.addFragment(TravelTabFragment.newInstance(bundleCountryName))
        pagerAdapter.addFragment(EvanTabFragment.newInstance(bundleCountryName))
        pagerAdapter.addFragment(PrayTabFragment.newInstance(bundleCountryName))
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