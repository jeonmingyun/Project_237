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
        "복음주의 교회의 성장으로 필리핀 현장에 인구성장률보다 그리스도인의 수가 빠르게 늘게 하심에 감사합니다. 이들이 영적으로 강력한 복음 공동체로 전역에서 전도 제자 운동을 일으키고 오직 복음만 전하는 교회가 확산되게 하옵소서. 4만 3000개의 지역 중 2만 3000개 지역에 교회가 없습니다. 복음에 생을 건 전도자들이 무교회 지역에 교회를 개척하는  비전을 품게 하옵소서.",
        "하나님 아버지, 오늘도 잠시 멈춰 주님을 바라봅니다. 죽기까지 나를 사랑하셔서 나를 살리시기 위해 예수 그리스도를 통해 십자가에 못 박히시고, 하난미 만나는 가장 쉬운 길을 열어주심에 감사합니다.\n" +
                "하나님을 떠나 올 수 밖에 없는 영적문제와 상처들로 똘똘 뭉쳐 버린 나의 틀린 기준들이 말씀 앞에서 다 깨져 하나님이 창조하신 무거운 짐이 없는 원래 모습을 회복시켜 주옵소서.\n" +
                "하나님이 알려주신 방법인 예배와 기도를 통해 오해 되고 잘못 섞인 복음이 치유 되어 참 행복과 기쁨과 감사로 하나님 자녀의 모든 것을 정복하고 다스리는 신분과 권세를 누리게 하옵소서.\n" +
                "지금도 나와 함께 하시고 살아서 역사하시는 예수 그리스도 이름으로 기도드립니다.",
        "우리의 원래 모습은 창조주 하나님만이 아시고 하나님이 창조하신 원래 모습을 우리가 회복 되길 원합니다.\n" +
                "복음 외에 많은 것들이 섞여져버린 우리의 창3,6,11의 체질이 원래 내 모습이 아님을 알고 원래 무거운 짐이 없는 모습으로 창조하시고 가장 쉽고 즐거운 예배와 기도의 축복을 누리는 제가 되게 하옵소서! \n" +
                "우리를 가장 사랑하셔서 우리를 살리시려고 예수 그리스도를 보내셨고 그 복음이 나의 복음이 되길 소망하며 반드시 치유하실 것을 믿는 믿음으로! 지금도 살아서 우리와 함께 하시는 예수 그리스도 이름으로 기도드립니다.",
        "하나님 아버지 하나님의 절대계획 속에 있는 오늘, 하나님 자녀된 신분에 집중하길 원합니다! 예수가 나의 그리스도 되시고 성령이 함께하시고 인도하시기에 염려하지 않고 하나님을 전적으로 신뢰하는 믿음으로 살아가게 하옵소서. 오늘 나에게 주신 말씀에 집중할 때 주시는 하나님의 절대계획을 깨닫고 나의 모든 여정이 살리는 여정 속에 있게 하옵소서 예수 그리스도 이름으로 기도합니다",
        "예수 그리스도를 믿음으로 말미암아 완전한 구원 얻게 하심에 감사를 드립니다. 오늘도 나를 향한 하나님의 절대계획울 말씀 속에서 확인 하게 하옵소서. 이를 위해 오늘도 하나님 자녀된 신분에 집중하길 소원합니다. 성삼위 하나님이 지금도 나와 함께 하시고 나를 인도하시고 보호하심을 믿습니다! 이때, 내게 예수가 그리스도되신다는 믿음이 완존 회복되는 영적 상태를 허락하옵소서. 그리스도 안에 모든 것 다 있음을 고백함 속에 오늘도 달려가게 하옵소서"
    )
    private val PRAY_HEAD = arrayListOf<String>(
        "필리핀 | 요한복음 11:25-26",
        "필리핀 | 시편 27:14",
        "필리핀 | 야고보서 5:13",
        "필리핀 | 시편 85:2",
        "필리핀 | 잠언 19:17",
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
            bundleModel!!.isSucceed = "16000101"
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