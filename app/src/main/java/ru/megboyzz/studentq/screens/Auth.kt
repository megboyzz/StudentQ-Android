package ru.megboyzz.studentq.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ru.megboyzz.studentq.AsPainter
import ru.megboyzz.studentq.AsString
import ru.megboyzz.studentq.R
import ru.megboyzz.studentq.authLauncher
import ru.megboyzz.studentq.ui.theme.accent
import ru.megboyzz.studentq.ui.theme.background
import ru.megboyzz.studentq.ui.theme.panel

@Composable
fun Auth(navHostController: NavHostController){

    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp

    //Отступ для верхней части экрана
    val headerOffset = (screenHeight * 0.1575).dp

    //Отступ под первым текстом (и под кнопкой)
    val headerText1Offset = (screenHeight * 0.0375).dp

    //Отступ под вторым текстом
    val headerText2Offset = (screenHeight * 0.05625).dp

    //Высота ткстового поля "Обновления"
    val updateFieldHeight = (screenHeight * 0.3625).dp

    //Отступ до кнопки
    val offset = (screenHeight * 0.025).dp

    //Высота кнопки
    val buttonSize = (screenHeight * 0.0525).dp

    //Ширина основного контента страницы
    val contentWidth = (configuration.screenWidthDp * 0.805).dp

    Box(Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ) { Image(R.drawable.ellipse.AsPainter(),"header") }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopEnd
        ){ Image(R.drawable.lines.AsPainter(),"header") }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ){ Image(R.drawable.rectangle.AsPainter(),"footer") }

        Column(Modifier.fillMaxSize()) {
            Spacer(Modifier.height(headerOffset))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier.width(contentWidth)
                ) {
                    Text(
                        text = R.string.header1.AsString(),
                        fontWeight = FontWeight.W400,
                        fontSize = 25.sp,
                        color = Color.White
                    )
                    Spacer(Modifier.height(headerText1Offset))
                    Text(
                        text = R.string.header2.AsString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                    Spacer(Modifier.height(headerText2Offset))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(updateFieldHeight),
                        shape = RoundedCornerShape(10.dp),
                        backgroundColor = panel,
                        elevation = 10.dp
                    ) {
                        Text("adsasd")
                    }
                    Text("")
                    Spacer(Modifier.width(offset))
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(buttonSize),
                        onClick = {
                            authLauncher?.launch(emptyList())
                            //TODO придумать план побега
                            // идея 1: Завязать все коллбеки на ViewModel
                        },
                        colors = buttonColors(
                            backgroundColor = accent,
                            contentColor = Color.White
                        ),
                        elevation = ButtonDefaults.elevation(
                            defaultElevation = 6.dp,
                            pressedElevation = 8.dp,
                            disabledElevation = 0.dp
                        ),
                        shape = RoundedCornerShape(10.dp),
                    ) {
                        Text(
                            text = R.string.login_button1_title.AsString(),
                            fontWeight = FontWeight.W400,
                            fontSize = 14.sp
                        )
                    }

                    Spacer(Modifier.height(headerText1Offset))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                    ){
                        Text(
                            modifier = Modifier.clickable {
                                //TODO Сделать копачку
                            },
                            text = R.string.service_title.AsString(),
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                        Text(
                            modifier = Modifier.clickable {
                                //TODO Сделать копачку
                            },
                            text = R.string.contact_title.AsString(),
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                    }

                }
            }
        }
    }
}