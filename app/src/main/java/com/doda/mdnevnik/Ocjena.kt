package com.doda.mdnevnik

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class Ocjena(
    @SerialName("note") val note: String,
    @SerialName("date") val date: Int,
    @SerialName("grade") val grade: String,
    @SerialName("ed_id") val ed_id: String,
    @SerialName("field") val field: String = "",
)
