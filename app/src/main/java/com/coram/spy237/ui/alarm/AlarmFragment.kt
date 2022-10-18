package com.coram.spy237.ui.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.coram.spy237.databinding.FragmentAlarmBinding
import com.coram.spy237.db.DbOpenHelper
import com.coram.spy237.db.DbTable
import com.coram.spy237.model.CalendarItemModel
import com.coram.spy237.model.db.AlarmModel
import com.coram.spy237.receiver.AlarmReceiver
import com.coram.spy237.ui.alarm.adapter.AlarmAdapter
import com.coram.spy237.ui.alarm.adapter.CalendarHeaderAdapter
import com.coram.spy237.util.DateFormatUtil
import java.util.*
import kotlin.collections.ArrayList

class AlarmFragment : Fragment(), View.OnClickListener {
    // view binding
    private var mBinding: FragmentAlarmBinding? = null
    private val binding get() = mBinding!!

    // DB
    private lateinit var dbHelper: DbOpenHelper

    // 캘린더 가데이터
    private var checkedCalendar = arrayListOf<Int>()

    private var calendar: Calendar? = null
    private var year = 0
    private var month: Int = 0
    private var date: Int = 0
    private var maxDateOfMonth: Int = 0
    private var firstDayOfMonth: Int = 0
    private var itemList: ArrayList<CalendarItemModel>? = null

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

        //db
        dbHelper = DbOpenHelper(context)

        binding.prayAddBtn.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        val alarmList = getAllAlarm()
        initAlarmRecycler(alarmList)
        insertDailyMission(alarmList)
        initDailyMission(selectDailyMission(DateFormatUtil.getCurrentDate(DateFormatUtil.DATE_FORMAT_5)))

        // TODO: 2022-09-20 알람
//        setAlarm(alarmList)
    }

    private fun setAlarm(alarmList: ArrayList<AlarmModel>) {
        val alarmManager = requireContext().getSystemService(ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, AlarmReceiver::class.java)  // 1
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            AlarmReceiver.NOTIFICATION_ID,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        for (item in alarmList) {
            val triggerTime = (SystemClock.elapsedRealtime()  // 4
                    + 5 * 1000)
            alarmManager.set(   // 5
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                triggerTime,
                pendingIntent
            )
        }

        if (alarmList.size == 0) {
            alarmManager.cancel(pendingIntent)
        }

    }

    private fun insertDailyMission(alarmList: ArrayList<AlarmModel>) {
        val succeedList = alarmList.filter {
            it.isSucceedDate()
        }
        if ( succeedList.isNotEmpty() && succeedList.size == alarmList.size) {
            dbHelper.insertDailyMission(DateFormatUtil.getCurrentDate(DateFormatUtil.DATE_FORMAT_3))
        }
    }

    /**
     * @param date yyyy-MM
     * @return dd
     */
    private fun selectDailyMission(date: String): ArrayList<Int> {
        val cursor: Cursor = dbHelper.selectDailyMissionOfMonth(date)
        val list = arrayListOf<Int>()
        var dateOfMonth = ""

        while (cursor.moveToNext()) {
            dateOfMonth =
                cursor.getString(cursor.getColumnIndexOrThrow(DbTable.DailyMission.COLUMN_DATE))
            list += DateFormatUtil.formatChangeDateString(
                dateOfMonth,
                DateFormatUtil.DATE_FORMAT_3,
                DateFormatUtil.DATE_FORMAT_6
            ).toInt()
        }
        cursor.close()
        return list
    }

    /**
     * @param dateList yyyy-MM-dd list
     */
    private fun initDailyMission(dateList: ArrayList<Int>) {
        checkedCalendar = dateList
        setCurrentDate()
        setCalendarDate(year, month, date)
        setItemList(maxDateOfMonth, firstDayOfMonth)
        initWeekCalendar(itemList!!)
        setMissionBarEnergy(checkedCalendar.size * 100 / maxDateOfMonth)
        binding.rateText.text = "${checkedCalendar.size} / $maxDateOfMonth"
        this.itemList
    }


    override fun onDestroyView() {
        super.onDestroyView()
        // view binding
        mBinding = null
    }

    override fun onClick(view: View?) {
        if (view == binding.prayAddBtn) {
            val intent = Intent(context, AlarmAddActivity::class.java)
            intent.putExtra(AlarmAddActivity.BUNDLE_KEY_FLAG, AlarmAddActivity.BUNDLE_VAL_FLAG_ADD)
            startActivity(intent)
        }
    }

    private fun setMissionBarEnergy(percent: Int) {
        binding.progressBar.progress = percent
    }

    private fun initAlarmRecycler(itemList: ArrayList<AlarmModel>) {
        val currentTime = DateFormatUtil.getCurrentDate(DateFormatUtil.DATE_FORMAT_2)
        var position = 0
        for (i in 0 until itemList.size) {
            if (itemList[i].time.compareTo(currentTime) > 0) {
                position = i
                break
            }
        }
        val adapter = AlarmAdapter(requireContext(), itemList, position)
        binding.prayRecycler.adapter = adapter
    }

    private fun initWeekCalendar(itemList: ArrayList<CalendarItemModel>) {
        val adapter = CalendarHeaderAdapter(itemList)
        binding.weekCalendar.adapter = adapter
        binding.weekCalendar.scrollToPosition(date - 1)
    }

    private fun setCurrentDate(year: Int, month: Int, date: Int) {
        this.year = year
        this.month = month
        this.date = date
    }

    private fun setCurrentDate() {
        calendar = Calendar.getInstance()
        year = calendar!!.get(Calendar.YEAR)
        month = calendar!!.get(Calendar.MONTH)
        date = calendar!!.get(Calendar.DATE)
    }

    private fun setCalendarDate(year: Int, month: Int, date: Int) {
        calendar!!.set(year, month, date)
        maxDateOfMonth = calendar!!.getActualMaximum(Calendar.DATE)
        calendar!!.set(Calendar.DAY_OF_MONTH, 1)
        firstDayOfMonth = calendar!!.get(Calendar.DAY_OF_WEEK)
    }

    private fun setItemList(maxDateOfMonth: Int, firstDayOfMonth: Int) {
        var calendarItemModel: CalendarItemModel
        itemList = ArrayList<CalendarItemModel>()
        var dateFormat: String
        var day: Int

        for (i in 1..maxDateOfMonth) {
            dateFormat = year.toString() + "." + month + 1 + "." + i
            day = (i + firstDayOfMonth - 1) % 7
            calendarItemModel = CalendarItemModel()
            calendarItemModel.dateOfMonth = i.toString() // 날짜
            calendarItemModel.dayOfWeek = dayStringFormat(day) // 요일
            calendarItemModel.date = dateFormat // 2020.01.01
            calendarItemModel.isChecked = checkedCalendar.contains(i)
            itemList?.add(calendarItemModel)
        }
    }

    private fun dayStringFormat(day: Int): String? {
        return when (day) {
            1 -> "SUN"
            2 -> "MON"
            3 -> "TUE"
            4 -> "WED"
            5 -> "THU"
            6 -> "FRI"
            0 -> "SAT"
            else -> " "
        }
    }

    fun getAllAlarm(): ArrayList<AlarmModel> {
        val cursor: Cursor = dbHelper.selectAllAlarm()
        val alarmList = arrayListOf<AlarmModel>()

        while (cursor.moveToNext()) {
            alarmList += AlarmModel(
                cursor.getInt(cursor.getColumnIndexOrThrow(DbTable.Alarm.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(DbTable.Alarm.COLUMN_TIME)),
                cursor.getString(cursor.getColumnIndexOrThrow(DbTable.Alarm.COLUMN_CONTENT)),
                cursor.getString(cursor.getColumnIndexOrThrow(DbTable.Alarm.COLUMN_HEADER_CONTENT)),
                cursor.getString(cursor.getColumnIndexOrThrow(DbTable.Alarm.COLUMN_IS_SUCCEED)),
                cursor.getString(cursor.getColumnIndexOrThrow(DbTable.Alarm.COLUMN_IS_SOUND_ALARM)),
                cursor.getString(cursor.getColumnIndexOrThrow(DbTable.Alarm.COLUMN_IS_VIB_ALARM)),
                cursor.getString(cursor.getColumnIndexOrThrow(DbTable.Alarm.COLUMN_IS_PUSH_ALARM))
            )
        }
        cursor.close()
        return alarmList
    }

}