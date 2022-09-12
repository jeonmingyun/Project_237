package com.coram.spy237.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.coram.spy237.model.db.CountryModel

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
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DbTable.Member.QUERY_DROP)
        db.execSQL(DbTable.Country.QUERY_DROP)
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

    fun selectCountries(countryName: String): Cursor {
        mdb = this.readableDatabase
        val sql = "select * from ${DbTable.Country.TABLENAME} where ${DbTable.Country.COLUMN_NAME} like '%$countryName%'"
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

}