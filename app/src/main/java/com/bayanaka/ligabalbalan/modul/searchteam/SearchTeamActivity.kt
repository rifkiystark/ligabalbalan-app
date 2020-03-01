package com.bayanaka.ligabalbalan.modul.searchteam

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bayanaka.ligabalbalan.R
import com.bayanaka.ligabalbalan.adapter.TeamsAdapter
import com.bayanaka.ligabalbalan.invisible
import com.bayanaka.ligabalbalan.model.Team
import com.bayanaka.ligabalbalan.modul.detailteam.DetailTeamActivity
import com.bayanaka.ligabalbalan.repository.ApiRepository
import com.bayanaka.ligabalbalan.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search_team.*

class SearchTeamActivity : AppCompatActivity(), SearchTeamContract.View {
    private lateinit var teamsAdapter: TeamsAdapter
    private lateinit var searchTeamPresenter: SearchTeamPresenter
    override fun showLoading() {
        rvSearchTeam.invisible()
        tvPencarian.visible()
    }

    override fun hideLoading() {
        rvSearchTeam.visible()
        tvPencarian.invisible()
    }

    override fun initAdapter() {
        teamsAdapter = TeamsAdapter {
            val intent = Intent(this, DetailTeamActivity::class.java)
            intent.putExtra("extra_team", it)
            startActivity(intent)
        }
        rvSearchTeam.layoutManager = LinearLayoutManager(this)
        rvSearchTeam.adapter = teamsAdapter
    }

    override fun updateDataSet(listOfTeam: List<Team>?) {
        listOfTeam?.let { teamsAdapter.updateDataSet(it) }
    }

    override fun init() {
        setSupportActionBar(toolbar)
        searchTeamPresenter = SearchTeamPresenter(this, ApiRepository(), Gson())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_team)

        init()
        initAdapter()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu!!.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            /*
            Gunakan method ini ketika search selesai atau OK
             */
            override fun onQueryTextSubmit(query: String): Boolean {
                searchTeamPresenter.getData(query)
                return true
            }

            /*
            Gunakan method ini untuk merespon tiap perubahan huruf pada searchView
             */
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return true
    }
}
