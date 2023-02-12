package com.doda.mdnevnik

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.concurrent.TimeUnit

class DatumConverter {

    companion object{
        fun getDatum(datum: Long): String {
            val vrijeme = TimeUnit.SECONDS.toMillis(datum)
            val date = Date(vrijeme)
            val localDate: LocalDate = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate()
            val formatter = DateTimeFormatter.ofPattern("dd.MM.")
            return localDate.format(formatter)
        }

        fun getDugiDatum(datum: Long): String {
            val vrijeme = TimeUnit.SECONDS.toMillis(datum)
            val date = Date(vrijeme)
            val localDate: LocalDate = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate()
            val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.")
            return localDate.format(formatter)
        }
    }

}