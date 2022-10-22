package com.doda.e_dnevnik.login

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class LoginRequest(
    @SerialName("username") val username: String,
    @SerialName("password") val password: String,
)
