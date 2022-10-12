package com.coram.spy237.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.coram.spy237.MainActivity
import com.coram.spy237.R
import com.coram.spy237.model.db.CountryModel
import com.coram.spy237.ui.country_info.CountryDetailFragment
import com.coram.spy237.util.Utils

class CountryAdapter(val context: Context, var itemList: List<CountryModel>) :
    RecyclerView.Adapter<CountryAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_country_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var item: CountryModel? = null
        private val name: TextView = itemView.findViewById(R.id.name)
        private val flag: ImageView = itemView.findViewById(R.id.flag)
        private val people: TextView = itemView.findViewById(R.id.people)
        private val prayCount: TextView = itemView.findViewById(R.id.prayCount)

        fun bind(item: CountryModel) {
            this.item = item

            name.text = item.name
            Glide.with(itemView).load(item.flagUrl.toInt()).into(flag)
            people.text = "선교사님 : ${item.peopleCount}"
            prayCount.text = "기도수 : ${item.prayCount}"

            itemView.setOnClickListener {
                // TODO: 국가 상세 페이지 제작 후 수정
                // TEST_DATA 검색 > 국가 검색 > 국가 선택 > 국가 상세
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
                        Utils.onToast(context, "해당 국가의 상세 정보가 없습니다")
                    }
                }
            }
        }

    }
}