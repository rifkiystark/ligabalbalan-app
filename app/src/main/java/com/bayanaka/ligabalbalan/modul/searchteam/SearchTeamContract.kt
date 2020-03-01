package com.bayanaka.ligabalbalan.modul.searchteam

import com.bayanaka.ligabalbalan.model.Team

interface SearchTeamContract {
    interface View {
        fun init()

        fun showLoading()
        fun hideLoading()
        fun initAdapter()
        fun updateDataSet(listOfTeam : List<Team>? = listOf())
    }

    interface Presenter {
        fun getData(query : String)
    }
}