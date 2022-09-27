package com.coram.spy237.ui.search

import android.database.Cursor
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.coram.spy237.R
import com.coram.spy237.databinding.FragmentSearchBinding
import com.coram.spy237.db.DbOpenHelper
import com.coram.spy237.db.DbTable
import com.coram.spy237.model.MissionaryModel
import com.coram.spy237.model.SearchModel
import com.coram.spy237.model.db.CountryModel
import com.coram.spy237.ui.country_info.adapter.ViewPagerAdapter
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.android.material.tabs.TabLayoutMediator

class SearchFragment : Fragment() {
    // 샘플용 데이터
    private val mockDataRecentSearchItems =
        arrayOf("김동길", "전민균", "김운비", "신윤수", "김성재", "김성재", "유효진", "김상아", "오수려")
    private val mockDataRecommendSearchItems =
        arrayOf("김동길", "전민균", "김운비", "신윤수", "김성재", "김성재", "유효진", "김상아", "오수려")

    // view binding
    private var mBinding: FragmentSearchBinding? = null
    private val binding get() = mBinding!!

    // DB
    private lateinit var dbHelper: DbOpenHelper

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
        //db
        dbHelper = DbOpenHelper(context)

        initSearchSpinner(arrayListOf("선교국가", "선교사명"))
        initSearchTagRecycler(binding.recentSearchWordRecycler, mockDataRecentSearchItems)
        initSearchTagRecycler(binding.recommendSearchWordRecycler, mockDataRecommendSearchItems)
        initViewPagerTab() // 검색 메인화면

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

        binding.searchEt.setOnKeyListener { _, keyCode, event ->
            if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                val searchText = binding.searchEt.text.toString()
                initSearchResultView(searchText)
                true
            } else {
                false
            }
        }
    }

    override fun onResume() {
        super.onResume()

        binding.searchMainContainer.visibility = View.VISIBLE
        binding.searchRecycler.visibility = View.GONE
        binding.searchResultContainer.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // view binding
        mBinding = null
    }

    private fun initSearchResultView(searchText: String) {
        binding.searchMainContainer.visibility = View.GONE
        binding.searchRecycler.visibility = View.GONE
        binding.searchResultContainer.visibility = View.VISIBLE

        val searchType = binding.searchSpinner.selectedItem.toString()
        var countryList = getAllCountry()
        var missionaryList = MissionaryModel().getTestList()
        if (searchText.isNotBlank()) {
            if (searchType == "선교국가") {
                countryList = countryList.filter { it.name.contains(searchText) }
                if (countryList.isEmpty()) missionaryList = listOf()
            } else {
                missionaryList = missionaryList.filter { it.name.contains(searchText) }
                if (missionaryList.isEmpty()) missionaryList = listOf()
            }
        }
        binding.icResult.countryCount.text = "검색결과 ${countryList.size}건"
        initCountryRecycler(countryList)
        binding.icResult.missionaryCount.text = "검색결과 ${missionaryList.size}건"
        initMissionaryRecycler(missionaryList)
    }

    private fun initCountryRecycler(itemList: List<CountryModel>) {
        binding.icResult.countryRecycler.adapter = CountryAdapter(requireContext(), itemList)
    }

    private fun initMissionaryRecycler(itemList: List<MissionaryModel>) {
        binding.icResult.missionaryRecycler.adapter = MissionaryAdapter(requireContext(), itemList)
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

    private fun getCountries(searchText: String): ArrayList<CountryModel> {
        val cursor: Cursor = dbHelper.selectCountries(searchText)
        val list = arrayListOf<CountryModel>()

        while (cursor.moveToNext()) {
            list += CountryModel(
                cursor.getInt(cursor.getColumnIndexOrThrow(DbTable.Country.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(DbTable.Country.COLUMN_NAME)),
                "",
                cursor.getString(cursor.getColumnIndexOrThrow(DbTable.Country.COLUMN_CONTINENT)),
                cursor.getString(cursor.getColumnIndexOrThrow(DbTable.Country.COLUMN_PEOPLE_COUNT)),
                cursor.getString(cursor.getColumnIndexOrThrow(DbTable.Country.COLUMN_PRAY_COUNT)),
                cursor.getString(cursor.getColumnIndexOrThrow(DbTable.Country.COLUMN_FLAG_URL))
            )
        }
        cursor.close()
        return list
    }

    private fun getAllCountry(): List<CountryModel> {
        val cursor = dbHelper.selectAllCountry()
        val alarmList = arrayListOf<CountryModel>()

        while (cursor.moveToNext()) {
            alarmList += CountryModel(
                cursor.getInt(cursor.getColumnIndexOrThrow(DbTable.Country.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(DbTable.Country.COLUMN_NAME)),
                "",
                cursor.getString(cursor.getColumnIndexOrThrow(DbTable.Country.COLUMN_CONTINENT)),
                cursor.getString(cursor.getColumnIndexOrThrow(DbTable.Country.COLUMN_PEOPLE_COUNT)),
                cursor.getString(cursor.getColumnIndexOrThrow(DbTable.Country.COLUMN_PRAY_COUNT)),
                cursor.getString(cursor.getColumnIndexOrThrow(DbTable.Country.COLUMN_FLAG_URL))
            )
        }
        cursor.close()
        return alarmList
    }

    private fun initSearching(isSearching: Boolean) {
        if (isSearching) {
            binding.searchMainContainer.visibility = View.GONE
            binding.searchResultContainer.visibility = View.GONE
            binding.searchWordContainer.visibility = View.GONE
            binding.searchRecycler.visibility = View.VISIBLE
        } else {
            binding.searchMainContainer.visibility = View.GONE
            binding.searchResultContainer.visibility = View.GONE
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

    private fun initViewPagerTab() {
        val pagerAdapter = ViewPagerAdapter(requireActivity())
        // Fragment Add
        pagerAdapter.addFragment(SearchMainFragment().newInstance("Asia"))
        pagerAdapter.addFragment(SearchMainFragment().newInstance("Africa"))
        pagerAdapter.addFragment(SearchMainFragment().newInstance("America"))
        pagerAdapter.addFragment(SearchMainFragment().newInstance("Europe"))
        pagerAdapter.addFragment(SearchMainFragment().newInstance("Oceania"))
        // Adapter
        binding.viewPager.adapter = pagerAdapter
        binding.viewPager.offscreenPageLimit = 5

        // TabLayout attach
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Asia"
                1 -> "Africa"
                2 -> "America"
                3 -> "Europe"
                4 -> "Oceania"
                else -> "Asia"
            }
        }.attach()

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val view = (binding.viewPager[0] as RecyclerView).layoutManager?.findViewByPosition(
                    position
                )
                view?.post {
                    val wMeasureSpec =
                        View.MeasureSpec.makeMeasureSpec(view.width, View.MeasureSpec.EXACTLY)
                    val hMeasureSpec =
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
                    view.measure(wMeasureSpec, hMeasureSpec)
                    if (binding.viewPager.layoutParams.height != view.measuredHeight) {
                        binding.viewPager.layoutParams =
                            (binding.viewPager.layoutParams).also { lp ->
                                lp.height = view.measuredHeight
                            }
                    }
                }
            }
        })

    }
}