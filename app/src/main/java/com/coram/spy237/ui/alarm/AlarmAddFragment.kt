package com.coram.spy237.ui.alarm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.coram.spy237.databinding.FragmentAlarmAddBinding
import com.coram.spy237.util.Utils

class AlarmAddFragment : Fragment(), View.OnClickListener {
    // view binding
    private var mBinding: FragmentAlarmAddBinding? = null
    private val binding get() = mBinding!!

    private var bundleFlag: String? = BUNDLE_VAL_FLAG_ADD

    companion object {
        const val BUNDLE_KEY_FLAG = "BUNDLE_KEY_FLAG"
        const val BUNDLE_VAL_FLAG_ADD = "BUNDLE_VAL_FLAG_ADD"
        const val BUNDLE_VAL_FLAG_EDIT = "BUNDLE_VAL_FLAG_EDIT"

        fun newInstance(flag: String) =
            AlarmAddFragment().apply {
                arguments = Bundle().apply {
                    putString(BUNDLE_KEY_FLAG, flag)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // view binding
        mBinding = FragmentAlarmAddBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBundleData()
        setBottomBtn(bundleFlag)

        setAmPmPicker()
        setHourPicker()
        setMinPicker()

        binding.cancelBtn.setOnClickListener(this)
        binding.deleteBtn.setOnClickListener(this)
        binding.leftSaveBtn.setOnClickListener(this)
        binding.rightSaveBtn.setOnClickListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // view binding
        mBinding = null
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.cancelBtn -> {
                requireActivity().supportFragmentManager.popBackStack()
            }
            binding.deleteBtn -> {
                Utils.onToast(context, "삭제")
            }
            binding.leftSaveBtn -> {
                Utils.onToast(context, "저장")
            }
            binding.rightSaveBtn -> {
                Utils.onToast(context, "저장")
            }
        }
    }

    private fun setAmPmPicker() {
        val values = arrayOf("오전", "오후")
        binding.amPmPicker.minValue = 0
        binding.amPmPicker.maxValue = values.size -1
        binding.amPmPicker.displayedValues = values
        binding.amPmPicker.setOnValueChangedListener { picker, oldVal, newVal ->
            Utils.onLog("$picker / $oldVal / $newVal")
        }
    }

    private fun setHourPicker() {
        val values: Array<String> = Array(12){
                i -> if(i<10) "0$i" else i.toString()
        }

        binding.hourPicker.minValue = 0
        binding.hourPicker.maxValue = values.size -1
        binding.hourPicker.displayedValues = values
    }

    private fun setMinPicker() {
        val values: Array<String> = Array(60){
                i -> if(i<10) "0$i" else i.toString()
        }

        binding.minPicker.minValue = 0
        binding.minPicker.maxValue = values.size -1
        binding.minPicker.displayedValues = values
    }

    private fun setBundleData() {
        arguments?.let {
            bundleFlag = it.getString(BUNDLE_KEY_FLAG)
        }
    }

    private fun setBottomBtn(flag: String?) {
        if (flag == BUNDLE_VAL_FLAG_EDIT) { // 수정
            binding.cancelBtn.visibility = View.GONE
            binding.rightSaveBtn.visibility = View.GONE
            binding.leftSaveBtn.visibility = View.VISIBLE
            binding.deleteBtn.visibility = View.VISIBLE
        } else { // 추가
            binding.cancelBtn.visibility = View.VISIBLE
            binding.rightSaveBtn.visibility = View.VISIBLE
            binding.leftSaveBtn.visibility = View.GONE
            binding.deleteBtn.visibility = View.GONE
        }
    }
}