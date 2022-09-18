package com.coram.spy237.model.db

import android.os.Parcelable
import com.coram.spy237.util.DateFormatUtil
import kotlinx.parcelize.Parcelize

@Parcelize
data class AlarmModel(
    val id: Int = 0,
    var time: String = "",
    var content: String = "",
    var headerContent: String = "",
    var isSucceed: String = "",

    var isSoundAlarm: String = "",
    var isVibAlarm: String = "",
    var isPushAlarm: String = "",
) : Parcelable {
    companion object {
        fun getTestList(): ArrayList<AlarmModel> {
            val result: ArrayList<AlarmModel> = arrayListOf()

            for (i in 0 until 5) {
                result.add(
                    AlarmModel(
                        i,
                        "2022-01-01 01:01",
                        "blaa blaa" + i.toString(),
                        "Philippines | Joshua 2:1-3",
                        "false",
                        "false",
                        "false",
                        "false"
                    )
                )
            }
            return result
        }
    }

}