package com.doda.mdnevnik.vladanje

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class Vladanje(
    @SerialName("title") val title: String,
    @SerialName("dataText") val dataText: String,
)
