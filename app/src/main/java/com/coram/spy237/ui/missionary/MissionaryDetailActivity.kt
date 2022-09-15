package com.coram.spy237.ui.missionary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.coram.spy237.R
import com.coram.spy237.databinding.ActivityMissionaryDetailBinding
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class MissionaryDetailActivity : AppCompatActivity() {
    // view binding
    private var mBinding: ActivityMissionaryDetailBinding? = null
    private val binding get() = mBinding!!

    // 샘플용 데이터
    private val tagList =
        arrayOf("#설레임", "#감격", "#이슬같은청년", "#기도", "#디모데", "#선교", "#절대제자", "#세계복음화")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // view binding
        mBinding = ActivityMissionaryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTagRecycler(binding.tag, tagList)

        binding.backBtn.setOnClickListener {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // view binding
        mBinding = null
    }

    private fun initTagRecycler(recyclerView: RecyclerView, wordArr: Array<String>) {
        val flexboxLayoutManager = FlexboxLayoutManager(this)
        flexboxLayoutManager.justifyContent = JustifyContent.FLEX_START
        recyclerView.layoutManager = flexboxLayoutManager

        recyclerView.adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): RecyclerView.ViewHolder {
                val layout = if (recyclerView == binding.tag) {
                    R.layout.item_recent_search_word_list
                } else {
                    R.layout.item_recommend_search_word_list
                }

                val itemView =
                    LayoutInflater.from(this@MissionaryDetailActivity)
                        .inflate(layout, parent, false)
                return object : RecyclerView.ViewHolder(itemView) {}
            }

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                val searchWordBtn = holder.itemView.findViewById<Button>(R.id.searchWordBtn)
                searchWordBtn.text = wordArr[position]
            }

            override fun getItemCount(): Int {
                return wordArr.size
            }
        }
    }
}