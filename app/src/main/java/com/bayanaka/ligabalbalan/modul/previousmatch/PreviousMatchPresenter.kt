package com.bayanaka.ligabalbalan.modul.previousmatch

import android.util.Log
import com.bayanaka.ligabalbalan.CoroutineContextProvider
import com.bayanaka.ligabalbalan.model.MatchResponse
import com.bayanaka.ligabalbalan.repository.ApiRepository
import com.bayanaka.ligabalbalan.repository.TheSportDBApi
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PreviousMatchPresenter(
    val view: PreviousMatchContract.View,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) : PreviousMatchContract.Presenter {
    override fun getDataMatch(idLeague: String) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getPreviousMatch(idLeague)).await(),
                MatchResponse::class.java
            )

            view.hideLoading()
            view.setAdapter(data.events)

        }
    }

}