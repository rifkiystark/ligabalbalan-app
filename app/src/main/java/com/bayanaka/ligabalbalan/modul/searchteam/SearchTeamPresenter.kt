package com.bayanaka.ligabalbalan.modul.searchteam

import com.bayanaka.ligabalbalan.CoroutineContextProvider
import com.bayanaka.ligabalbalan.model.TeamResponse
import com.bayanaka.ligabalbalan.repository.ApiRepository
import com.bayanaka.ligabalbalan.repository.TheSportDBApi
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchTeamPresenter(
    val view: SearchTeamContract.View,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) : SearchTeamContract.Presenter {
    override fun getData(query: String) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.findTeams(query)).await(),
                TeamResponse::class.java
            )

            view.hideLoading()
            view.updateDataSet(data.teams?.filter { it.strSport == "Soccer" })

        }
    }
}