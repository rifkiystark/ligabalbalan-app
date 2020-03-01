package com.bayanaka.ligabalbalan.modul.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.bayanaka.ligabalbalan.R
import com.bayanaka.ligabalbalan.adapter.MatchAdapter
import com.bayanaka.ligabalbalan.adapter.TeamsAdapter
import com.bayanaka.ligabalbalan.model.Match
import com.bayanaka.ligabalbalan.model.Team
import com.bayanaka.ligabalbalan.modul.detailmatch.DetailMatchActivity
import com.bayanaka.ligabalbalan.modul.detailteam.DetailTeamActivity
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity(), FavoriteContract.View {


    lateinit var favoritePresenter: FavoritePresenter
    lateinit var type : String
    override fun setRecyclerViewFavoriteMatch(matches: List<Match>) {
        rvFavoriteMatch.layoutManager = LinearLayoutManager(this)
        rvFavoriteMatch.adapter = MatchAdapter(matches){
            val intent = Intent(this, DetailMatchActivity::class.java)
            intent.putExtra("idEvent", it)
            startActivity(intent)
        }
    }

    override fun setRecyclerViewFavoriteTeam(teams: List<Team>) {
        rvFavoriteMatch.layoutManager = LinearLayoutManager(this)
        rvFavoriteMatch.adapter = TeamsAdapter(teams){
            val intent = Intent(this, DetailTeamActivity::class.java)
            intent.putExtra("extra_team", it)
            Log.d("hehe", it.toString())
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        type = intent.getStringExtra("TYPE")
        Log.d("hehe", type)
        favoritePresenter = FavoritePresenter(this, this, type)
        favoritePresenter.getFavorite()
    }

    override fun onResume() {
        super.onResume()
        favoritePresenter.getFavorite()
    }
}
