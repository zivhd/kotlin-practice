package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadePreview()
            }
        }
    }
}

fun ProceedToNextStep(currentStep: Int): Int {
    if (currentStep == 4) {
        return 1
    }



    return currentStep + 1
}

@Composable
fun LemonadeImageWithText(modifier: Modifier = Modifier) {
    var currentStep by remember { mutableIntStateOf(1) }
    var squeezeCount by remember { mutableIntStateOf(0) }

    val imageResource = when (currentStep) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        4 -> R.drawable.lemon_restart
        else -> R.drawable.lemon_tree
    }

    val stringResource = when (currentStep) {
        1 -> stringResource(R.string.tap_lemon_instruction)
        2 -> stringResource(R.string.keep_tapping_instruction)
        3 -> stringResource(R.string.tap_to_drink)
        4 -> stringResource(R.string.tap_empty_glass)
        else -> stringResource(R.string.tap_lemon_instruction)
    }

    val contentDescription = when (currentStep) {
        1 -> stringResource(R.string.lemon_tree)
        2 -> stringResource(R.string.lemon)
        3 -> stringResource(R.string.glass_of_lemonade)
        4 -> stringResource(R.string.empty_glass)
        else -> stringResource(R.string.lemon_tree)
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Button(onClick = {

            if (currentStep == 1) {
                squeezeCount = (2..4).random()
            }
            if (currentStep == 2) {
                squeezeCount--
                if (squeezeCount == 0) {
                    currentStep = 3
                }
            } else {
                currentStep = ProceedToNextStep(currentStep)
            }

        }) {
            Image(painter = painterResource(imageResource), contentDescription = contentDescription)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(stringResource)


    }

}


@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    LemonadeImageWithText(
        Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}