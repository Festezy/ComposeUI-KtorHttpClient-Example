package com.ariqandrean.compose_activity_ktor_clientside.data.remote.api

import com.ariqandrean.compose_activity_ktor_clientside.data.remote.model.PostRequest
import com.ariqandrean.compose_activity_ktor_clientside.data.remote.model.PostResponse
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

interface PostsService {

    suspend fun getPosts(): List<PostResponse>

    suspend fun createPost(postRequest: PostRequest): PostResponse?

    companion object{
        fun create(): PostsService {
            return PostsServiceImpl(
                client = HttpClient(Android){
                    install(Logging){
                        level = LogLevel.ALL
                    }
                    install(JsonFeature){
                        serializer = KotlinxSerializer()
                    }
                }
            )
        }
    }
}