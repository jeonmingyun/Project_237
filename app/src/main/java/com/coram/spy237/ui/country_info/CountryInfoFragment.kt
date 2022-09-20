package com.coram.spy237.ui.country_info

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.coram.spy237.databinding.FragmentCountryInfoBinding
import com.coram.spy237.model.HeaderViewpagerModel
import com.coram.spy237.model.db.CountryModel
import com.coram.spy237.ui.country_info.adapter.PagerRecyclerAdapter
import com.coram.spy237.ui.country_info.adapter.RankAdapter

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

        initHeaderViewpager(HeaderViewpagerModel().getTestList())
        initRankRecycler(CountryModel().getRankList())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // view binding
        mBinding = null
    }

    private fun initHeaderViewpager(itemList: ArrayList<HeaderViewpagerModel>) {
        binding.headerViewPager.adapter = PagerRecyclerAdapter(activity as Context, itemList)
        binding.headerViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    }

    private fun initRankRecycler(itemList: ArrayList<CountryModel>) {
        binding.rankRecycler.adapter = RankAdapter(requireContext(), itemList)
    }

}