package com.bayanaka.ligabalbalan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bayanaka.ligabalbalan.R
import com.bayanaka.ligabalbalan.model.Ranking
import kotlinx.android.synthetic.main.item_klasemen.view.*

class RankingsAdapter (
    private val listOfRankings : List<Ranking>
) : RecyclerView.Adapter<RankingsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_klasemen, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfRankings.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listOfRankings[position])
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(ranking : Ranking){
            with(itemView){
                tvTeam.text = ranking.name
                tvPlay.text = ranking.played.toString()
                tvW.text = ranking.played.toString()
                tvD.text = ranking.draw.toString()
                tvL.text = ranking.loss.toString()
                tvPoint.text = ranking.total.toString()
            }
        }
    }

}