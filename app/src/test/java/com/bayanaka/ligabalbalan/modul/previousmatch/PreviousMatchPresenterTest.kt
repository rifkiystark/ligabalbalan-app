package com.bayanaka.ligabalbalan.modul.previousmatch

import android.content.Context
import android.util.Log
import com.bayanaka.ligabalbalan.TestContextProvider
import com.bayanaka.ligabalbalan.model.Match
import com.bayanaka.ligabalbalan.model.MatchResponse
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

class PreviousMatchPresenterTest {
    @Mock
    private lateinit var view: PreviousMatchContract.View

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiResponse: Deferred<String>


    private lateinit var presenter: PreviousMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = PreviousMatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getDataMatch() {
        val idLeague = "12321"
        val matches : MutableList<Match> = mutableListOf()
        val response = MatchResponse(matches)

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(gson.fromJson("", MatchResponse::class.java)).thenReturn(response)

            presenter.getDataMatch(idLeague)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).setAdapter(matches)
            Mockito.verify(view).hideLoading()
        }
    }
}