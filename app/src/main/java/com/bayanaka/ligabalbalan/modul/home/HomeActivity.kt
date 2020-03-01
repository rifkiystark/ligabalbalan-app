package com.bayanaka.ligabalbalan.modul.home

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bayanaka.ligabalbalan.R
import com.bayanaka.ligabalbalan.adapter.FootbalClubAdapter
import com.bayanaka.ligabalbalan.adapter.MatchAdapter
import com.bayanaka.ligabalbalan.invisible
import com.bayanaka.ligabalbalan.model.Leagues
import com.bayanaka.ligabalbalan.model.Match
import com.bayanaka.ligabalbalan.modul.detailleague.DetailLeagueActivity
import com.bayanaka.ligabalbalan.modul.detailmatch.DetailMatchActivity
import com.bayanaka.ligabalbalan.repository.ApiRepository
import com.bayanaka.ligabalbalan.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class HomeActivity : AppCompatActivity(), HomeContract.View {
    override fun showLoading() {
        loadingHome.visible()
    }

    override fun hideLoading() {
        loadingHome.invisible()
    }

    override fun requestDataMatch(query: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setDataMatch(listOfMatch: List<Match>) {
        rvHomeMatch.layoutManager = LinearLayoutManager(this)
        rvHomeMatch.adapter = MatchAdapter(listOfMatch){
            val intent = Intent(this, DetailMatchActivity::class.java)
            intent.putExtra("idEvent", it)
            startActivity(intent)
        }
    }

    lateinit var homePresenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        homePresenter = HomePresenter(this, this, ApiRepository(), Gson())
        requestLeagueDatas()
    }

    override fun setLeaguesAdapter(listOfLeagues: List<Leagues>) {
        rvHomeLeagues.layoutManager = GridLayoutManager(this, 2)
        rvHomeLeagues.adapter = FootbalClubAdapter(this, listOfLeagues) {
            startActivity<DetailLeagueActivity>(
                "id" to it.id,
                "img" to it.imagesSrc,
                "name" to it.leagueName,
                "description" to it.description
            )
        }
    }

    override fun requestLeagueDatas() {
        homePresenter.getDataLeagues()
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
                homePresenter.getDataMatch(query)
                rvHomeLeagues.invisible()
                rvHomeMatch.visible()

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

    override fun onBackPressed() {
        if (rvHomeMatch.visibility == View.VISIBLE){
            rvHomeMatch.invisible()
            rvHomeLeagues.visible()
        } else {
            super.onBackPressed()
        }
    }
}

