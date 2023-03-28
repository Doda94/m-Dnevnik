package com.doda.mdnevnik.izostanci

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class Izostanak(
    @SerialName("classNumber") val classNumber: Int,
    @SerialName("subject") val subject: String,
    @SerialName("status") val status: String,
    @SerialName("description") val description: String,
    @SerialName("date") val date: Long,
)
