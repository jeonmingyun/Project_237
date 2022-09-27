package com.coram.spy237

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.coram.spy237.databinding.ActivityMainBinding
import com.coram.spy237.db.DbOpenHelper
import com.coram.spy237.model.db.CountryModel
import com.coram.spy237.ui.alarm.AlarmFragment
import com.coram.spy237.ui.country_info.CountryInfoFragment
import com.coram.spy237.ui.pray_note.PrayNoteFragment
import com.coram.spy237.ui.search.SearchFragment

class MainActivity : AppCompatActivity() {
    // view binding
    private var mBinding: ActivityMainBinding? = null
    private val binding get() = mBinding!!
    // DB
    private lateinit var dbHelper: DbOpenHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // view binding
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //db
        dbHelper = DbOpenHelper(this)
        
        supportFragmentManager.beginTransaction().add(R.id.mainFrame, CountryInfoFragment()).commit()

        binding.mainBottomMenu.itemIconTintList = null
        binding.mainBottomMenu.setOnItemSelectedListener {
            replaceFragment(
                when (it.itemId) {
                    R.id.menu_home -> CountryInfoFragment()
                    R.id.menu_search -> SearchFragment()
                    R.id.menu_alarm -> AlarmFragment()
                    R.id.menu_pray_note -> PrayNoteFragment()
//                    R.id.menu_my_page -> MyPageFragment()
                    else -> CountryInfoFragment()
                }
            )
            true
        }

        // TODO: 2022-09-27 테스트용 db
//        setTestDbCountry()
    }

    override fun onDestroy() {
        super.onDestroy()
        // view binding
        mBinding = null
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainFrame, fragment)
            .commit()
    }

    fun replaceFragmentWithBackPress(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainFrame, fragment)
            .addToBackStack(null)
            .commit()
    }

    // 샘플용 국가 정보
    fun setTestDbCountry() {
        val countryTestList = arrayOf(
            CountryModel(0, "마다가스카르", "", "Africa", "12", "2333", R.drawable.flag_mada.toString()),
            CountryModel(0, "인도네시아", "", "Asia", "12", "2333", R.drawable.flag_indonesia.toString()),
            CountryModel(0, "방글라데시", "", "Asia", "3", "234", R.drawable.flag_bangladesh.toString()),
            CountryModel(0, "파키스탄", "", "Asia", "5", "345", R.drawable.flag_pakistan.toString()),
            CountryModel(0, "베트남", "", "Asia", "6", "772", R.drawable.flag_vietnam.toString()),
            CountryModel(0, "아프리카", "", "Africa", "6", "772", R.drawable.flag_vietnam.toString()),
            CountryModel(0, "아메리카", "", "America", "6", "772", R.drawable.flag_vietnam.toString()),
            CountryModel(0, "유럽", "", "Europe", "6", "772", R.drawable.flag_vietnam.toString()),
            CountryModel(0, "오세아니아", "", "Oceania", "6", "772", R.drawable.flag_vietnam.toString()),
        )
        
        for(country in countryTestList) {
            dbHelper.insertCountry(country)
        }
    }
}