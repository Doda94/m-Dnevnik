package com.doda.mdnevnik.data

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class DataResponse(
    @SerialName("data") val data: Array<Razred>,
    @SerialName("name") val name: String,
)
