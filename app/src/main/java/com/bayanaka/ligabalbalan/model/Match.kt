package com.bayanaka.ligabalbalan.model

import com.google.gson.annotations.SerializedName

data class Match(
    val id : Long? = null,
    @SerializedName("idEvent")
    val idMatch : String? = null,

    val idHomeTeam : String? = null,

    val idAwayTeam : String? = null,

    @SerializedName("strHomeTeam")
    val homeTeam : String? = null,

    @SerializedName("strAwayTeam")
    val awayTeam : String? = null,

    @SerializedName("intHomeScore")
    val homeTeamScore : String? = null,

    @SerializedName("intAwayScore")
    val awayTeamScore : String? = null,

    @SerializedName("dateEvent")
    val date : String? = null,

    @SerializedName("strTime")
    val time : String? = null,

    @SerializedName("strSport")
    val sport : String? = null

) {
    companion object {
        const val TABLE_MATCH = "TABLE_MATCH"
        const val ID = "ID_"
        const val ID_MATCH = "ID_MATCH"
        const val ID_HOME_TEAM = "ID_HOME_TEAM"
        const val ID_AWAY_TEAM = "ID_AWAY_TEAM"
        const val HOME_TEAM = "HOME_TEAM"
        const val AWAY_TEAM = "AWAY_TEAM"
        const val HOME_SCORE = "HOME_SCORE"
        const val AWAY_SCORE = "AWAY_SCORE"
        const val DATE_EVENT = "DATE_EVENT"
        const val TIME_EVENT = "TIME_EVENT"
        const val SPORT = "SPORT"
    }
}

data class MatchResponse(
    var events: List<Match>
)

data class MatchSearchResponse(
    var event : List<Match>
)