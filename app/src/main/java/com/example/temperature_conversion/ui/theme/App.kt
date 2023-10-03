package com.example.temperature_conversion.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.temperature_conversion.navigation.Screens


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(navController: NavController, conv: ConverterViewModel) {
    Scaffold(
        topBar =  { TopAppBar(
            colors = topAppBarColors(
                Purple40
            ),
            title = { Text(text = "") }
        )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 75.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(38.dp)
            ) {
                Text(
                    text = "Enter a temperature you would like to convert and choose the unit it is in. The temperature gets converted to °C, °F and °K",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    color = Purple40,
                    modifier = Modifier.width(275.dp)
                )

                Row(modifier = Modifier) {
                    CustomTextField(conv.getInputStateValue(),ImeAction.Done, conv::updateInput)
                    Spacer(modifier = Modifier.width(5.dp))
                    DropDownMenu(updateMode = conv::updateMode)
                }

                val focusManager = LocalFocusManager.current
                Button(
                    onClick = {
                        focusManager.clearFocus();
                        conv.calculate();
                        if (conv.getInputStateValue().error == null) {
                            navController.navigate(Screens.Result.route)
                        }
                              },
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(14.dp),
                    colors = ButtonColors(containerColor = Purple40,
                                          contentColor = Color.White,
                                          disabledContainerColor = Color.Red,
                                          disabledContentColor = Color.Red)
                ) {
                    Text("CONVERT", fontSize = 15.sp)
                }

            }
        }
    }
}
