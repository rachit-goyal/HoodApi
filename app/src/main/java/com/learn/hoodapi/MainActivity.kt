package com.learn.hoodapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.learn.hoodapi.models.Character
import com.learn.hoodapi.ui.theme.HoodApiTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainViewModel by viewModels<MainViewModel>()
            HoodApiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val data by mainViewModel.uiState.collectAsState()

                    when(data.items){
                        is Resource.Loading -> {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .wrapContentSize(align = Alignment.Center)
                            )
                        }

                        is Resource.Success -> {
                            LazyColumn {
                                items((data.items as Resource.Success<List<Character>>).data.size) {
                                    CharacterImageCard(character = (data.items as Resource.Success).data[it])
                                }
                            }
                        }

                        is Resource.Error -> {
                            Text(text = (data.items as Resource.Error).message )
                        }
                    }
               /*     when (data) {
                        is Resource.Loading -> {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .wrapContentSize(align = Alignment.Center)
                            )
                        }

                        is Resource.Success -> {
                            LazyColumn {
                                items((data as Resource.Success<List<Character>>).data.size) {
                                    CharacterImageCard(character = (data as Resource.Success).data[it])
                                }
                            }
                        }

                        is Resource.Error -> {
                            Text(text = (data as Resource.Error).message )
                        }
                    }*/

                }
            }
        }
    }

        @Composable
        fun CharacterImageCard(character: Character) {
            val imagerPainter = rememberAsyncImagePainter(model = character.image)

            Card(
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Box {

                    Image(
                        painter = imagerPainter,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentScale = ContentScale.FillBounds
                    )

                    Surface(
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = .3f),
                        modifier = Modifier.align(Alignment.BottomCenter),
                        contentColor = MaterialTheme.colorScheme.surface
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                        ) {
                            Text(text = "Real name: ${character.actor}")
                            Text(text = "Actor name: ${character.name}")
                        }
                    }


                }


            }
        }

}