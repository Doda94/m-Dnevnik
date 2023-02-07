package com.doda.mdnevnik

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class Biljeska(
    @SerialName("title") val title: String,
    @SerialName("dataText") val dataText: String,
    // TODO check linksData type
//    @SerialName("linksData") val linksData: List<String>,
)
