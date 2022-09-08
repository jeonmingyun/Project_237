package com.coram.spy237

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.coram.spy237.databinding.ActivityMainBinding
import com.coram.spy237.ui.alarm.AlarmFragment
import com.coram.spy237.ui.country_info.CountryInfoFragment
import com.coram.spy237.ui.pray_note.PrayNoteFragment
import com.coram.spy237.ui.search.SearchFragment

class MainActivity : AppCompatActivity() {
    // view binding
    private var mBinding: ActivityMainBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // view binding
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportFragmentManager.beginTransaction().add(R.id.mainFrame, CountryInfoFragment()).commit()

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
}