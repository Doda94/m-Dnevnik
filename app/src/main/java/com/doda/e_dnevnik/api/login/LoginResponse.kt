package com.doda.e_dnevnik.api.login

import kotlinx.serialization.SerialName

data class LoginResponse(
    @SerialName("LoginSuccessful") val isSuccessful: Boolean,
)
