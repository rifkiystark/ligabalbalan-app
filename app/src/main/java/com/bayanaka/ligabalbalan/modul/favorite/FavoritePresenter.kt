package com.bayanaka.ligabalbalan.modul.favorite

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import com.bayanaka.ligabalbalan.database.database
import com.bayanaka.ligabalbalan.model.Match
import com.bayanaka.ligabalbalan.model.Team
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoritePresenter (
    private val ctx : Context,
    val view : FavoriteContract.View,
    private val type : String
) : FavoriteContract.Presenter {
    override fun getFavorite() {

        try{
            if(type == "MATCH"){
                ctx.database.use{
                    val result = select(Match.TABLE_MATCH)
                    val favorite = result.parseList(classParser<Match>())

                    view.setRecyclerViewFavoriteMatch(favorite)
                }
            } else {
                ctx.database.use{
                    val result = select(Team.TABLE_TEAM)
                    val favorite = result.parseList(classParser<Team>())

                    view.setRecyclerViewFavoriteTeam(favorite)
                }
            }

        }catch (e : SQLiteConstraintException){

        }
    }

}