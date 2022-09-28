package com.coram.spy237.util

import android.text.format.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateFormatUtil {
    private const val TAG = "DateUtils"
    private val mCalendar: Calendar? = null
    const val DATE_FORMAT_1 = "yyyy-MM-dd HH:mm" // 2020-04-02 10:37
    const val DATE_FORMAT_2 = "aa hh:mm" //오전 10:37
    const val DATE_FORMAT_3 = "yyyy-MM-dd" // 2020-04-02
    const val DATE_FORMAT_4 = "HH:mm" // 2020-04-02
    const val DATE_FORMAT_5 = "yyyy-MM" // 2020-04
    const val DATE_FORMAT_6 = "dd" // 02

    fun getCurrentDate(formatType: String?): String {
        val dateFormat = SimpleDateFormat(formatType, Locale.getDefault())
        val today: Date = Calendar.getInstance().getTime()
        return dateFormat.format(today)
    }

    /**
     * @param time        in milliseconds (Timestamp)
     * @param mDateFormat SimpleDateFormat
     * @return
     */
    fun getDateTimeFromTimeStamp(time: Long, mDateFormat: String?): String {
        val dateFormat = SimpleDateFormat(mDateFormat, Locale.getDefault())
        val dateTime = Date(time)
        return dateFormat.format(dateTime)
    }

    /**
     * Get Timestamp from date and time
     *
     * @param mDateTime   datetime String
     * @param mDateFormat Date Format
     * @return
     * @throws ParseException
     */
    @Throws(ParseException::class)
    fun getTimeStampFromDateTime(mDateTime: String?, mDateFormat: String?): Long {
        val dateFormat = SimpleDateFormat(mDateFormat, Locale.getDefault())
        val date: Date = dateFormat.parse(mDateTime)
        return date.getTime()
    }

    @Throws(ParseException::class)
    fun convertStringToDate(mDateTime: String?, mDateFormat: String?): Date {
        val formatter = SimpleDateFormat(mDateFormat)
        return formatter.parse(mDateTime)
    }

    /**
     * Return datetime String from date object
     *
     * @param mDateFormat format of date
     * @param date        date object that you want to parse
     * @return
     */
    fun convertDateToString(mDateFormat: String?, date: Date?): String? {
        return if (date == null) {
            null
        } else DateFormat.format(mDateFormat, date).toString()
    }

    /**
     * Convert one date format string to another date format string in android
     *
     * @param inputDate        input Date String
     * @param inputDateFormat  Input SimpleDateFormat
     * @param outputDateFormat Output SimpleDateFormat
     * @return
     * @throws ParseException
     */
    @Throws(ParseException::class)
    fun formatChangeDateString(
        inputDate: String?,
        inputDateFormat: String?,
        outputDateFormat: String?
    ): String {
        val mParsedDate: Date
        val mOutputDateString: String
        val mInputDateFormat = SimpleDateFormat(inputDateFormat, Locale.getDefault())
        val mOutputDateFormat = SimpleDateFormat(outputDateFormat, Locale.getDefault())
        mParsedDate = mInputDateFormat.parse(inputDate)
        mOutputDateString = mOutputDateFormat.format(mParsedDate)
        return mOutputDateString
    }
}