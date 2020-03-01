package com.bayanaka.ligabalbalan.modul.klasemen


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.bayanaka.ligabalbalan.R
import com.bayanaka.ligabalbalan.adapter.RankingsAdapter
import com.bayanaka.ligabalbalan.invisible
import com.bayanaka.ligabalbalan.model.Ranking
import com.bayanaka.ligabalbalan.repository.ApiRepository
import com.bayanaka.ligabalbalan.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_klasemen.*

/**
 * A simple [Fragment] subclass.
 */
class KlasemenFragment(
    private  val idLeague : String
) : Fragment(), KlasemenContract.View {
    override fun init() {
        val presenter = KlasemenPresenter(this, ApiRepository(), Gson())
        presenter.getData(idLeague)
    }

    override fun showProgressBar() {
        loadingKlasemen.visible()
    }

    override fun hideProgressBar() {
        loadingKlasemen.invisible()
    }

    override fun setRecyclerView(listOfKlasemen: List<Ranking>) {
        rvKlasemen.layoutManager = LinearLayoutManager(context)
        rvKlasemen.adapter = RankingsAdapter(listOfKlasemen)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_klasemen, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }
}
