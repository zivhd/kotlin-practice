package com.example.firstapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstapp.ui.theme.FirstAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirstAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingImage(message = stringResource(R.string.happy_birthday_ziv), from = stringResource(R.string.from_ziv), modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp))

                }
            }
        }
    }
}

@Composable
fun GreetingImage(message: String, from: String,modifier: Modifier = Modifier){
    val image = painterResource(R.drawable.androidparty)
    Box(modifier) {
        Image(
            painter= image,
            alpha = 0.5F,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        GreetingText(
            message = stringResource(R.string.happy_birthday_ziv),
            from = stringResource(R.string.from_ziv),
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
            )


    }


}
@Composable
fun GreetingText(message: String,from: String, modifier: Modifier = Modifier){
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {

        Text(
            text = message,
            fontSize = 100.sp,
            lineHeight = 116.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = from,
            fontSize = 36.sp,
            modifier = Modifier
                .padding(16.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
    }


}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FirstAppTheme {
        GreetingImage(message = stringResource(R.string.happy_birthday_ziv), from = stringResource(R.string.from_ziv), modifier = Modifier
            .fillMaxSize()
            .padding(8.dp))
    }
}

