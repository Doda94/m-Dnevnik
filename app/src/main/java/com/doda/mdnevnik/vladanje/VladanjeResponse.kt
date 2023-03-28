package com.doda.mdnevnik.vladanje

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class VladanjeResponse(
    @SerialName("data") val data: List<Vladanje>,
)
