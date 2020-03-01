package com.bayanaka.ligabalbalan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bayanaka.ligabalbalan.R
import com.bayanaka.ligabalbalan.model.Team
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_team.view.*

class TeamsAdapter(
    private var listOfTeams: List<Team>? = listOf(),
    private val listener : (Team?) -> Unit
) : RecyclerView.Adapter<TeamsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false)
        return ViewHolder(view)
    }

    fun updateDataSet(listOfTeams: List<Team>){
        this.listOfTeams = listOfTeams
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listOfTeams?.size?:0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listOfTeams?.get(position), listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(team: Team?, listener: (Team?) -> Unit) {
            with(itemView) {
                Picasso.get().load(team?.strTeamLogo).fit().centerInside()
                    .into(ivTeam)
                tvTeam.text = team?.strTeam
                tvFormedYear.text = team?.intFormedYear
                itemTeam.setOnClickListener {
                    listener(team)
                }
            }

        }
    }
}