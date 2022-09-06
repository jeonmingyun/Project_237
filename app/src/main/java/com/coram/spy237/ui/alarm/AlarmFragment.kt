package com.coram.spy237.ui.alarm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.coram.spy237.databinding.FragmentAlarmBinding
import com.coram.spy237.model.AlarmModel
import com.coram.spy237.model.SearchModel
import com.coram.spy237.ui.search.SearchAdapter
import java.util.ArrayList

class AlarmFragment : Fragment() {
    // view binding
    private var mBinding: FragmentAlarmBinding? = null
    private val binding get() = mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // view binding
        mBinding = FragmentAlarmBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setMissionBarEnergy(40)
        initAlarmRecycler(AlarmModel.getTestList())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // view binding
        mBinding = null
    }

    private fun setMissionBarEnergy(percent: Int) {
        binding.progressBar.progress = percent
    }

    private fun initAlarmRecycler(itemList: ArrayList<AlarmModel>) {
        val adapter = AlarmAdapter(requireContext(), itemList, 1)
        binding.prayRecycler.adapter = adapter
    }
}