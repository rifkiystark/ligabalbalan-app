package com.bayanaka.ligabalbalan.modul.previousmatch

import com.bayanaka.ligabalbalan.model.Match

interface PreviousMatchContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun requestData()
        fun setAdapter(listOfMatch : List<Match>? = mutableListOf())
    }

    interface Presenter {
        fun getDataMatch(idLeague : String)
    }
}