package com.coram.spy237.model

data class AlarmModel(
    val time: String,
    val content: String,
    val headerContent: String
) {
    companion object {
        fun getTestList(): ArrayList<AlarmModel> {
            val result: ArrayList<AlarmModel> = arrayListOf()

            for (i in 0 until 5) {
                result.add(
                    AlarmModel(
                        "오전 8:00",
                        "blaa blaa" + i.toString(),
                        "Philippines | Joshua 2:1-3"
                    )
                )
            }
            return result
        }
    }
}