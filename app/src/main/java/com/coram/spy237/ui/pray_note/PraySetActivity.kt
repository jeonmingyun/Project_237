package com.coram.spy237.ui.pray_note

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.Spanned
import android.text.style.BackgroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.coram.spy237.R
import com.coram.spy237.databinding.ActivityPraySetBinding
import com.coram.spy237.util.PrefManager
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
        initHighlightRadioGroup()
        exportTextSpannable()

        binding.toolbarCloseBtn.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        // view binding
        mBinding = null
    }

    private fun initHighlightRadioGroup() {
        val color:Int = PrefManager.getInt(this, PrefManager.PREF_PRAY_NOTE_HIGHLIGHT)

        when(color) {
            R.color.app_green -> {
                binding.radioBtnGreen.isChecked
            }
            R.color.app_pink -> {
                binding.radioBtnRed.isChecked
            }
            R.color.app_gray_dark -> {
                binding.radioBtnGray.isChecked
            }
            else ->{
                binding.radioBtnYellow.isChecked
            }
        }
    }

    private fun initSearchSpinner(itemList: ArrayList<String>) {
        val adapter: ArrayAdapter<String> =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, itemList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.languageSpin.adapter = adapter
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.toolbarCloseBtn -> {
                onFinish()
            }
        }
    }

    private fun exportTextSpannable() {
        val wordToSpan: Spannable = binding.exportText.text as Spannable

        wordToSpan.setSpan(
            StyleSpan(Typeface.BOLD), 17, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.exportText.text = wordToSpan
    }

    private fun onFinish() {
        PrefManager.setString(
            this,
            PrefManager.PREF_PRAY_NOTE_LANG,
            binding.languageSpin.selectedItem.toString()
        )

        saveHighlightPref()

        val datePicker = binding.calender
        val day: Int = datePicker.getDayOfMonth()
        val month: Int = datePicker.getMonth() + 1
        val year: Int = datePicker.getYear()

        val intent = intent
        intent.putExtra("lang", binding.languageSpin.selectedItem.toString())
        intent.putExtra("date", "$year-$month-$day")
        setResult(RESULT_OK, intent)

        finish()
    }

    private fun saveHighlightPref() {
        val checkedId = binding.highlightRadioGroup.checkedRadioButtonId
        var value: Int = resources.getColor(R.color.app_yellow)
        when(checkedId) {
            binding.radioBtnGreen.id -> {
                    resources.getColor(R.color.app_green)
            }
            binding.radioBtnRed.id -> {
                value = resources.getColor(R.color.app_pink)
            }
            binding.radioBtnGray.id -> {
                value = resources.getColor(R.color.app_gray_dark)
            }
            else ->{
                value = resources.getColor(R.color.app_yellow)
            }
        }
        PrefManager.setInt(this, PrefManager.PREF_PRAY_NOTE_HIGHLIGHT, value)
    }

    override fun onBackPressed() {
        onFinish()
    }
}