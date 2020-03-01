package com.bayanaka.ligabalbalan.modul.klasemen

import com.bayanaka.ligabalbalan.model.Ranking

interface KlasemenContract {
    interface View{
        fun init()
        fun showProgressBar()
        fun hideProgressBar()
        fun setRecyclerView(listOfKlasemen : List<Ranking>)
    }

    interface Presenter{
        fun getData(idLeague : String)
    }
}