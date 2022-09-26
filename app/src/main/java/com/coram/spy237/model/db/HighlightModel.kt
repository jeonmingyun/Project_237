package com.coram.spy237.model.db

import android.os.Parcelable
import com.coram.spy237.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class HighlightModel(
    val id: Int = -1,
    var position: Int = -1,
    var textId: Int = -1,
    var start: Int = -1,
    var end: Int = -1,
    var color: Int = -1
) : Parcelable {
    companion object {
        fun getTestList(): ArrayList<HighlightModel> {
            val result: ArrayList<HighlightModel> = arrayListOf()

            for (i in 0 until 3) {
                result.add(
                    HighlightModel(
                        i,
                        i,
                        2,
                        2,
                        10,
                        R.color.app_yellow,
                    )
                )
            }
            return result
        }
    }

}