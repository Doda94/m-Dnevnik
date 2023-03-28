package com.doda.mdnevnik.razredi

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class PredmetiResponse(
    @SerialName("data") val data: List<Predmet>,
)
