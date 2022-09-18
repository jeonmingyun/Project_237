package com.coram.spy237.ui.alarm

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.coram.spy237.databinding.ActivityAlarmAddBinding
import com.coram.spy237.db.DbOpenHelper
import com.coram.spy237.model.db.AlarmModel
import com.coram.spy237.util.Utils
import com.google.gson.Gson
import kotlin.random.Random

class AlarmAddActivity : AppCompatActivity(), View.OnClickListener {
    // view binding
    private var mBinding: ActivityAlarmAddBinding? = null
    private val binding get() = mBinding!!

    // DB
    private lateinit var dbHelper: DbOpenHelper

    private var bundleFlag: String? = BUNDLE_VAL_FLAG_ADD
    private var bundleModel: AlarmModel? = null

    // sample
    private val PRAY_CONTENT = arrayListOf<String>(
        "111",
        "222",
        "333",
        "444",
        "555"
    )
    private val PRAY_HEAD = arrayListOf<String>(
        "Philippines | Joshua 2:1-3 1",
        "Philippines | Joshua 2:1-3 2",
        "Philippines | Joshua 2:1-3 3",
        "Philippines | Joshua 2:1-3 4",
        "Philippines | Joshua 2:1-3 5",
    )

    companion object {
        const val BUNDLE_KEY_ALARM_MODEL = "BUNDLE_KEY_ALARM_MODEL"
        const val BUNDLE_KEY_FLAG = "BUNDLE_KEY_FLAG"
        const val BUNDLE_VAL_FLAG_ADD = "BUNDLE_VAL_FLAG_ADD"
        const val BUNDLE_VAL_FLAG_EDIT = "BUNDLE_VAL_FLAG_EDIT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // view binding
        mBinding = ActivityAlarmAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //db
        dbHelper = DbOpenHelper(this)

        setBundleData()
        setAmPmPicker()
        setHourPicker()
        setMinPicker()

        setBottomBtn(bundleFlag)
        setEditLayout()

        binding.cancelBtn.setOnClickListener(this)
        binding.deleteBtn.setOnClickListener(this)
        binding.leftSaveBtn.setOnClickListener(this)
        binding.rightSaveBtn.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        // view binding
        mBinding = null
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.cancelBtn -> {
                finish()
            }
            binding.deleteBtn -> {
                bundleModel.let { dbHelper.deleteAlarm(bundleModel!!.id) }
                finish()
            }
            binding.leftSaveBtn -> { // 수정
                editBundleModel()
                bundleModel.let { dbHelper.updateAlarm(bundleModel!!) }
                finish()
            }
            binding.rightSaveBtn -> { // 추가 저장
                editBundleModel()
                bundleModel.let { dbHelper.insertAlarm(bundleModel!!) }
                finish()
            }
        }
    }

    private fun setEditLayout() {
        if (bundleFlag != BUNDLE_VAL_FLAG_EDIT) return

        val time = bundleModel!!.time
        val amPm = time.substring(0, 2)
        val hour = time.substring(3, 5)
        val min = time.substring(6)

        binding.amPmPicker.value = binding.amPmPicker.displayedValues.indexOf(amPm)
        binding.hourPicker.value = binding.hourPicker.displayedValues.indexOf(hour)
        binding.minPicker.value = binding.minPicker.displayedValues.indexOf(min)
        binding.soundSwitch.isChecked = bundleModel!!.isSoundAlarm.toBoolean()
        binding.vibSwitch.isChecked = bundleModel!!.isVibAlarm.toBoolean()
        binding.pushSwitch.isChecked = bundleModel!!.isPushAlarm.toBoolean()
    }

    private fun editBundleModel() {
        if (bundleModel == null) bundleModel = AlarmModel()

        val amPm =
            if (binding.minPicker.displayedValues[binding.amPmPicker.value] == "00") "오전" else "오후"
        val hour = binding.minPicker.displayedValues[binding.hourPicker.value]
        val min = binding.minPicker.displayedValues[binding.minPicker.value]
        val time = "$amPm ${hour}:${min}"

        bundleModel!!.time = time
        if (bundleModel!!.content.isBlank())
            bundleModel!!.content = PRAY_CONTENT[Random.nextInt(PRAY_CONTENT.size)]
        if (bundleModel!!.headerContent.isBlank())
            bundleModel!!.headerContent = PRAY_HEAD[Random.nextInt(PRAY_HEAD.size)]
        if (bundleModel!!.isSucceed.isBlank())
            bundleModel!!.isSucceed = "false"
        bundleModel!!.isSoundAlarm = binding.soundSwitch.isChecked.toString()
        bundleModel!!.isVibAlarm = binding.vibSwitch.isChecked.toString()
        bundleModel!!.isPushAlarm = binding.pushSwitch.isChecked.toString()
    }

    private fun setAmPmPicker() {
        val values = arrayOf("오전", "오후")
        binding.amPmPicker.minValue = 0
        binding.amPmPicker.maxValue = values.size - 1
        binding.amPmPicker.displayedValues = values
    }

    private fun setHourPicker() {
        val values: Array<String> = Array(12) { i ->
            val hour = i
            if (hour < 10) "0${hour}" else hour.toString()
        }

        binding.hourPicker.minValue = 0
        binding.hourPicker.maxValue = values.size - 1
        binding.hourPicker.displayedValues = values
    }

    private fun setMinPicker() {
        val values: Array<String> = Array(60) { i ->
            if (i < 10) "0${i}" else i.toString()
        }

        binding.minPicker.minValue = 0
        binding.minPicker.maxValue = values.size - 1
        binding.minPicker.displayedValues = values
    }

    private fun setBundleData() {
        bundleFlag = intent.getStringExtra(BUNDLE_KEY_FLAG)
        bundleModel = intent.getParcelableExtra(BUNDLE_KEY_ALARM_MODEL)
        Utils.onLog(Gson().toJson(bundleModel))
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