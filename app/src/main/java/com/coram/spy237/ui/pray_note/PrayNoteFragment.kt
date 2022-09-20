package com.coram.spy237.ui.pray_note

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.coram.spy237.R
import com.coram.spy237.databinding.FragmentPrayNoteBinding
import com.coram.spy237.ui.pray_note.callback.MyCallback
import com.coram.spy237.util.Utils

class PrayNoteFragment : Fragment(), View.OnClickListener {
    // view binding
    private var mBinding: FragmentPrayNoteBinding? = null
    private val binding get() = mBinding!!

    // sample
    private val prayNoteList = arrayListOf<String>(
        "2022-9-20",
        "2022-9-21",
        "2022-9-22",
    )
    private val prayNoteMap = hashMapOf<String, Int>(
        prayNoteList[0] to R.drawable.pray_note_1,
        prayNoteList[1] to R.drawable.pray_note_2,
        prayNoteList[2] to R.drawable.pray_note_3
    )

    private var currentPage = 0 // 0,1,2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // view binding
        mBinding = FragmentPrayNoteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnPagingColor(currentPage)

        binding.toolbarMenuBtn.setOnClickListener(this)
        binding.prevBtn.setOnClickListener(this)
        binding.nextBtn.setOnClickListener(this)

        // todo highlight sample
        binding.toolbarTitle.customSelectionActionModeCallback = MyCallback(requireContext(), binding.toolbarTitle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // view binding
        mBinding = null
    }

    override fun onClick(v: View?) {
        when(v) {
            binding.toolbarMenuBtn -> {
                startActivityForResult(Intent(context, PraySetActivity::class.java), 100)
            }
            binding.prevBtn -> {
                if(currentPage == 0) return

                currentPage -= 1
                setOnPagingColor(currentPage)
            }
            binding.nextBtn -> {
                if(currentPage == 2) return

                currentPage += 1
                setOnPagingColor(currentPage)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 100) {
            if(resultCode == Activity.RESULT_OK) {
                val lang = data!!.extras!!.getString("lang").toString()
                val date = data!!.extras!!.getString("date").toString()

                if(prayNoteMap[date] != null) {
                    currentPage = prayNoteList.indexOf(date)
                    setOnPagingColor(currentPage)
                } else {
                    Utils.onToast(context, "해달 날짜 기도수첩이 없습니다.")
                }
            }
        }
    }

    private fun setOnPagingColor(position: Int) {
        when(position) {
            0 -> {
                binding.prayNoteImg.setImageResource(R.drawable.pray_note_1)
                binding.pagingIndicator1.setBackgroundDrawable(resources.getDrawable(R.drawable.btn_page_on))
                binding.pagingIndicator2.setBackgroundDrawable(resources.getDrawable(R.drawable.btn_page_off))
                binding.pagingIndicator3.setBackgroundDrawable(resources.getDrawable(R.drawable.btn_page_off))
            }
            1 -> {
                binding.prayNoteImg.setImageResource(R.drawable.pray_note_2)
                binding.pagingIndicator1.setBackgroundDrawable(resources.getDrawable(R.drawable.btn_page_off))
                binding.pagingIndicator2.setBackgroundDrawable(resources.getDrawable(R.drawable.btn_page_on))
                binding.pagingIndicator3.setBackgroundDrawable(resources.getDrawable(R.drawable.btn_page_off))

            }
            2 -> {
                binding.prayNoteImg.setImageResource(R.drawable.pray_note_3)
                binding.pagingIndicator1.setBackgroundDrawable(resources.getDrawable(R.drawable.btn_page_off))
                binding.pagingIndicator2.setBackgroundDrawable(resources.getDrawable(R.drawable.btn_page_off))
                binding.pagingIndicator3.setBackgroundDrawable(resources.getDrawable(R.drawable.btn_page_on))
            }
        }
    }
}