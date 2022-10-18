package com.coram.spy237.ui.country_info.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.coram.spy237.MainActivity
import com.coram.spy237.R
import com.coram.spy237.model.db.CountryModel
import com.coram.spy237.ui.country_info.CountryDetailFragment
import com.coram.spy237.util.Utils

class RankAdapter(
    val context: Context,
    var itemList: ArrayList<CountryModel>,
) : RecyclerView.Adapter<RankAdapter.BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankAdapter.BaseViewHolder {
        return BaseViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_rank_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RankAdapter.BaseViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private var item: CountryModel? = null
        private val countryName: TextView = itemView.findViewById(R.id.countryName)
        private val prayContent: TextView = itemView.findViewById(R.id.prayContent)

        fun bind(item: CountryModel) {
            this.item = item
            countryName.text = item.name
            prayContent.text = item.prayContent

            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if (v == this.itemView) {
                // TEST_DATA 국가 > 주간랭킹 선택 > 국가 상세
                when (item!!.name) {
                    "마다가스카르" -> {
                        (context as MainActivity).replaceFragmentWithBackPress(CountryDetailFragment().newInstance(true, item!!.name))
                    }
                    "미국" -> {
                        (context as MainActivity).replaceFragmentWithBackPress(CountryDetailFragment().newInstance(true, item!!.name))
                    }
                    "필리핀" -> {
                        (context as MainActivity).replaceFragmentWithBackPress(CountryDetailFragment().newInstance(true, item!!.name))
                    }
                    "우크라이나" -> {
                        (context as MainActivity).replaceFragmentWithBackPress(CountryDetailFragment().newInstance(true, item!!.name))
                    }
                    else -> {
                        Utils.onToast(context, "해당 국가 정보가 없습니다")
                    }
                }
            }
        }
    }

}