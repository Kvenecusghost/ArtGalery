package com.example.artgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artgallery.ui.theme.ArtGalleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtGalleryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    ArtGalery()
                }
            }
        }
    }
}

@Composable
fun ImageDescription(
    author: String, description: String, modifier: Modifier
) {
    Column {
        Text(
            text = author, fontSize = 24.sp
        )
        Text(
            text = description, fontSize = 16.sp
        )
    }
}

@Composable
fun ControlButton(
    text: String,
    onButtonClick: () -> Unit,
    modifier: Modifier
) {
    Button(
        onClick = onButtonClick,
        modifier = modifier
    ) {
        Text(text = text)
    }
}

@Composable
fun ArtGalery(modifier: Modifier = Modifier) {
    val images = listOf<List<Int>>(
        listOf(R.drawable.cansas_city, R.string.cansas_city_author, R.string.cansas_city_location),
        listOf(R.drawable.chicago, R.string.chicago_author, R.string.chicago_location),
        listOf(R.drawable.dubai, R.string.dubai_author, R.string.dubai_location),
        listOf(R.drawable.moscow, R.string.moscow_author, R.string.moscow_location),
        listOf(R.drawable.new_york, R.string.new_yourk_author, R.string.new_yourk_location),
        listOf(R.drawable.vancouver, R.string.vancouver_author, R.string.vancouver_location)
    )
    var imageId by remember { mutableStateOf(0) }
    val LAST_IMAGE_ID = 5
    var nextButtonClicked by remember {
        mutableStateOf(false)
    }
    var previosButtonClicked by remember {
        mutableStateOf(false)
    }

    if (nextButtonClicked) {
        imageId = if (imageId >= LAST_IMAGE_ID) 0 else imageId.inc()
        nextButtonClicked = false
    } else if (previosButtonClicked) {
        imageId = if (imageId <= 0) LAST_IMAGE_ID else imageId.dec()
        previosButtonClicked = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = images[imageId][0]),
            contentDescription = null,
            modifier = Modifier
                .weight(4F)
                .padding(bottom = 16.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        ImageDescription(
            author = stringResource(id = images[imageId][1]),
            description = stringResource(id = images[imageId][2]),
            modifier = Modifier
                .weight(1F)
                .padding(bottom = 32.dp)
        )
        Row(
            modifier = Modifier.weight(1F).fillMaxWidth()
        ) {
            ControlButton(
                text = "Previous",
                onButtonClick = { previosButtonClicked = true },
                modifier = Modifier.weight(1F).padding(end = 8.dp).fillMaxWidth()
            )
            ControlButton(
                text = "Next",
                onButtonClick = { nextButtonClicked = true },
                modifier = Modifier.weight(1F).padding(start = 8.dp).fillMaxWidth()
            )
        }
    }
}

@Preview(
    showBackground = true, showSystemUi = true
)
@Composable
fun ArtGaleryPreview() {
    ArtGalleryTheme {
        ArtGalery()
    }
}