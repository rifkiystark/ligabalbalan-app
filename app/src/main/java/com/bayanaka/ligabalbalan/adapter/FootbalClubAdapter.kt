package com.bayanaka.ligabalbalan.adapter

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bayanaka.ligabalbalan.R
import com.bayanaka.ligabalbalan.model.Leagues
import org.jetbrains.anko.*

class FootbalClubAdapter(val context: Context, var listFootbal : List<Leagues>, val listener : (Leagues) -> Unit) : RecyclerView.Adapter<FootbalClubAdapter.ViewHolder>(){

    class FootbalUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui){
                verticalLayout {
                    id = R.id.itemFootbal

                    lparams{
                        width = matchParent
                        height = wrapContent
                        margin = dip(8)
                    }
                    padding = dip(16)
                    orientation = LinearLayout.VERTICAL
                    gravity = Gravity.CENTER
                    backgroundDrawable = resources.getDrawable(R.drawable.background_card)

                    imageView {
                        id = R.id.ivItemFootbal
                    }.lparams(dip(56), dip(56))

                    textView {
                        id = R.id.tvItemFootbal
                        gravity = Gravity.CENTER
                    }

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FootbalUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = listFootbal.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(listFootbal[position], context, listener)
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val imageClub: ImageView = view.find(R.id.ivItemFootbal)
        private val clubName: TextView = view.find(R.id.tvItemFootbal)
        private val item : LinearLayout = view.find(R.id.itemFootbal)

        fun bindView(footbalData : Leagues, context : Context, listener : (Leagues) -> Unit){
            imageClub.setImageDrawable(context.resources.getDrawable(footbalData.imagesSrc))
            clubName.text = footbalData.leagueName

            item.setOnClickListener {
                listener(footbalData)
            }
        }
    }

}