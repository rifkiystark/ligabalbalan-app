package com.bayanaka.ligabalbalan.modul.detailteam

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import com.bayanaka.ligabalbalan.database.database
import com.bayanaka.ligabalbalan.model.Team
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailTeamPresenter(
    val view: DetailTeamContract.View,
    private val ctx: Context
) : DetailTeamContract.Presenter {
    override fun saveTeamFavorite(team: Team) {
        try {
            ctx.database.use {
                insert(
                    Team.TABLE_TEAM,
                    Team.ID_TEAM to team.idTeam,
                    Team.TEAM to team.strTeam,
                    Team.TEAM_LOGO to team.strTeamLogo,
                    Team.DESCRIPTION to team.description,
                    Team.SPORT to team.strSport,
                    Team.COUNTRY to team.strCountry,
                    Team.FORMED_YEAR to team.intFormedYear,
                    Team.STADIUM to team.strStadium
                )
            }

            view.isFavorite(true)
            view.showToast("Team added to favorite.")

        } catch (e: SQLiteConstraintException) {

        }
    }

    override fun deleteTeamFavorite(idTeam: String) {
        try {
            ctx.database.use {
                delete(Team.TABLE_TEAM, "(ID_TEAM = {id})", "id" to idTeam)
            }

            view.isFavorite(false)
            view.showToast("Team has been removed from favorite.")
        } catch (e: SQLiteConstraintException) {

        }
    }

    override fun getFavorite(idTeam: String) {
        try {
            ctx.database.use {
                val result = select(Team.TABLE_TEAM)
                    .whereArgs("(ID_TEAM = {id})", "id" to idTeam)
                val favorite = result.parseList(classParser<Team>())
                if (favorite.isEmpty())
                    view.isFavorite(false)
                else
                    view.isFavorite(true)
            }
        } catch (e: SQLiteConstraintException) {

        }
    }

}