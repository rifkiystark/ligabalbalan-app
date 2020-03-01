package com.bayanaka.ligabalbalan.modul.detailmatch

import com.bayanaka.ligabalbalan.model.DetailMatch

interface DetailMatchContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun requestData(idEvent: String)
        fun setData(detailMatch: DetailMatch)
        fun isFavorite(isFavorite: Boolean)
        fun showToast(message : String)
    }

    interface Presenter {
        fun getDetailMatch(idEvent: String)
        fun saveMatchFavorite(match: DetailMatch)
        fun isFavorite(idEvent: String)
        fun deleteMatchFavorite(idEvent : String)
    }
}