package com.coram.spy237.ui.alarm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.coram.spy237.R
import com.coram.spy237.model.AlarmModel
import com.coram.spy237.util.Utils

class AlarmAdapter(
    val context: Context,
    var itemList: ArrayList<AlarmModel>,
    private var selectedPosition: Int = -1
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var preSelectedPosition: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == selectedPosition) {
            SelectedViewHolder(
                LayoutInflater.from(context)
                    .inflate(R.layout.item_pray_list_selected, parent, false)
            )
        } else {
            BaseViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_pray_list_default, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (selectedPosition == position) {
            holder as SelectedViewHolder

            holder.bind(itemList[position])
        } else {
            holder as BaseViewHolder

            holder.bind(itemList[position])
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun setSelectedPosition(position: Int) {
        this.preSelectedPosition = this.selectedPosition
        this.selectedPosition = position
        notifyItemChanged(preSelectedPosition)
        notifyItemChanged(position)
    }

    inner class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private var item: AlarmModel? = null
        private val prayTime: TextView = itemView.findViewById(R.id.prayTime)
        private val prayContent: TextView = itemView.findViewById(R.id.prayContent)
        private val prayChkBox: CheckBox = itemView.findViewById(R.id.prayChkBox)

        fun bind(item: AlarmModel) {
            this.item = item
            prayTime.text = item.time
            prayContent.text = item.content
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if(v == this.itemView) {
                setSelectedPosition(adapterPosition)
            }
        }
    }

    inner class SelectedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var item: AlarmModel? = null
        private val prayHeaderTime: TextView = itemView.findViewById(R.id.prayHeaderTime)
        private val prayHeaderContent: TextView = itemView.findViewById(R.id.prayHeaderContent)
        private val prayHeaderChkBox: CheckBox = itemView.findViewById(R.id.prayHeaderChkBox)
        private val prayContent: TextView = itemView.findViewById(R.id.prayContent)
        private val seeMoreBtn: Button = itemView.findViewById(R.id.seeMoreBtn)

        fun bind(item: AlarmModel) {
            this.item = item
            prayHeaderTime.text = item.time
            prayHeaderContent.text = item.headerContent
            prayContent.text = item.content
        }
    }
}