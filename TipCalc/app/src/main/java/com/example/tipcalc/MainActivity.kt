package com.example.tipcalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tipcalc.ui.theme.TipCalcTheme
import java.text.NumberFormat
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.material3.Icon

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TipCalcTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    TipPreview()
                }

            }
        }
    }
}

private fun calculateTip(amount: Double,
                         tipPercent: Double = 15.0,
                         isTipRoundedUp: Boolean): String {
    var tip = tipPercent / 100 * amount
    if(isTipRoundedUp){
        tip = kotlin.math.ceil(tip)
    }
    return NumberFormat.getCurrencyInstance().format(tip)
}

@Composable
fun EditNumberField(
    @DrawableRes leadingIcon: Int,
    modifier: Modifier = Modifier,
    labelResourceString: Int,
    value: String,
    onValueChange: (String) -> Unit,
    imeAction: ImeAction
) {


    TextField(
        leadingIcon = { Icon(painter = painterResource(id = leadingIcon), null) },
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = { Text(stringResource(labelResourceString)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number, imeAction = imeAction
        )
    )
}


@Composable
fun TipLayout() {
    var amountInput by remember { mutableStateOf("0") }
    var tipPercentInput by remember { mutableStateOf("15") }
    var isTipRoundedUp by remember { mutableStateOf(false) }
    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val tip = calculateTip(amount = amount,
        tipPercent = tipPercentInput.toDoubleOrNull() ?: 0.0,
        isTipRoundedUp = isTipRoundedUp )
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,

        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.calculate_tip),
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(alignment = Alignment.Start)
        )
        EditNumberField(
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth(),
            labelResourceString = R.string.bill_amount,
            value = amountInput,
            onValueChange = { amountInput = it },
            imeAction = ImeAction.Done,
            leadingIcon = R.drawable.baseline_money_24,

        )
        EditNumberField(
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth(),
            labelResourceString = R.string.tip_percent,
            value = "$tipPercentInput%",
            onValueChange = { tipPercentInput = it.replace("%", "") },
            imeAction = ImeAction.Send,
            leadingIcon = R.drawable.baseline_percent_24,
        )
        RoundTheTipRow(
            isTipRoundedUp = isTipRoundedUp,
            onRoundUpChanged = { isTipRoundedUp = it },
            modifier = Modifier.padding(bottom = 32.dp),
        )

        Text(
            text = stringResource(R.string.tip_amount, tip),
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.height(150.dp))

    }
}

@Composable
private fun RoundTheTipRow(
    isTipRoundedUp: Boolean, onRoundUpChanged: (Boolean) -> Unit, modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .size(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.round_up_tip),
        )
        Switch(
            checked = isTipRoundedUp, onCheckedChange = onRoundUpChanged,
            modifier = modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TipPreview() {
    TipCalcTheme {
        TipLayout()
    }
}