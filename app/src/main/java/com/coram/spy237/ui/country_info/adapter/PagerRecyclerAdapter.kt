package com.coram.spy237.ui.country_info.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.coram.spy237.MainActivity
import com.coram.spy237.R
import com.coram.spy237.model.HeaderViewpagerModel
import com.coram.spy237.ui.country_info.CountryDetailFragment
import com.coram.spy237.util.Utils

class PagerRecyclerAdapter(
    private val context: Context,
    private val itemList: ArrayList<HeaderViewpagerModel>
) : RecyclerView.Adapter<PagerRecyclerAdapter.PagerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_header_viewpager, parent, false)
        return PagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    inner class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var item: HeaderViewpagerModel? = null
        private val countryImg: ImageView = itemView.findViewById(R.id.countryImg)
        private val countryName: TextView = itemView.findViewById(R.id.countryName)
        private val countryTitle: TextView = itemView.findViewById(R.id.countryTitle)

        fun bind(item: HeaderViewpagerModel) {
            this.item = item

            countryImg.setImageResource(item.uri)
            countryName.text = item.name
            countryTitle.text = item.title

            itemView.setOnClickListener {
                // TODO: 국가 상세 페이지 제작 후 수정
                // TEST_DATA 국가 > 상단 배너 클릭 > 국가 상세
                when (item.name) {
                    "마다가스카르" -> {
                        (context as MainActivity).replaceFragmentWithBackPress(CountryDetailFragment().newInstance(false))
                    }
                    "미국" -> {
                        (context as MainActivity).replaceFragmentWithBackPress(CountryDetailFragment().newInstance(false))
                    }
                    "필리핀" -> {
                        (context as MainActivity).replaceFragmentWithBackPress(CountryDetailFragment().newInstance(false))
                    }
                    "우크라이나" -> {
                        (context as MainActivity).replaceFragmentWithBackPress(CountryDetailFragment().newInstance(false))
                    }
                    else -> {
                        Utils.onToast(context, "해당 국가 정보가 없습니다")
                    }
                }
            }
        }
    }

}
