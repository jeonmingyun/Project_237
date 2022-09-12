package com.coram.spy237.ui.pray_note

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.coram.spy237.databinding.ActivityPraySetBinding
import com.coram.spy237.util.PrefManager
import com.coram.spy237.util.Utils
import java.util.*

class PraySetActivity : AppCompatActivity(), View.OnClickListener {
    // view binding
    private var mBinding: ActivityPraySetBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // view binding
        mBinding = ActivityPraySetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSearchSpinner(arrayListOf("한국어", "English"))

        binding.toolbarCloseBtn.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        // view binding
        mBinding = null
    }

    private fun initSearchSpinner(itemList: ArrayList<String>) {
        val adapter: ArrayAdapter<String> =
            ArrayAdapter(this, R.layout.simple_spinner_item, itemList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.languageSpin.adapter = adapter
    }

    override fun onClick(v: View?) {
        when(v) {
            binding.toolbarCloseBtn -> {
                onFinish()
            }
        }
    }

    private fun onFinish() {
        PrefManager.setString(this, PrefManager.PREF_PRAY_NOTE_LANG, binding.languageSpin.selectedItem.toString())
        // TODO: 2022-09-11 radio group 선택 데이터 전송 
        PrefManager.setString(this, PrefManager.PREF_PRAY_NOTE_HIGHLIGHT, binding.highlightRadioGroup.toString())

        val intent = intent
        intent.putExtra("lang", binding.languageSpin.selectedItem.toString())
        setResult(RESULT_OK, intent)

        finish()
    }

    override fun onBackPressed() {
        onFinish()
    }
}