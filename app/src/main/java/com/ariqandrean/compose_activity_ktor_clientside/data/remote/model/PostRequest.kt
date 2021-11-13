package com.ariqandrean.compose_activity_ktor_clientside.data.remote.model

data class PostRequest(
    val userId: Int,
    val title: String,
    val body: String
)