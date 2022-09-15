package com.coram.spy237.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.coram.spy237.R
import com.coram.spy237.databinding.FragmentSearchBinding
import com.coram.spy237.model.SearchModel
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import java.util.*

class SearchFragment : Fragment() {
    // 샘플용 데이터
    private val mockDataRecentSearchItems =
        arrayOf("김동길", "전민균", "김운비", "신윤수", "김성재", "김성재", "유효진", "김상아", "오수려")
    private val mockDataRecommendSearchItems =
        arrayOf("김동길", "전민균", "김운비", "신윤수", "김성재", "김성재", "유효진", "김상아", "오수려")

    // view binding
    private var mBinding: FragmentSearchBinding? = null
    private val binding get() = mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // view binding
        mBinding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSearchSpinner(arrayListOf("선교국가", "선교사명"))

        initSearchTagRecycler(binding.recentSearchWordRecycler, mockDataRecentSearchItems)
        initSearchTagRecycler(binding.recommendSearchWordRecycler, mockDataRecommendSearchItems)

        binding.searchEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                val searchText = p0.toString()
                onSearch(searchText)
            }
        })
        // todo 검색 기능 : 엔터
//        binding.searchEt.setOnKeyListener { _, keyCode, event ->
//            if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
//                val searchText = binding.searchEt.text.toString()
//                onSearch(searchText)
//                true
//            } else {
//                false
//            }
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // view binding
        mBinding = null
    }

    private fun onSearch(searchText: String) {
        val searchType = binding.searchSpinner.selectedItem.toString()

        if (searchText.isBlank()) {
            initSearching(false)
        } else {
            if (searchType == "선교국가")
                initSearchRecycler(SearchModel.getTestCountryList(searchText))
            else
                initSearchRecycler(SearchModel.getTestNameList(searchText))

            initSearching(true)
        }
    }

    private fun initSearching(isSearching: Boolean) {
        if (isSearching) {
            binding.searchWordContainer.visibility = View.GONE
            binding.searchRecycler.visibility = View.VISIBLE
        } else {
            binding.searchWordContainer.visibility = View.GONE // 필요시 visible
            binding.searchRecycler.visibility = View.GONE
        }
    }

    private fun initSearchRecycler(itemList: List<SearchModel>) {
        val adapter = SearchAdapter(requireContext(), itemList)
        binding.searchRecycler.adapter = adapter
    }

    private fun initSearchTagRecycler(recyclerView: RecyclerView, wordArr: Array<String>) {
        val flexboxLayoutManager = FlexboxLayoutManager(requireContext())
        flexboxLayoutManager.justifyContent = JustifyContent.FLEX_START
        recyclerView.layoutManager = flexboxLayoutManager

        recyclerView.adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): RecyclerView.ViewHolder {
                val layout = if (recyclerView == binding.recentSearchWordRecycler) {
                    R.layout.item_recent_search_word_list
                } else {
                    R.layout.item_recommend_search_word_list
                }

                val itemView =
                    LayoutInflater.from(requireContext()).inflate(layout, parent, false)
                return object : RecyclerView.ViewHolder(itemView) {}
            }

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                val searchWordBtn = holder.itemView.findViewById<Button>(R.id.searchWordBtn)
                searchWordBtn.text = wordArr[position]
                searchWordBtn.setOnClickListener {
                    onSearch(searchWordBtn.text.toString())
                }
            }

            override fun getItemCount(): Int {
                return wordArr.size
            }
        }
    }

    private fun initSearchSpinner(itemList: ArrayList<String>) {
        val adapter: ArrayAdapter<String> =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, itemList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.searchSpinner.adapter = adapter
        binding.searchSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                setSearchHint(position)
            }
        }
    }

    private fun setSearchHint(position: Int) {
        when (position) {
            0 -> binding.searchEt.hint = "국가명을 입력해주세요"
            1 -> binding.searchEt.hint = "이름을 입력해주세요"
        }
    }
}