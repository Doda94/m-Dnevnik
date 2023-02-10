package com.doda.mdnevnik.ispiti

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class Ispit(
    @SerialName("subject") val predmet: String,
    @SerialName("note") val biljeska: String,
    @SerialName("date") val datum: Long,
)
