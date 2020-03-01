package com.bayanaka.ligabalbalan.modul.home

import android.content.Context
import com.bayanaka.ligabalbalan.CoroutineContextProvider
import com.bayanaka.ligabalbalan.R
import com.bayanaka.ligabalbalan.model.Leagues
import com.bayanaka.ligabalbalan.model.MatchSearchResponse
import com.bayanaka.ligabalbalan.repository.ApiRepository
import com.bayanaka.ligabalbalan.repository.TheSportDBApi
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomePresenter(
    private val view: HomeContract.View,
    private val ctx: Context,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val conteks : CoroutineContextProvider = CoroutineContextProvider()
) : HomeContract.Presenter {
    override fun getDataMatch(query: String) {
        view.showLoading()

        GlobalScope.launch(conteks.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(
                    TheSportDBApi.getSearchMatch(query)
                ).await(),
                MatchSearchResponse::class.java
            )

            data.event = data.event.filter {
                it.sport == "Soccer"
            }

            view.setDataMatch(data.event)
            view.hideLoading()

        }
    }

    override fun getDataLeagues() {
        val image = ctx.resources.obtainTypedArray(R.array.league_image)
        val name = ctx.resources.getStringArray(R.array.league_name)
        val description = ctx.resources.getStringArray(R.array.league_description)
        val id = ctx.resources.getStringArray(R.array.league_id)

        val listOfLeague = mutableListOf<Leagues>()

        listOfLeague.clear()
        for (i in name.indices) {
            listOfLeague.add(
                Leagues(
                    id = id[i],
                    imagesSrc = image.getResourceId(i, 0),
                    leagueName = name[i],
                    description = description[i]
                )
            )
        }
        image.recycle()

        view.setLeaguesAdapter(listOfLeague)

    }

}