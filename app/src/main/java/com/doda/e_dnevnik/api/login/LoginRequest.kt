package com.doda.e_dnevnik.api.login

import kotlinx.serialization.SerialName

data class LoginRequest(
    @SerialName("username") val username: String,
    @SerialName("password") val password: String,
)
