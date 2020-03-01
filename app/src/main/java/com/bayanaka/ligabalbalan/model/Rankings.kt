package com.bayanaka.ligabalbalan.model

data class Ranking(
    var name: String? = null,

    var played: Int? = null,

    var win: Int? = null,

    var draw: Int? = null,

    var loss: Int? = null,

    var total: Int? = null
)

data class RankingsResponse(
    val table: List<Ranking>
)