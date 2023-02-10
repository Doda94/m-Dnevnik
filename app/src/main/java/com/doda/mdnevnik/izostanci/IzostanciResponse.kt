package com.doda.mdnevnik.izostanci

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class IzostanciResponse(
    @SerialName("data") val data: List<Izostanak>,
)
