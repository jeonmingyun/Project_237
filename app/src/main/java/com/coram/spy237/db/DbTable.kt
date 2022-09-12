package com.coram.spy237.db

import android.provider.BaseColumns

class DbTable  // To prevent someone from accidentally instantiating the contract class,
// make the constructor private.
private constructor() {
    /*회원*/
    object Member : BaseColumns {
        const val TABLENAME = "member"
        const val COLUMN_ID = "member_id"
        const val COLUMN_NICKNAME = "member_nickname"
        const val COLUMN_BIRTH = "member_birth"
        const val QUERY_CREATE = ("create table IF NOT EXISTS " + TABLENAME + "("
                + COLUMN_ID + " text primary key,"
                + COLUMN_NICKNAME + " text not null,"
                + COLUMN_BIRTH + " text);")
        const val QUERY_DROP = "drop table if exists " + TABLENAME
    }

    /*나라 정보*/
    object Country : BaseColumns {
        const val TABLENAME = "country"
        const val COLUMN_ID = "_id"
        const val COLUMN_NAME = "COLUMN_NAME"
        const val COLUMN_CONTINENT = "COLUMN_CONTINENT"
        const val COLUMN_PEOPLE_COUNT = "COLUMN_PEOPLE_COUNT"
        const val COLUMN_PRAY_COUNT = "COLUMN_PRAY_COUNT"
        const val COLUMN_FLAG_URL = "COLUMN_FLAG_URL"
        const val QUERY_CREATE = ("create table IF NOT EXISTS " + TABLENAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " text,"
                + COLUMN_CONTINENT + " text,"
                + COLUMN_PEOPLE_COUNT + " text,"
                + COLUMN_PRAY_COUNT + " text,"
                + COLUMN_FLAG_URL + " text"
                + ");")
        const val QUERY_DROP = "drop table if exists " + TABLENAME
    }
}