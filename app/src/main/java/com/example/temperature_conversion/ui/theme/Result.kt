package com.example.temperature_conversion.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.temperature_conversion.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Result(navController: NavController, conv: ConverterViewModel) {
    val focusManager = LocalFocusManager.current
    Scaffold(
        topBar =  { TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                Purple40
            ),
            title = { Text(text = "") }
        )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(top = 75.dp, bottom = 25.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Text(
                text = "Your converted temperature",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Purple40
            )
            Spacer(modifier = Modifier.height(100.dp))
            Column(
                //modifier = Modifier
                 //   .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    text = conv.getCelsius(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
                Text(
                    text = conv.getFarenheit(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
                Text(
                    text = conv.getKelvin(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    focusManager.clearFocus();
                    conv.reset();
                    navController.popBackStack();
                },
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(14.dp),
                colors = ButtonColors(containerColor = Purple40,
                                      contentColor = Color.White,
                                      disabledContainerColor = Color.Red,
                                      disabledContentColor = Color.Red)
            ) {
                Text("BACK", fontSize = 15.sp)
            }

        }
    }
}

