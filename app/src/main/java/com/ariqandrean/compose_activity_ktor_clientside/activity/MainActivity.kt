package com.ariqandrean.compose_activity_ktor_clientside.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ariqandrean.compose_activity_ktor_clientside.data.remote.api.PostsService
import com.ariqandrean.compose_activity_ktor_clientside.data.remote.model.PostResponse
import com.ariqandrean.compose_activity_ktor_clientside.ui.theme.ComposeActivityKtorClientSideTheme

class MainActivity : ComponentActivity() {
    private val service = PostsService.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val posts = produceState<List<PostResponse>>(
                initialValue = emptyList(),
                producer = { value = service.getPosts() }
            )
            ComposeActivityKtorClientSideTheme {
                androidx.compose.material.Surface(color = MaterialTheme.colors.background) {
                    LazyColumn {
                        items(posts.value) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Text(text = it.title, fontSize = 20.sp)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = it.body, fontSize = 14.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}


