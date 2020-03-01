package com.bayanaka.ligabalbalan.model

import com.google.gson.annotations.SerializedName

data class DetailMatch(
    val idEvent : String? = null,
    val idHomeTeam : String? = null,
    val idAwayTeam : String? = null,
    var logoHomeTeam : String? = null,
    var logoAwayTeam : String? = null,

    @SerializedName("intHomeScore")
    val homeScore : String? = "0",
    @SerializedName("intAwayScore")
    val awayScore : String? = "0",
    @SerializedName("strHomeTeam")
    val homeTeam : String? = null,
    @SerializedName("strAwayTeam")
    val awayTeam : String? = null,
    @SerializedName("strHomeGoalDetails")
    val homeGoalDetails : String? = "-",
    @SerializedName("strAwayGoalDetails")
    val awayGoalDetails : String? = "-",
    @SerializedName("strHomeRedCards")
    val homeRedCards : String? = "-",
    @SerializedName("strAwayRedCards")
    val awayRedCards : String? = "-",
    @SerializedName("strHomeYellowCards")
    val homeYellowCards : String? = "-",
    @SerializedName("strAwayYellowCards")
    val awayYellowCards : String? = "-",

    @SerializedName("dateEvent")
    val date : String? = null,

    @SerializedName("strTime")
    val time : String? = null
)


data class DetailMatchResponse(
    val events : List<DetailMatch>
)