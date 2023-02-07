package com.doda.mdnevnik.razredi

import com.doda.mdnevnik.Ocjena

data class Rubrika(
    val name: String,
    val ocjene: List<Ocjena>
)
