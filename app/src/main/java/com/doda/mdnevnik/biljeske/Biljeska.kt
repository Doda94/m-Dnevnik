package com.doda.mdnevnik.biljeske

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class Biljeska(
    @SerialName("title") val title: String = "",
    @SerialName("dataText") val dataText: String = "",
//    @SerialName("linksData") val linksData: LinksData = LinksData("",""),
)
