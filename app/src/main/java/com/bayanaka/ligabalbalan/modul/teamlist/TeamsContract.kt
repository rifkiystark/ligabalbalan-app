package com.bayanaka.ligabalbalan.modul.teamlist

import com.bayanaka.ligabalbalan.model.Team

interface TeamsContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun setRecyclerView(listOfTeams : List<Team>?)
        fun init()
    }

    interface Presenter {
        fun getData(idLeague : String)
    }
}