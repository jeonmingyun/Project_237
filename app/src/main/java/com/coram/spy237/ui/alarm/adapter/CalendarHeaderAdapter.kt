package com.coram.spy237.ui.alarm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.coram.spy237.R
import com.coram.spy237.model.CalendarItemModel
import java.util.*

class CalendarHeaderAdapter(
    private var itemList: ArrayList<CalendarItemModel>
) : RecyclerView.Adapter<CalendarHeaderAdapter.CalendarHeaderHolder>() {

    private val TAG = "CalendarHeaderAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarHeaderHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_calendar_list, parent, false)
        return CalendarHeaderHolder(view)
    }

    override fun onBindViewHolder(holder: CalendarHeaderHolder, position: Int) {
//        Log.e(TAG, itemList.get(position).getDateOfMonth());
        holder.dateOfMonth.text = itemList[position].dateOfMonth
        holder.dayOfWeek.text = itemList[position].dayOfWeek

        if (itemList[position].isChecked) {
            holder.datePoint.visibility = View.VISIBLE
            holder.dateOfMonth.visibility = View.GONE
        } else {
            holder.datePoint.visibility = View.GONE
            holder.dateOfMonth.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun addItem(addItemList: ArrayList<CalendarItemModel>?) {
        itemList.addAll(addItemList!!)
        notifyDataSetChanged()
    }

    fun updateItem(addItemList: ArrayList<CalendarItemModel>) {
        itemList = addItemList
        notifyDataSetChanged()
    }

    inner class CalendarHeaderHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var dateOfMonth: TextView
        var dayOfWeek: TextView
        var datePoint: View

        init {
            dateOfMonth = view.findViewById(R.id.header_date_of_month)
            dayOfWeek = view.findViewById(R.id.header_day_of_week)
            datePoint = view.findViewById(R.id.header_date_point)
        }
    }
}