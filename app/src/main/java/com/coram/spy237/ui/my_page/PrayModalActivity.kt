package com.coram.spy237.ui.my_page

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.coram.spy237.databinding.ActivityPrayModalBinding
import com.coram.spy237.db.DbOpenHelper
import com.coram.spy237.model.db.AlarmModel
import com.coram.spy237.util.DateFormatUtil

class PrayModalActivity : AppCompatActivity() {
    // view binding
    private var mBinding: ActivityPrayModalBinding? = null
    private val binding get() = mBinding!!

    // DB
    private lateinit var dbHelper: DbOpenHelper

    lateinit var model: AlarmModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // view binding
        mBinding = ActivityPrayModalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //db
        dbHelper = DbOpenHelper(this)

        model = intent.getParcelableExtra<AlarmModel>("model")!!
        binding.prayHeaderContent.text = model.headerContent
        binding.modalContent.text = model.content

        binding.closeBtn.setOnClickListener {
            finish()
        }
        binding.appCompatButton.setOnClickListener {
            model.isSucceed = DateFormatUtil.getCurrentDate(DateFormatUtil.DATE_FORMAT_8)
            dbHelper.updateAlarm(model)
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // view binding
        mBinding = null
    }
}