package com.bayanaka.ligabalbalan.modul.detailleague

import com.bayanaka.ligabalbalan.model.League

interface DetailLeagueContract {
    interface View {
        fun setData2View(league : League)
    }
    interface Presenter{
        fun getDetailMatch(idLeague : String)
    }
}