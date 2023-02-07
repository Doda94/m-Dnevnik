package com.doda.mdnevnik.login

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class LoginResponse(
    @SerialName("LoginSuccessful") val isSuccessful: Boolean,
    @SerialName("token") val token: String,
)
