package com.doda.e_dnevnik.razredi

import com.doda.e_dnevnik.Ocjena

data class PredmetEntity(
    val predmetId: String,
    val predmet: Predmet,
    val prvoPolugodiste: String?,
    val drugoPolugodiste: String?,
    val elementiOcjenjivanjaList: List<String>?,
    val ocjene: List<Ocjena>?,
    val prosjek: Double?,
)
