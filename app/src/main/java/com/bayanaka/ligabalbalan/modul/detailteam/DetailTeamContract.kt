package com.bayanaka.ligabalbalan.modul.detailteam

import com.bayanaka.ligabalbalan.model.Team

interface DetailTeamContract {
    interface View {
        fun init()
        fun isFavorite(isFavorite: Boolean)
        fun showToast(msg : String)
        fun onClick()
    }

    interface Presenter {
        fun saveTeamFavorite(team : Team)
        fun deleteTeamFavorite(idTeam : String)
        fun getFavorite(idTeam : String)
    }
}