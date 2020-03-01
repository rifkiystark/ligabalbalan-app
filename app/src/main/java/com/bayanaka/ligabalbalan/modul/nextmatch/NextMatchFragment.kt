package com.bayanaka.ligabalbalan.modul.nextmatch


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.bayanaka.ligabalbalan.R
import com.bayanaka.ligabalbalan.adapter.MatchAdapter
import com.bayanaka.ligabalbalan.invisible
import com.bayanaka.ligabalbalan.model.Match
import com.bayanaka.ligabalbalan.repository.ApiRepository
import com.bayanaka.ligabalbalan.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_next_match.*

/**
 * A simple [Fragment] subclass.
 */
class NextMatchFragment(val idLeague : String) : Fragment(), NextMatchContract.View {

    lateinit var nextMatchPresenter: NextMatchPresenter
    lateinit var recycleNextMatch : RecyclerView

    override fun showLoading() {
        loadingNextMatch.visible()
    }

    override fun hideLoading() {
        loadingNextMatch.invisible()
    }

    override fun requestData() {
        nextMatchPresenter.getDataMatch(idLeague)
    }

    override fun setAdapter(listOfMatch: List<Match>?) {
        recycleNextMatch.layoutManager = LinearLayoutManager(context)
        recycleNextMatch.adapter = MatchAdapter(listOfMatch){

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_next_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gson = Gson()
        val apiRepository = ApiRepository()

        nextMatchPresenter = NextMatchPresenter(this, apiRepository, gson)

        recycleNextMatch = view.findViewById(R.id.rvNextMatchh)

        requestData()
    }
}
