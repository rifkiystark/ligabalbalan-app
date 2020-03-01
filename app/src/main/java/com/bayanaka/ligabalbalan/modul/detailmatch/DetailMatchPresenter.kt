package com.bayanaka.ligabalbalan.modul.detailmatch

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import com.bayanaka.ligabalbalan.CoroutineContextProvider
import com.bayanaka.ligabalbalan.database.database
import com.bayanaka.ligabalbalan.model.DetailMatch
import com.bayanaka.ligabalbalan.model.DetailMatchResponse
import com.bayanaka.ligabalbalan.model.Match
import com.bayanaka.ligabalbalan.model.TeamResponse
import com.bayanaka.ligabalbalan.repository.ApiRepository
import com.bayanaka.ligabalbalan.repository.TheSportDBApi
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailMatchPresenter(
    val view: DetailMatchContract.View,
    private val apiRepository: ApiRepository,
    val gson: Gson,
    private val ctx: Context,
    private val context : CoroutineContextProvider = CoroutineContextProvider()
) : DetailMatchContract.Presenter {
    override fun deleteMatchFavorite(idEvent: String) {
        try {
            ctx.database.use {
                delete(Match.TABLE_MATCH, "(ID_MATCH = {id})", "id" to idEvent)
            }

            view.isFavorite(false)
            view.showToast("Match has been removed from favorite.")
        } catch (e: SQLiteConstraintException) {

        }
    }

    override fun isFavorite(idEvent: String) {
        try {
            ctx.database.use {
                val result = select(Match.TABLE_MATCH)
                    .whereArgs("(ID_MATCH = {id})", "id" to idEvent)
                val favorite = result.parseList(classParser<Match>())
                if (favorite.isEmpty())
                    view.isFavorite(false)
                else
                    view.isFavorite(true)
            }
        } catch (e: SQLiteConstraintException) {

        }

    }

    override fun saveMatchFavorite(match: DetailMatch) {
        try {
            ctx.database.use {
                insert(
                    Match.TABLE_MATCH,
                    Match.ID_MATCH to match.idEvent,
                    Match.ID_HOME_TEAM to match.idHomeTeam,
                    Match.ID_AWAY_TEAM to match.idAwayTeam,
                    Match.HOME_TEAM to match.homeTeam,
                    Match.AWAY_TEAM to match.awayTeam,
                    Match.HOME_SCORE to match.homeScore,
                    Match.AWAY_SCORE to match.awayScore,
                    Match.DATE_EVENT to match.date,
                    Match.TIME_EVENT to match.time,
                    Match.SPORT to "Soccer"
                )
            }

            view.isFavorite(true)
            view.showToast("Match added to favorite.")

        } catch (e: SQLiteConstraintException) {

        }

//        ctx.database.use {
//            val result = select(Match.TABLE_MATCH)
//            val favorite = result.parseList(classParser<Match>())
//            Toast.makeText(ctx, favorite.toString(), Toast.LENGTH_SHORT).show()
//
//        }


    }

    override fun getDetailMatch(idEvent: String) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getDetailMatch(idEvent)).await(),
                DetailMatchResponse::class.java
            )

            val dataAwayTeam =
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getTeam(data.events[0].idAwayTeam)).await(),
                    TeamResponse::class.java
                )

            val dataHomeTeam =
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getTeam(data.events[0].idHomeTeam)).await(),
                    TeamResponse::class.java
                )

            data.events[0].logoAwayTeam = dataAwayTeam.teams!![0].strTeamLogo
            data.events[0].logoHomeTeam = dataHomeTeam.teams!![0].strTeamLogo


            view.setData(data.events[0])
            view.hideLoading()

        }
    }

}