package com.doda.e_dnevnik.razredi

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class Predmet(
    @SerialName ("subjectID") val subjectID: String,
    @SerialName ("subject") val subject: String,
    @SerialName ("teachersName") val teachersName: String,
)
