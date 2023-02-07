package com.doda.mdnevnik.biljeske

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class LinksData(
    @SerialName("href") val href: String,
    @SerialName("text") val text: String,
)
