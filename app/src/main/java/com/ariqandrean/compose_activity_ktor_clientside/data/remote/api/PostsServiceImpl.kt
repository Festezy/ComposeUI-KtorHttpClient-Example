package com.ariqandrean.compose_activity_ktor_clientside.data.remote.api

import com.ariqandrean.compose_activity_ktor_clientside.data.remote.HttpRoutes
import com.ariqandrean.compose_activity_ktor_clientside.data.remote.model.PostRequest
import com.ariqandrean.compose_activity_ktor_clientside.data.remote.model.PostResponse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*

class PostsServiceImpl(private val client: HttpClient): PostsService {
    override suspend fun getPosts(): List<PostResponse> {
        return try {
            client.get{ url(HttpRoutes.POSTS) }
        } catch (e: RedirectResponseException){
//            3xx - response
            println("Eror: ${e.response.status.description}")
            emptyList()
        } catch (e: ClientRequestException){
//            4xx - response
            println("Eror: ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException){
//            5xx - response
            println("Eror: ${e.response.status.description}")
            emptyList()
        } catch (e: Exception){
//            3xx - response
            println("Eror: ${e.message}")
            emptyList()
        }
    }

    override suspend fun createPost(postRequest: PostRequest): PostResponse? {
        return try {
            client.post<PostResponse>(){
                url(HttpRoutes.POSTS)
                contentType(ContentType.Application.Json)
                body = postRequest
            }
        } catch (e: RedirectResponseException){
//            3xx - response
            println("Eror: ${e.response.status.description}")
            null
        } catch (e: ClientRequestException){
//            4xx - response
            println("Eror: ${e.response.status.description}")
            null
        } catch (e: ServerResponseException){
//            5xx - response
            println("Eror: ${e.response.status.description}")
            null
        } catch (e: Exception){
//            3xx - response
            println("Eror: ${e.message}")
            null
        }
    }
}