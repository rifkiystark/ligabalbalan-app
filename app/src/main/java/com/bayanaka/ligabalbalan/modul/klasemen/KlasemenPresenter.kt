package com.bayanaka.ligabalbalan.modul.klasemen

import com.bayanaka.ligabalbalan.CoroutineContextProvider
import com.bayanaka.ligabalbalan.model.MatchResponse
import com.bayanaka.ligabalbalan.model.RankingsResponse
import com.bayanaka.ligabalbalan.repository.ApiRepository
import com.bayanaka.ligabalbalan.repository.TheSportDBApi
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class KlasemenPresenter(
    val view: KlasemenContract.View,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) : KlasemenContract.Presenter {
    override fun getData(idLeague: String) {
        view.showProgressBar()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getRanking(idLeague)).await(),
                RankingsResponse::class.java
            )
            view.hideProgressBar()
            view.setRecyclerView(data.table)
        }
    }

}