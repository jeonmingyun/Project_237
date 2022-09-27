package com.coram.spy237.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.coram.spy237.MainActivity
import com.coram.spy237.databinding.FragmentSearchMainBinding
import com.coram.spy237.db.DbOpenHelper
import com.coram.spy237.db.DbTable
import com.coram.spy237.model.db.CountryModel
import com.coram.spy237.ui.country_info.CountryDetailFragment
import com.coram.spy237.util.Utils

class SearchMainFragment : Fragment() {
    // view binding
    private var mBinding: FragmentSearchMainBinding? = null
    private val binding get() = mBinding!!

    // DB
    private lateinit var dbHelper: DbOpenHelper

    val BUNDLE_CONTINENT = "BUNDLE_CONTINENT"
    lateinit var bundleDataContinent: String
    lateinit var countryList: ArrayList<CountryModel>

    fun newInstance(continent: String): SearchMainFragment {
        val args = Bundle()
        args.putString(BUNDLE_CONTINENT, continent)

        val fragment = SearchMainFragment()
        fragment.arguments = args
        return fragment
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // view binding
        mBinding = FragmentSearchMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //db
        dbHelper = DbOpenHelper(context)

        bundleDataContinent = arguments?.getString(BUNDLE_CONTINENT).toString()
        countryList = getCountryInfo(bundleDataContinent)
        setCountryInfo(countryList)

        binding.country01Container.setOnClickListener{
            setOnCountryDetail(binding.name01.text.toString())
        }
        binding.country02Container.setOnClickListener{
            setOnCountryDetail(binding.name02.text.toString())
        }
        binding.country03Container.setOnClickListener{
            setOnCountryDetail(binding.name03.text.toString())
        }
        binding.country04Container.setOnClickListener{
            setOnCountryDetail(binding.name04.text.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // view binding
        mBinding = null
    }

    private fun setOnCountryDetail(countryName: String) {
        when (countryName) {
            "마다가스카르" -> {
                (context as MainActivity).replaceFragmentWithBackPress(CountryDetailFragment().newInstance(false))
            }
            else -> {
                Utils.onToast(context, "해당 국가의 상세 정보가 없습니다")
            }
        }
    }

    private fun setCountryInfo(countryList: ArrayList<CountryModel>) {
        binding.country01Container.visibility = View.INVISIBLE
        binding.country02Container.visibility = View.INVISIBLE
        binding.country03Container.visibility = View.INVISIBLE
        binding.country04Container.visibility = View.INVISIBLE
        for (i in 0 until countryList.size) {
            when (i) {
                0 -> {
                    binding.country01Container.visibility = View.VISIBLE
                    binding.name01.text = countryList[i].name
                    binding.flag01.setImageResource(countryList[i].flagUrl.toInt())
                    binding.people01.text = "선교사님 : ${countryList[i].peopleCount}"
                    binding.prayCount01.text = "기도수 : ${countryList[i].prayCount}"
                }
                1 -> {
                    binding.country02Container.visibility = View.VISIBLE
                    binding.name02.text = countryList[i].name
                    binding.flag02.setImageResource(countryList[i].flagUrl.toInt())
                    binding.people02.text = "선교사님 : ${countryList[i].peopleCount}"
                    binding.prayCount02.text = "기도수 : ${countryList[i].prayCount}"
                }
                2 -> {
                    binding.country03Container.visibility = View.VISIBLE
                    binding.name03.text = countryList[i].name
                    binding.flag03.setImageResource(countryList[i].flagUrl.toInt())
                    binding.people03.text = "선교사님 : ${countryList[i].peopleCount}"
                    binding.prayCount03.text = "기도수 : ${countryList[i].prayCount}"
                }
                3 -> {
                    binding.country04Container.visibility = View.VISIBLE
                    binding.name04.text = countryList[i].name
                    binding.flag04.setImageResource(countryList[i].flagUrl.toInt())
                    binding.people04.text = "선교사님 : ${countryList[i].peopleCount}"
                    binding.prayCount04.text = "기도수 : ${countryList[i].prayCount}"
                }
                else -> {

                }
            }
        }
    }

    private fun getCountryInfo(continent: String): ArrayList<CountryModel> {
        val cursor = dbHelper.selectAllContinent(continent)
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
}