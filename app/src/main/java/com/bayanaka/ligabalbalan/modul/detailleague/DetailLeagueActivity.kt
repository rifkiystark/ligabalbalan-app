package com.bayanaka.ligabalbalan.modul.detailleague

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.bayanaka.ligabalbalan.R
import com.bayanaka.ligabalbalan.adapter.TabAdapter
import com.bayanaka.ligabalbalan.invisible
import com.bayanaka.ligabalbalan.model.League
import com.bayanaka.ligabalbalan.modul.favorite.FavoriteActivity
import com.bayanaka.ligabalbalan.modul.klasemen.KlasemenFragment
import com.bayanaka.ligabalbalan.modul.nextmatch.NextMatchFragment
import com.bayanaka.ligabalbalan.modul.previousmatch.PreviousMatchFragment
import com.bayanaka.ligabalbalan.modul.searchteam.SearchTeamActivity
import com.bayanaka.ligabalbalan.modul.teamlist.TeamsFragment
import com.bayanaka.ligabalbalan.repository.ApiRepository
import com.bayanaka.ligabalbalan.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail.*


class DetailLeagueActivity : AppCompatActivity(), DetailLeagueContract.View {

    lateinit var detailLeaguePresenter: DetailLeaguePresenter

    lateinit var listOfFragment: MutableList<Fragment>
    lateinit var listOfTabTitle: MutableList<String>
    var tabPosition : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setView()

        setTabView()

        setToolbar()




        detailLeaguePresenter = DetailLeaguePresenter(this, ApiRepository(), Gson())
        detailLeaguePresenter.getDetailMatch(intent.getStringExtra("id"))

        setOnClick()

    }

    private fun setToolbar() {
        setSupportActionBar(toolbar)

        collapsing_toolbar.title = intent.getStringExtra("name")

        collapsing_toolbar.setCollapsedTitleTextColor(
            ContextCompat.getColor(this, android.R.color.white)
        )
        collapsing_toolbar.setExpandedTitleColor(
            ContextCompat.getColor(this, android.R.color.transparent)
        )
    }

    private fun setTabView() {

        listOfFragment = mutableListOf()
        listOfFragment.add(PreviousMatchFragment(intent.getStringExtra("id")))
        listOfFragment.add(NextMatchFragment(intent.getStringExtra("id")))
        listOfFragment.add(KlasemenFragment(intent.getStringExtra("id")))
        listOfFragment.add(TeamsFragment(intent.getStringExtra("id")))

        listOfTabTitle = mutableListOf()
        listOfTabTitle.add("Previous Match")
        listOfTabTitle.add("Next Match")
        listOfTabTitle.add("Klasemen")
        listOfTabTitle.add("Teams")

        vpMatch.adapter = TabAdapter(supportFragmentManager, listOfFragment, listOfTabTitle)
        vpMatch.offscreenPageLimit = 4
        tlMatch.setupWithViewPager(vpMatch)
        vpMatch.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                if(position == 1 || position == 2){
                    tvDetailLeagueFavorite.invisible()
                } else {
                    tvDetailLeagueFavorite.visible()
                    tabPosition = position
                }
            }

        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu2, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.search2 -> {
                startActivity(Intent(this, SearchTeamActivity::class.java))
            }
        }

        return true
    }

    private fun setOnClick() {
        val intent = Intent(this, FavoriteActivity::class.java)

        tvDetailLeagueFavorite.setOnClickListener {
            if (tabPosition == 0){
                intent.putExtra("TYPE", "MATCH")
            } else if (tabPosition == 3) {
                intent.putExtra("TYPE", "TEAM")
            }
            startActivity(intent)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setView() {
        val name = intent.getStringExtra("name")
        val img = intent.getIntExtra("img", 0)
        val description = intent.getStringExtra("description")

        tvLeagueName.text = name
        tvDetaiLeagueDescription.text = "Description : \n${description}"
        ivLeagueLogo.setImageDrawable(ContextCompat.getDrawable(this, img))
    }


    @SuppressLint("SetTextI18n")
    override fun setData2View(league: League) {
        tvLeagueName.text = league.leagueName
        tvLeagueFormedYear.text = "Formed Year : ${league.formedYear}"
        tvLeagueCountry.text = "Country : ${league.country}"

    }
}
