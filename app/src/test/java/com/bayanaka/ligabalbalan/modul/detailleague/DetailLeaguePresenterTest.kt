package com.bayanaka.ligabalbalan.modul.detailleague

import com.bayanaka.ligabalbalan.TestContextProvider
import com.bayanaka.ligabalbalan.model.League
import com.bayanaka.ligabalbalan.model.LeagueResponse
import com.bayanaka.ligabalbalan.modul.nextmatch.NextMatchContract
import com.bayanaka.ligabalbalan.modul.nextmatch.NextMatchPresenter
import com.bayanaka.ligabalbalan.repository.ApiRepository
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailLeaguePresenterTest {


    @Mock
    private lateinit var view: DetailLeagueContract.View

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: DetailLeaguePresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailLeaguePresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getDetailMatch() {
        val idLeague = "4328"
        val leagues : MutableList<League> = mutableListOf(
            League()
        )
        val response = LeagueResponse(leagues)

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(gson.fromJson("", LeagueResponse::class.java)).thenReturn(response)

            presenter.getDetailMatch(idLeague)
            Mockito.verify(view).setData2View(leagues[0])
        }
    }
}