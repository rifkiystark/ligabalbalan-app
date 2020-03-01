package com.bayanaka.ligabalbalan.modul.teamlist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bayanaka.ligabalbalan.R
import com.bayanaka.ligabalbalan.adapter.TeamsAdapter
import com.bayanaka.ligabalbalan.invisible
import com.bayanaka.ligabalbalan.model.Team
import com.bayanaka.ligabalbalan.modul.detailteam.DetailTeamActivity
import com.bayanaka.ligabalbalan.repository.ApiRepository
import com.bayanaka.ligabalbalan.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_teams.*

class TeamsFragment(
    private val idLeague: String
) : Fragment(), TeamsContract.View {
    override fun setRecyclerView(listOfTeams: List<Team>?) {
        rvTeams.layoutManager = LinearLayoutManager(context)
        rvTeams.adapter = TeamsAdapter(listOfTeams){
            val intent = Intent(context, DetailTeamActivity::class.java)
            intent.putExtra("extra_team", it)
            startActivity(intent)
        }
    }

    override fun showLoading() {
        loadingTeams.visible()
    }

    override fun hideLoading() {
        loadingTeams.invisible()
    }


    override fun init() {
        val presenter = TeamsPresenter(this, ApiRepository(), Gson())
        presenter.getData(idLeague)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teams, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
}
