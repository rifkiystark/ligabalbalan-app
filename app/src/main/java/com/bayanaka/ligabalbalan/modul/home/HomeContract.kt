package com.bayanaka.ligabalbalan.modul.home

import com.bayanaka.ligabalbalan.model.Leagues
import com.bayanaka.ligabalbalan.model.Match

interface HomeContract {
    interface View {
        fun setLeaguesAdapter(listOfLeagues : List<Leagues>)
        fun requestLeagueDatas()
        fun requestDataMatch(query: String)
        fun setDataMatch(listOfMatch : List<Match>)
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter {
        fun getDataLeagues()
        fun getDataMatch(query : String)
    }
}