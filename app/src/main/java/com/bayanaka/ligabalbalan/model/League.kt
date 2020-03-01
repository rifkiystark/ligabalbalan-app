package com.bayanaka.ligabalbalan.model

import com.google.gson.annotations.SerializedName

data class League (
   var idLeague: String? = null,

   @SerializedName("strLeague")
   var leagueName : String? = null,

   @SerializedName("intFormedYear")
   var formedYear : String? = null,

   @SerializedName("strCountry")
   var country : String? = null
)

data class LeagueResponse (
   val leagues : List<League>
)