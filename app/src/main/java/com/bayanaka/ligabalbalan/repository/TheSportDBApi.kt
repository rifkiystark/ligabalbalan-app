package com.bayanaka.ligabalbalan.repository

import com.bayanaka.ligabalbalan.BuildConfig

object TheSportDBApi {

    private const val BASE_URL = BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}"
    fun getTeam(idTeam: String?): String {
        return "${BASE_URL}/lookupteam.php?id=${idTeam}"
    }

    fun getDetailLeague(idLeague : String): String {
        return "$BASE_URL/lookupleague.php?id=$idLeague"
    }

    fun getSearchMatch(query : String) : String{
        return "$BASE_URL/searchevents.php?e=$query"
    }

    fun getNextMatch(idLeague : String): String {
        return "$BASE_URL/eventsnextleague.php?id=$idLeague"
    }

    fun getPreviousMatch(idLeague : String): String {
        return "$BASE_URL/eventspastleague.php?id=$idLeague"
    }

    fun getDetailMatch(idEvent : String) : String {
        return "$BASE_URL/lookupevent.php?id=$idEvent"
    }

    fun getRanking(idLeague : String) : String {
        return "$BASE_URL/lookuptable.php?l=$idLeague"
    }

    fun getTeams(idLeague : String) : String {
        return "$BASE_URL/lookup_all_teams.php?id=$idLeague"
    }

    fun findTeams(query : String) : String {
        return "$BASE_URL/searchteams.php?t=$query"
    }


}