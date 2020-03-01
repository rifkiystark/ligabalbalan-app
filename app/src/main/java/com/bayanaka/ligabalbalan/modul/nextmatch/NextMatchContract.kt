package com.bayanaka.ligabalbalan.modul.nextmatch

import com.bayanaka.ligabalbalan.model.Match

interface NextMatchContract {
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