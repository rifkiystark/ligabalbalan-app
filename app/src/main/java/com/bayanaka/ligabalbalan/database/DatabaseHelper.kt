package com.bayanaka.ligabalbalan.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.bayanaka.ligabalbalan.model.Match
import com.bayanaka.ligabalbalan.model.Team
import org.jetbrains.anko.db.*

class DatabaseHelper(ctx : Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteMatch.db", null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(Match.TABLE_MATCH, true,
            Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Match.ID_MATCH to TEXT + UNIQUE,
            Match.ID_HOME_TEAM to TEXT,
            Match.ID_AWAY_TEAM to TEXT,
            Match.HOME_TEAM to TEXT,
            Match.AWAY_TEAM to TEXT,
            Match.HOME_SCORE to TEXT,
            Match.AWAY_SCORE to TEXT,
            Match.DATE_EVENT to TEXT,
            Match.TIME_EVENT to TEXT,
            Match.SPORT to TEXT)

        db?.createTable(Team.TABLE_TEAM, true,
            Team.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Team.ID_TEAM to TEXT + UNIQUE,
            Team.TEAM to TEXT,
            Team.TEAM_LOGO to TEXT,
            Team.DESCRIPTION to TEXT,
            Team.SPORT to TEXT,
            Team.COUNTRY to TEXT,
            Team.FORMED_YEAR to TEXT,
            Team.STADIUM to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(Match.TABLE_MATCH,true)
        db?.dropTable(Team.TABLE_TEAM,true)
    }


    companion object {
        private var instance : DatabaseHelper? = null

        @Synchronized
        fun getIntance(ctx : Context) : DatabaseHelper {
            if (instance == null){
                instance = DatabaseHelper(ctx.applicationContext)
            }

            return instance as DatabaseHelper
        }
    }
}

val Context.database: DatabaseHelper
    get() = DatabaseHelper.getIntance(applicationContext)