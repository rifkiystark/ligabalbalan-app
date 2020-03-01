package com.bayanaka.ligabalbalan.modul.detailmatch

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bayanaka.ligabalbalan.R
import com.bayanaka.ligabalbalan.invisible
import com.bayanaka.ligabalbalan.model.DetailMatch
import com.bayanaka.ligabalbalan.repository.ApiRepository
import com.bayanaka.ligabalbalan.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_match.*

class DetailMatchActivity : AppCompatActivity(), DetailMatchContract.View {

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private var isFavorite = false

    override fun isFavorite(isFavorite: Boolean) {
        if (isFavorite)
            tvFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_star_red_24dp
                )
            )
        else
            tvFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_star_black_24dp
                )
            )

        this.isFavorite = isFavorite
    }

    lateinit var detailMatchPresenter: DetailMatchPresenter
    lateinit var detailMatch: DetailMatch

    override fun showLoading() {
        loadingDetailMatch.visible()
    }

    override fun hideLoading() {
        loadingDetailMatch.invisible()
    }

    override fun requestData(idEvent: String) {
        detailMatchPresenter.getDetailMatch(idEvent)
    }

    override fun setData(detailMatch: DetailMatch) {
        this.detailMatch = detailMatch

        tvDetailMatchHomeTeam.text = detailMatch.homeTeam
        tvDetailMatchAwayTeam.text = detailMatch.awayTeam

        tvDetailMatchHomeScore.text = detailMatch.homeScore
        tvDetailMatchAwayScore.text = detailMatch.awayScore

        tvDetailMatchHomeGoalDetails.text = detailMatch.homeGoalDetails
        tvDetailMatchAwayGoalDetails.text = detailMatch.awayGoalDetails

        tvDetailMatchHomeYellowCards.text = detailMatch.homeYellowCards
        tvDetailMatchAwayYellowCards.text = detailMatch.awayYellowCards

        tvDetailMatchHomeRedCards.text = detailMatch.homeRedCards
        tvDetailMatchAwayRedCards.text = detailMatch.awayRedCards

        Picasso.get().load(detailMatch.logoAwayTeam).fit().centerInside()
            .into(ivDetailMatchLogoAway)
        Picasso.get().load(detailMatch.logoHomeTeam).fit().centerInside()
            .into(ivDetailMatchLogoHome)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)

        detailMatchPresenter = DetailMatchPresenter(this, ApiRepository(), Gson(), this)
        val idEvent = intent.getStringExtra("idEvent")
        requestData(idEvent)
        detailMatchPresenter.isFavorite(idEvent)
        onClick()

    }

    fun onClick() {
        tvFavorite.setOnClickListener {
            if(!isFavorite)
                detailMatchPresenter.saveMatchFavorite(detailMatch)
            else
                detailMatchPresenter.deleteMatchFavorite(detailMatch.idEvent!!)
        }
    }


}
