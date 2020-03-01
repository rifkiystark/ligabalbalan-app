package com.bayanaka.ligabalbalan.modul.favorite

import com.bayanaka.ligabalbalan.model.Match
import com.bayanaka.ligabalbalan.model.Team

interface FavoriteContract {
    interface View {
        fun setRecyclerViewFavoriteMatch(matches : List<Match>)
        fun setRecyclerViewFavoriteTeam(teams : List<Team>)
    }

    interface Presenter{
        fun getFavorite()
    }
}