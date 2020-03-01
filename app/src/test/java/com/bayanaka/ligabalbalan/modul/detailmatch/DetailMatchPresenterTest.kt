package com.bayanaka.ligabalbalan.modul.detailmatch

import android.content.Context
import com.bayanaka.ligabalbalan.TestContextProvider
import com.bayanaka.ligabalbalan.model.*
import com.bayanaka.ligabalbalan.modul.home.HomeContract
import com.bayanaka.ligabalbalan.modul.home.HomePresenter
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

class DetailMatchPresenterTest {
    @Mock
    private lateinit var view: DetailMatchContract.View

    @Mock
    private lateinit var ctx: Context

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: DetailMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailMatchPresenter(view,  apiRepository, gson,ctx, TestContextProvider())
    }

    @Test
    fun getDetailMatch() {
        val idEvent = "12321"
        val match : MutableList<DetailMatch> = mutableListOf(
            DetailMatch()
        )
        val detailMatchResponse = DetailMatchResponse(match)

        val teams : MutableList<Team> = mutableListOf(
            Team()
        )
        val teamResponse = TeamResponse(teams)

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(gson.fromJson("", DetailMatchResponse::class.java)).thenReturn(detailMatchResponse)

            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(gson.fromJson("", TeamResponse::class.java)).thenReturn(teamResponse)

            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(gson.fromJson("", TeamResponse::class.java)).thenReturn(teamResponse)


            presenter.getDetailMatch(idEvent)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).setData(match[0])
            Mockito.verify(view).hideLoading()
        }
    }
}