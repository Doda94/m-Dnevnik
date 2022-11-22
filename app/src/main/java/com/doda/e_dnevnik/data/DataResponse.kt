package com.doda.e_dnevnik.data

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class DataResponse(
    @SerialName("data") val data: Array<Razred>,
    @SerialName("name") val name: String,
)
