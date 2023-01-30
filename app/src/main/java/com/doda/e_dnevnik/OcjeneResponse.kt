package com.doda.e_dnevnik

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class OcjeneResponse(
    @SerialName("prvoPolugodiste") val prvoPolugodiste: String? = "",
    @SerialName("drugoPolugodiste") val drugoPolugodiste: String? = "",
    @SerialName("elementiOcjenjivanjaList") val elementiOcjenjivanjaList: List<String>? = listOf(),
    @SerialName("data") val data: List<Ocjena>? = listOf(),
)
