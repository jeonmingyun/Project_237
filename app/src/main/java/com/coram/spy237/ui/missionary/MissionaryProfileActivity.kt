package com.coram.spy237.ui.missionary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.coram.spy237.databinding.ActivityMissionaryBinding

class MissionaryProfileActivity : AppCompatActivity() {
    // view binding
    private var mBinding: ActivityMissionaryBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // view binding
        mBinding = ActivityMissionaryBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        // view binding
        mBinding = null
    }
}