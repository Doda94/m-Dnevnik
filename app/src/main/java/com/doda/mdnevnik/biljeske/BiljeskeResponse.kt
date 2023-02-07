package com.doda.mdnevnik.biljeske

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class BiljeskeResponse(
    @SerialName("data") val data: List<Biljeska>,
)
