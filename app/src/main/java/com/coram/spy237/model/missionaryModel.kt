package com.coram.spy237.model

import android.os.Parcelable
import com.coram.spy237.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class MissionaryModel(
    val profileImg: Int = -1,
    val name: String = "",
    val location: String = ""
) : Parcelable {
    fun getTestList(): List<MissionaryModel> {
        return arrayListOf(
            MissionaryModel(
                R.drawable.profile_img,
                "김동길",
                "아프리카|케냐"
            )
        )
    }
}