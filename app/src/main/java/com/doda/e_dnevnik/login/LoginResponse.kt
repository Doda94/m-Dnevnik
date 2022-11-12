package com.doda.e_dnevnik.login

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class LoginResponse(
    @SerialName("LoginSuccessful") val isSuccessful: Boolean,
    @SerialName("token") val token: String,
)
