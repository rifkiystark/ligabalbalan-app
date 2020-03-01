package com.bayanaka.ligabalbalan.modul.teamlist

import com.bayanaka.ligabalbalan.CoroutineContextProvider
import com.bayanaka.ligabalbalan.model.TeamResponse
import com.bayanaka.ligabalbalan.repository.ApiRepository
import com.bayanaka.ligabalbalan.repository.TheSportDBApi
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamsPresenter(
    private val view : TeamsContract.View,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) : TeamsContract.Presenter {
    override fun getData(idLeague: String) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getTeams(idLeague)).await(),
                TeamResponse::class.java
            )

            view.hideLoading()
            view.setRecyclerView(data.teams)

        }
    }
}