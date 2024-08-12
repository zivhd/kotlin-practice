package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAbsoluteAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
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
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                ArtSpacePreview()
            }
        }
    }
}

fun ProceedToNextImage(currentImage: Int): Int {
    if (currentImage ==2){
        return 1
    }
    return 2
}

@Composable
fun ArtImageAndText(
    modifier: Modifier = Modifier,
) {
    var currentImage by remember {mutableIntStateOf(1)}

    val context = LocalContext.current
    val drawableResourceId = context.resources.getIdentifier(
        "image_$currentImage",
        "drawable",
        context.packageName
    )

    val textTitleResourceId = context.resources.getIdentifier(
        "image_${currentImage}_title",
        "string",
        context.packageName
    )

    val textAuthorResourceId = context.resources.getIdentifier(
        "image_${currentImage}_author",
        "string",
        context.packageName
    )

    val textYearResourceId = context.resources.getIdentifier(
        "image_${currentImage}_year",
        "string",
        context.packageName
    )

    val contentDescriptionResourceId = context.resources.getIdentifier(
        "image_${currentImage}_cd",
        "string",
        context.packageName
    )




    Box(
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Box(
                modifier = Modifier
                    .shadow(elevation = 5.dp)
                    .background(Color.White).height(500.dp),
                contentAlignment = Alignment.Center
            )
            {
                Image(
                    modifier = Modifier.padding(10.dp),
                    painter = painterResource(drawableResourceId),
                    contentDescription = stringResource(
                        contentDescriptionResourceId
                    )
                )
            }
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_vertical)))
            Box(modifier = Modifier.background(color = Color.LightGray)){
                Column(
                 modifier = Modifier.padding(5.dp)
                ) {
                    Text(stringResource(textTitleResourceId), fontSize = 30.sp)
                    Row{
                        Text(stringResource(textAuthorResourceId), fontWeight = FontWeight.Bold )
                        Text(" (" + stringResource(textYearResourceId)+")" )
                    }
                }


            }
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_vertical)))

            Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically ){
                Button(onClick= {currentImage = ProceedToNextImage(currentImage) },modifier = Modifier.width(150.dp)){ Text(text = stringResource(R.string.previous))}
                Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_vertical)))
                Button(onClick= {currentImage = ProceedToNextImage(currentImage)}, modifier = Modifier.width(150.dp)){ Text(text = stringResource(R.string.next))}
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtImageAndText(
            Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
                .padding(20.dp))}
}