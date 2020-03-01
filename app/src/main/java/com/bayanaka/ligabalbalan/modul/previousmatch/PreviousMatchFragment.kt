package com.bayanaka.ligabalbalan.modul.previousmatch


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.bayanaka.ligabalbalan.R
import com.bayanaka.ligabalbalan.adapter.MatchAdapter
import com.bayanaka.ligabalbalan.invisible
import com.bayanaka.ligabalbalan.model.Match
import com.bayanaka.ligabalbalan.modul.detailmatch.DetailMatchActivity
import com.bayanaka.ligabalbalan.repository.ApiRepository
import com.bayanaka.ligabalbalan.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_previous_match.*

/**
 * A simple [Fragment] subclass.
 */
class PreviousMatchFragment(val idLeague : String) : Fragment(), PreviousMatchContract.View {
    lateinit var previousMatchPresenter: PreviousMatchPresenter

    override fun showLoading() {
        loadingPreviousMatch.visible()
    }

    override fun hideLoading() {
        loadingPreviousMatch.invisible()
    }

    override fun requestData() {
        previousMatchPresenter.getDataMatch(idLeague)
    }

    override fun setAdapter(listOfMatch: List<Match>?) {
        rvPrevMatch.layoutManager = LinearLayoutManager(context)
        rvPrevMatch.adapter = MatchAdapter(listOfMatch){
            val intent = Intent(context, DetailMatchActivity::class.java)
            intent.putExtra("idEvent", it)
            startActivity(intent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_previous_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        previousMatchPresenter = PreviousMatchPresenter(this, ApiRepository(), Gson())
        requestData()
    }
}
