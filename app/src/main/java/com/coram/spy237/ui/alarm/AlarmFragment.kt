package com.coram.spy237.ui.alarm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.coram.spy237.MainActivity
import com.coram.spy237.databinding.FragmentAlarmBinding
import com.coram.spy237.model.AlarmModel
import com.coram.spy237.model.CalendarItemModel
import com.coram.spy237.ui.alarm.adapter.CalendarHeaderAdapter
import java.util.*

class AlarmFragment : Fragment(), View.OnClickListener {
    // view binding
    private var mBinding: FragmentAlarmBinding? = null
    private val binding get() = mBinding!!

    private var calendar: Calendar? = null
    private var year = 0
    private  var month:Int = 0
    private  var date:Int = 0
    private  var maxDateOfMonth:Int = 0
    private  var firstDayOfMonth:Int = 0
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

        setMissionBarEnergy(40)
        initAlarmRecycler(AlarmModel.getTestList())


        setCurrentDate()
        setCalendarDate(year, month, date)
        setItemList(maxDateOfMonth, firstDayOfMonth)

        initWeekCalendar(itemList!!)

        binding.prayAddBtn.setOnClickListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // view binding
        mBinding = null
    }

    override fun onClick(view: View?) {
        if (view == binding.prayAddBtn) {
            (activity as MainActivity).replaceFragmentWithBackPress(AlarmAddFragment.newInstance(AlarmAddFragment.BUNDLE_VAL_FLAG_ADD))
        }
    }

    private fun setMissionBarEnergy(percent: Int) {
        binding.progressBar.progress = percent
    }

    private fun initAlarmRecycler(itemList: ArrayList<AlarmModel>) {
        val adapter = AlarmAdapter(requireContext(), itemList, 1)
        binding.prayRecycler.adapter = adapter
    }

    private fun initWeekCalendar(itemList: ArrayList<CalendarItemModel>) {
        val adapter = CalendarHeaderAdapter(itemList)
        binding.weekCalendar.adapter = adapter
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

            // TODO: 2022-09-09 가데이터
            calendarItemModel.isChecked = i == 3
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
}