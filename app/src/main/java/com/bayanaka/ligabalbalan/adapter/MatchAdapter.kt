package com.bayanaka.ligabalbalan.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bayanaka.ligabalbalan.R
import com.bayanaka.ligabalbalan.model.Match
import kotlinx.android.synthetic.main.item_match.view.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class MatchAdapter(val listOfMatch : List<Match>?, val listener : (String) -> Unit) : RecyclerView.Adapter<MatchAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listOfMatch?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listOfMatch?.get(position), listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        @SuppressLint("SetTextI18n", "SimpleDateFormat")
        fun bind(match : Match?, listener : (String) -> Unit){
            with(itemView){
                val formatter = SimpleDateFormat("yyyy-MM-dd")
                val date = formatter.parse(match?.date)
                val formatter2 = SimpleDateFormat("dd MMM yyyy")

                tvItemMatchHomeTeam.text = match?.homeTeam
                tvItemMatchAwayTeam.text = match?.awayTeam
                tvItemMatchTime.text = match?.time!!.dropLast(3) + " UTC, " + formatter2.format(date)
                tvAwayScore.text = match.awayTeamScore ?: "0"
                tvHomeScore.text = match.homeTeamScore ?: "0"
                itemMatch.setOnClickListener {
                    listener(match.idMatch!!)
                }
            }
        }


    }

}