package com.doda.mdnevnik.izostanci

import com.doda.mdnevnik.db.IzostanakEntity

data class DatumIzostanka(
    val datum: Long,
    val izostanci: List<IzostanakEntity>
)
