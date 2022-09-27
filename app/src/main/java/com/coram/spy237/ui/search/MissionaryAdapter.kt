package com.coram.spy237.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.coram.spy237.MainActivity
import com.coram.spy237.R
import com.coram.spy237.model.MissionaryModel
import com.coram.spy237.ui.country_info.CountryDetailFragment
import com.coram.spy237.ui.missionary.MissionaryProfileFragment
import com.coram.spy237.util.Utils

class MissionaryAdapter(val context: Context, var itemList: List<MissionaryModel>) :
    RecyclerView.Adapter<MissionaryAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_missionary_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var item: MissionaryModel? = null
        private val profileImg: ImageView = itemView.findViewById(R.id.profileImg)
        private val name: TextView = itemView.findViewById(R.id.name)
        private val location: TextView = itemView.findViewById(R.id.location)

        fun bind(item: MissionaryModel) {
            this.item = item

            profileImg.setImageResource(item.profileImg)
            name.text = item.name
            location.text = item.location

            itemView.setOnClickListener {
                when (item.name) {
                    "김동길" -> {
                        (context as MainActivity).replaceFragmentWithBackPress(MissionaryProfileFragment())
                    }
                    else -> {
                        Utils.onToast(context, "선교사님 정보가 없습니다")
                    }
                }
            }
        }

    }
}