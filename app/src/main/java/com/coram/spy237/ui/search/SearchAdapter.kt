package com.coram.spy237.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.coram.spy237.R
import com.coram.spy237.model.SearchModel

class SearchAdapter(val context: Context, var itemList: ArrayList<SearchModel>) :
    RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_country_search_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var item: SearchModel? = null
        private val searchItemIcon: ImageView = itemView.findViewById(R.id.searchItemIcon)
        private val searchItemText: TextView = itemView.findViewById(R.id.searchItemText)

        fun bind(item: SearchModel) {
            this.item = item
            setSearchIcon(item)
            searchItemText.text = item.searchText
        }

        fun setSearchIcon(item: SearchModel) {
            if (item.imageUri.isBlank()) {
                searchItemIcon.visibility = View.GONE
            } else {
                searchItemIcon.visibility = View.VISIBLE
                Glide.with(itemView).load(item.imageUri).into(searchItemIcon)
            }
        }
    }
}