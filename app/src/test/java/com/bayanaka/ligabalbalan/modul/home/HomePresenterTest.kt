package com.bayanaka.ligabalbalan.modul.home

import android.content.Context
import com.bayanaka.ligabalbalan.TestContextProvider
import com.bayanaka.ligabalbalan.model.Match
import com.bayanaka.ligabalbalan.model.MatchResponse
import com.bayanaka.ligabalbalan.model.MatchSearchResponse
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

class HomePresenterTest {
    @Mock
    private lateinit var view: HomeContract.View

    @Mock
    private lateinit var ctx: Context

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: HomePresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = HomePresenter(view, ctx, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getDataMatch() {
        val matches : MutableList<Match> = mutableListOf()
        val response = MatchSearchResponse(matches)
        val query = "Manchaster"

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(gson.fromJson("", MatchSearchResponse::class.java))
                .thenReturn(response)

            presenter.getDataMatch(query)
            Mockito.verify(view).showLoading()
            Mockito.verify(view).setDataMatch(matches)
            Mockito.verify(view).hideLoading()
        }
    }
}