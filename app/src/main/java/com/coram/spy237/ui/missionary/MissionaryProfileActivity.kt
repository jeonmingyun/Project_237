package com.coram.spy237.ui.missionary

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.coram.spy237.R
import com.coram.spy237.databinding.ActivityMissionaryProfileBinding
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent

class MissionaryProfileActivity : AppCompatActivity() {
    // view binding
    private var mBinding: ActivityMissionaryProfileBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // view binding
        mBinding = ActivityMissionaryProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener {
            finish()
        }
        binding.moreBtn.setOnClickListener {
            openMissionaryDetail()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // view binding
        mBinding = null
    }

    fun openMissionaryDetail() {
        startActivity(Intent(this, MissionaryDetailActivity::class.java))
    }
}