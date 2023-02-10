package com.doda.mdnevnik.ispiti

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IspitiResponse(
    @SerialName("data") val ispiti: List<Ispit>,
)
