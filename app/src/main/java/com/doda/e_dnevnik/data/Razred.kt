package com.doda.e_dnevnik.data

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class Razred(
    @SerialName("name") val name: String,
    @SerialName("gen") val gen: String,
    @SerialName("ed_id") val ed_id: String,
)
