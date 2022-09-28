package com.coram.spy237.model

data class CalendarItemModel(
    var dateOfMonth: String? = null,
    var dayOfWeek: String? = null, // 요일
    var date: String? = null,
    var isChecked: Boolean = false
) {
    fun setChecked(dateOfMonth: String) {
        isChecked = this.dateOfMonth == dateOfMonth
    }
}