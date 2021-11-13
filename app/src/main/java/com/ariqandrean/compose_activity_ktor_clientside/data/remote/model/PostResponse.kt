package com.ariqandrean.compose_activity_ktor_clientside.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class PostResponse(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)