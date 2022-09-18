package com.coram.spy237.ui.missionary

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.coram.spy237.databinding.FragmentMissionaryProfileBinding
import com.coram.spy237.databinding.FragmentPrayNoteBinding

class MissionaryProfileFragment : Fragment() {
    // view binding
    private var mBinding: FragmentMissionaryProfileBinding? = null
    private val binding get() = mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // view binding
        mBinding = FragmentMissionaryProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backBtn.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        binding.moreBtn.setOnClickListener {
            openMissionaryDetail()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // view binding
        mBinding = null
    }

    fun openMissionaryDetail() {
        startActivity(Intent(context, MissionaryDetailActivity::class.java))
    }
}