package com.coram.spy237.ui.pray_note

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.coram.spy237.databinding.FragmentPrayNoteBinding
import com.coram.spy237.db.DbOpenHelper
import com.coram.spy237.model.db.CountryModel
import com.coram.spy237.util.Utils

class PrayNoteFragment : Fragment(), View.OnClickListener {
    // view binding
    private var mBinding: FragmentPrayNoteBinding? = null
    private val binding get() = mBinding!!

    // DB
    private lateinit var dbHelper: DbOpenHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // view binding
        mBinding = FragmentPrayNoteBinding.inflate(layoutInflater)
        return binding.root

        view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //db
        dbHelper = DbOpenHelper(context)

        binding.toolbarMenuBtn.setOnClickListener(this)
        dbHelper.insertCountry(CountryModel(
            0,
            "인도네시아",
            "아시아",
            "12",
            "2233",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQH_9ICYeo7xzTvf7f9o9LiKnGxkeBG89jGN3GRq7pllg&s"
        ))

        dbHelper.insertCountry(CountryModel(
            0,
            "방글라데시",
            "아시아",
            "1",
            "56",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f9/Flag_of_Bangladesh.svg/383px-Flag_of_Bangladesh.svg.png"
        ))

        dbHelper.insertCountry(CountryModel(
            0,
            "파키스탄",
            "아시아",
            "3",
            "233",
            "https://world.moleg.go.kr/oweb/images/countryFlag/PK_L.png"
        ))

        dbHelper.insertCountry(CountryModel(
            0,
            "베트남",
            "아시아",
            "7",
            "202",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/Flag_of_Vietnam.svg/225px-Flag_of_Vietnam.svg.png"
        ))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // view binding
        mBinding = null
    }

    override fun onClick(v: View?) {
        when(v) {
            binding.toolbarMenuBtn -> {
                startActivityForResult(Intent(context, PraySetActivity::class.java), 100)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 100) {
            if(resultCode == Activity.RESULT_OK)
                Utils.onToast(activity, data!!.extras!!.getString("lang").toString())
        }
    }
}