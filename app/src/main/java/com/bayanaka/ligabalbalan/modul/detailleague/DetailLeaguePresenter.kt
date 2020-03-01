package com.bayanaka.ligabalbalan.modul.detailleague

import com.bayanaka.ligabalbalan.CoroutineContextProvider
import com.bayanaka.ligabalbalan.model.LeagueResponse
import com.bayanaka.ligabalbalan.repository.ApiRepository
import com.bayanaka.ligabalbalan.repository.TheSportDBApi
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class DetailLeaguePresenter(
    private val view: DetailLeagueContract.View,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context : CoroutineContextProvider = CoroutineContextProvider()
) : DetailLeagueContract.Presenter {
    override fun getDetailMatch(idLeague: String) {

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getDetailLeague(idLeague)).await(),
                LeagueResponse::class.java
            )

            view.setData2View(data.leagues[0])

        }
    }

}
