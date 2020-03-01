package com.bayanaka.ligabalbalan.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(
    val id : Long? = null,
    val idTeam : String? = null,
    val strTeam : String? = null,
    val strTeamLogo : String? = null,

    @SerializedName("strDescriptionEN")
    val description : String? = null,

    val strSport : String? = null,

    val strCountry : String? = null,
    val intFormedYear : String? = null,
    val strStadium : String? = null
) : Parcelable {
    companion object {
        const val TABLE_TEAM = "TABLE_TEAM"
        const val ID = "ID_"
        const val ID_TEAM = "ID_TEAM"
        const val TEAM = "TEAM"
        const val TEAM_LOGO = "TEAM_LOGO"
        const val DESCRIPTION = "DESCRIPTION"
        const val COUNTRY = "COUNTRY"
        const val FORMED_YEAR = "FORMED_YEAR"
        const val STADIUM = "STADIUM"
        const val SPORT = "SPORT"
    }
}

data class TeamResponse(
    val teams : List<Team>? = listOf()
)

