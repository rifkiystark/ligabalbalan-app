package com.bayanaka.ligabalbalan.modul.detailteam

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bayanaka.ligabalbalan.R
import com.bayanaka.ligabalbalan.model.Team
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_team.*
import kotlinx.android.synthetic.main.item_team.view.*

class DetailTeamActivity : AppCompatActivity() , DetailTeamContract.View{
    var isFavorite = false
    lateinit var presenter : DetailTeamPresenter
    lateinit var team : Team

    override fun onClick() {
        ivFavorite.setOnClickListener {
            if(!isFavorite)
                presenter.saveTeamFavorite(team)
            else
                presenter.deleteTeamFavorite(team.idTeam!!)
        }
    }


    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun isFavorite(isFavorite: Boolean) {
        if (isFavorite)
            ivFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_star_red_24dp
                )
            )
        else
            ivFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_star_black_24dp
                )
            )
        this.isFavorite = isFavorite
    }


    @SuppressLint("SetTextI18n")
    override fun init() {
        presenter = DetailTeamPresenter(this, this)
        team = intent.getParcelableExtra<Team>("extra_team")
        presenter.getFavorite(team.idTeam!!)
        Picasso.get().load(team.strTeamLogo).fit().centerInside()
            .into(ivTeam)
        tvTeam.text = team.strTeam
        tvFormedYear.text = "Formed Year : ${team.intFormedYear}"
        tvCountry.text = "Country : ${team.strCountry}"
        tvStadium.text = "Stadium : ${team.strStadium}"
        tvDescription.text = team.description
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

        init()
        onClick()
    }
}
