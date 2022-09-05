package com.coram.spy237

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.coram.spy237.databinding.ActivityMainBinding
import com.coram.spy237.ui.search.SearchFragment
import com.coram.spy237.util.Utils
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    // view binding
    private var mBinding: ActivityMainBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // view binding
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportFragmentManager.beginTransaction().add(R.id.mainFrame, SearchFragment()).commit()

        binding.mainBottomMenu.setOnItemSelectedListener {
            Utils.onLog("dddddddddddd")
            replaceFragment(
                when (it.itemId) {
                    R.id.menu_home -> SearchFragment()
                    R.id.menu_search -> SearchFragment()
                    R.id.menu_alarm -> SearchFragment()
                    R.id.menu_my_page -> SearchFragment()
                    else -> SearchFragment()
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
        supportFragmentManager.beginTransaction().replace(R.id.mainFrame, fragment).commit()
    }
}