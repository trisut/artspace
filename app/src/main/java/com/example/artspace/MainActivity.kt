package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp),
                    color = Color.White, // Ganti warna sesuai kebutuhan
                    elevation = 4.dp, // Atur elevasi sesuai kebutuhan
                    border = BorderStroke(2.dp, color = Color.Transparent) // Atur garis tepi sesuai kebutuhan
                )
                {
                    ArtSpaceScreen()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceScreen(modifier: Modifier = Modifier) {
    val firstArtwork = R.drawable.denji_face
    val secondArtwork = R.drawable.zero_two_face
    val thirdArtwork = R.drawable.sanji_face
    val fourthArtwork = R.drawable.naruto_face

    var title by remember {
        mutableStateOf(R.string.denji)
    }

    var year by remember {
        mutableStateOf(R.string.denji_year)
    }

    var currentArtwork by remember {
        mutableStateOf(firstArtwork)
    }

    var imageResource by remember {
        mutableStateOf(currentArtwork)
    }


    Column(
        modifier = Modifier
            .padding(16.dp)
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ArtworkDisplay(currentArtwork = currentArtwork)
        Spacer(modifier = modifier.size(20.dp))
        ArtworkTitle(title = title, year = year)
        Spacer(modifier = modifier.size(40.dp))
        Row(
            modifier = modifier.padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
        ) {
            // Previous Button
            Button(
                onClick = {
                    when (currentArtwork) {
                        firstArtwork -> {
                            currentArtwork = fourthArtwork
                            title = R.string.naruto
                            year = R.string.naruto_year
                        }
                        secondArtwork -> {
                            currentArtwork = firstArtwork
                            title = R.string.denji
                            year = R.string.denji_year
                        }
                        thirdArtwork -> {
                            currentArtwork = secondArtwork
                            title = R.string.zero_two
                            year = R.string.zero_two_year
                        }
                        else -> {
                            currentArtwork = thirdArtwork
                            title = R.string.sanji
                            year = R.string.sanji_year
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.gray_900)
                ),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 1.dp,
                    pressedElevation = 0.dp,
                    focusedElevation = 0.dp
                )
            ) {
                Text(
                    text = "Previous",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.white)
                )
            }

            // Next Button
            Button(
                onClick = {
                    when (currentArtwork) {
                        firstArtwork -> {
                            currentArtwork = secondArtwork
                            title = R.string.zero_two
                            year = R.string.zero_two_year
                        }
                        secondArtwork -> {
                            currentArtwork = thirdArtwork
                            title = R.string.sanji
                            year = R.string.sanji_year
                        }
                        thirdArtwork -> {
                            currentArtwork = fourthArtwork
                            title = R.string.naruto
                            year = R.string.naruto_year
                        }
                        else -> {
                            currentArtwork = firstArtwork
                            title = R.string.denji
                            year = R.string.denji_year
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.gray_900)
                ),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 1.dp,
                    pressedElevation = 0.dp,
                    focusedElevation = 0.dp
                )
            ) {
                Text(
                    text = "Next",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.white)
                )
            }
        }
    }
}

@Composable
fun ArtworkDisplay(
    modifier: Modifier = Modifier,
    @DrawableRes currentArtwork: Int
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = 8.dp // Atur elevasi sesuai kebutuhan
    ) {
        Image(
            painter = painterResource(currentArtwork),
            contentDescription = stringResource(id = R.string.zero_two),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .height(500.dp) // Sesuaikan tinggi kartu sesuai kebutuhan
                .fillMaxWidth()
        )
    }
}

@Composable
fun ArtworkTitle(
    @StringRes title: Int,
    @StringRes year: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(0.dp),
        elevation = 4.dp, // Atur elevasi sesuai kebutuhan
        border = BorderStroke(2.dp, Color.Transparent),
        backgroundColor = Color.LightGray
    ) {
        Column(
            modifier = Modifier
                .padding(0.dp)
                .padding(vertical = 0.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Artwork title
            Text(
                text = stringResource(id = title),
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.black),
                fontSize = 35.sp
            )

            // Artwork year
            Text(
                text = "— ${stringResource(id = year)} —",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = colorResource(id = R.color.gray_300)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceScreen()
    }
}