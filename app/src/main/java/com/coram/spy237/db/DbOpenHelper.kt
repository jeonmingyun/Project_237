package com.coram.spy237.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.coram.spy237.model.db.AlarmModel
import com.coram.spy237.model.db.CountryModel
import com.coram.spy237.model.db.HighlightModel

class DbOpenHelper(context: Context?) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private const val DB_VERSION = 1
        private const val DB_NAME = "237.db"
        lateinit var mdb: SQLiteDatabase
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(DbTable.Member.QUERY_CREATE)
        db.execSQL(DbTable.Country.QUERY_CREATE)
        db.execSQL(DbTable.Alarm.QUERY_CREATE)
        db.execSQL(DbTable.Highlight.QUERY_CREATE)
        db.execSQL(DbTable.DailyMission.QUERY_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DbTable.Member.QUERY_DROP)
        db.execSQL(DbTable.Country.QUERY_DROP)
        db.execSQL(DbTable.Alarm.QUERY_DROP)
        db.execSQL(DbTable.Highlight.QUERY_DROP)
        db.execSQL(DbTable.DailyMission.QUERY_DROP)
    }

    /**
     * SQL
     */
    // START Member
    fun selectAllMember(): Cursor {
        mdb = this.readableDatabase
        val sql = "select * from member"
        return mdb.rawQuery(sql, null)
    }

    fun insertMember(member_id: String?, member_nickname: String?, member_birth: String?): Boolean {
        mdb = this.writableDatabase
        val values = ContentValues()
        values.put(DbTable.Member.COLUMN_ID, member_id)
        values.put(DbTable.Member.COLUMN_NICKNAME, member_nickname)
        values.put(DbTable.Member.COLUMN_BIRTH, member_birth)
        val result = mdb.insert(DbTable.Member.TABLENAME, null, values)

        return result != -1L // success
    }

    fun updateMember(member_id: String, member_nickname: String?, member_birth: String?): Boolean {
        mdb = this.writableDatabase
        val values = ContentValues()
        values.put(DbTable.Member.COLUMN_ID, member_id)
        values.put(DbTable.Member.COLUMN_NICKNAME, member_nickname)
        values.put(DbTable.Member.COLUMN_BIRTH, member_birth)
        val result = mdb.update(
            DbTable.Member.TABLENAME,
            values,
            DbTable.Member.COLUMN_ID.toString() + "=?",
            arrayOf(member_id)
        )
        return result != 0 // success
    }

    fun deleteMember(member_id: String): Boolean {
        mdb = this.writableDatabase
        val result = mdb.delete(
            DbTable.Member.TABLENAME,
            DbTable.Member.COLUMN_ID.toString() + "=?",
            arrayOf(member_id)
        )
        return result != 0 // success
    }
    // END Member

    // START Country
    fun selectAllCountry(): Cursor {
        mdb = this.readableDatabase
        val sql = "select * from ${DbTable.Country.TABLENAME}"
        return mdb.rawQuery(sql, null)
    }

    fun selectAllContinent(continent: String): Cursor {
        mdb = this.readableDatabase
        val sql = "select * from ${DbTable.Country.TABLENAME} where ${DbTable.Country.COLUMN_CONTINENT} like '%$continent%'"
        return mdb.rawQuery(sql, null)
    }

    fun selectCountries(countryName: String): Cursor {
        mdb = this.readableDatabase
        val sql =
            "select * from ${DbTable.Country.TABLENAME} where ${DbTable.Country.COLUMN_NAME} like '%$countryName%'"
        return mdb.rawQuery(sql, null)
    }

    fun insertCountry(model: CountryModel): Boolean {
        mdb = this.writableDatabase
        val values = ContentValues()
//        values.put(DbTable.Country.COLUMN_ID, model.id)
        values.put(DbTable.Country.COLUMN_NAME, model.name)
        values.put(DbTable.Country.COLUMN_CONTINENT, model.continent)
        values.put(DbTable.Country.COLUMN_PEOPLE_COUNT, model.peopleCount)
        values.put(DbTable.Country.COLUMN_PRAY_COUNT, model.prayCount)
        values.put(DbTable.Country.COLUMN_FLAG_URL, model.flagUrl)
        val result = mdb.insert(DbTable.Country.TABLENAME, null, values)

        return result != -1L // success
    }
    // END Country

    // START Alarm
    fun selectAllAlarm(): Cursor {
        mdb = this.readableDatabase
        val sql = "select * from ${DbTable.Alarm.TABLENAME} order by ${DbTable.Alarm.COLUMN_TIME} asc"
        return mdb.rawQuery(sql, null)
    }

    fun insertAlarm(model: AlarmModel): Boolean {
        mdb = this.writableDatabase
        val values = ContentValues()
        values.put(DbTable.Alarm.COLUMN_TIME, model.time)
        values.put(DbTable.Alarm.COLUMN_CONTENT, model.content)
        values.put(DbTable.Alarm.COLUMN_HEADER_CONTENT, model.headerContent)
        values.put(DbTable.Alarm.COLUMN_IS_SUCCEED, model.isSucceed)
        values.put(DbTable.Alarm.COLUMN_IS_SOUND_ALARM, model.isSoundAlarm)
        values.put(DbTable.Alarm.COLUMN_IS_VIB_ALARM, model.isVibAlarm)
        values.put(DbTable.Alarm.COLUMN_IS_PUSH_ALARM, model.isPushAlarm)
        val result = mdb.insert(DbTable.Alarm.TABLENAME, null, values)

        return result != -1L // success
    }

    fun updateAlarm(model: AlarmModel): Boolean {
        mdb = this.writableDatabase
        val values = ContentValues()
        values.put(DbTable.Alarm.COLUMN_TIME, model.time)
        values.put(DbTable.Alarm.COLUMN_CONTENT, model.content)
        values.put(DbTable.Alarm.COLUMN_HEADER_CONTENT, model.headerContent)
        values.put(DbTable.Alarm.COLUMN_IS_SUCCEED, model.isSucceed)
        values.put(DbTable.Alarm.COLUMN_IS_SOUND_ALARM, model.isSoundAlarm)
        values.put(DbTable.Alarm.COLUMN_IS_VIB_ALARM, model.isVibAlarm)
        values.put(DbTable.Alarm.COLUMN_IS_PUSH_ALARM, model.isPushAlarm)
        val result = mdb.update(
            DbTable.Alarm.TABLENAME,
            values,
            DbTable.Alarm.COLUMN_ID.toString() + "=?",
            arrayOf(model.id.toString())
        )
        return result != 0 // success
    }

    fun deleteAlarm(id: Int): Boolean {
        mdb = this.writableDatabase
        val result = mdb.delete(
            DbTable.Alarm.TABLENAME,
            DbTable.Alarm.COLUMN_ID.toString() + "=?",
            arrayOf(id.toString())
        )
        return result != 0 // success
    }
    // END Alarm

    // START Highlight
    fun selectAllHighlight(): Cursor {
        mdb = this.readableDatabase
        val sql = "select * from ${DbTable.Highlight.TABLENAME} order by ${DbTable.Highlight.COLUMN_ID} asc"
        return mdb.rawQuery(sql, null)
    }

    fun insertHighlight(model: HighlightModel): Boolean {
        mdb = this.writableDatabase
        val values = ContentValues()
        values.put(DbTable.Highlight.COLUMN_POSITION, model.position)
        values.put(DbTable.Highlight.COLUMN_TEXT_ID, model.textId)
        values.put(DbTable.Highlight.COLUMN_START, model.start)
        values.put(DbTable.Highlight.COLUMN_END, model.end)
        values.put(DbTable.Highlight.COLUMN_COLOR, model.color)
        val result = mdb.insert(DbTable.Highlight.TABLENAME, null, values)

        return result != -1L // success
    }
    //END Highlight

    // START Daily Mission
    /**
     * @param date yyyy-MM
     */
    fun selectDailyMissionOfMonth(date: String): Cursor {
        mdb = this.readableDatabase
        val sql = "select * from ${DbTable.DailyMission.TABLENAME} where ${DbTable.DailyMission.COLUMN_DATE} like '%$date%'"
        return mdb.rawQuery(sql, null)
    }

    /**
     * @param date yyyy-MM-dd
     */
    fun insertDailyMission(date: String): Boolean {
        mdb = this.writableDatabase
        val values = ContentValues()
        values.put(DbTable.DailyMission.COLUMN_DATE, date)
        val result = mdb.insertWithOnConflict(DbTable.DailyMission.TABLENAME, null, values, SQLiteDatabase.CONFLICT_IGNORE)

        return result != -1L // success
    }
    //END Daily Mission

}