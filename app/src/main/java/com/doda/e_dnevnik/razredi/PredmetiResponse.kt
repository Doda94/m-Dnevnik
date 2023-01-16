package com.doda.e_dnevnik.razredi

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class PredmetiResponse(
    @SerialName("data") val data: List<Predmet>,
)
