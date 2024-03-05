package com.example.meditationui.ui.theme

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAbsoluteAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditationui.BottomMenuuContent
import com.example.meditationui.Features
import com.example.meditationui.R
import com.example.meditationui.standardQuadFromTo

@Composable
fun HomeScreen(){
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ){
        Column {

            GreetingSection()
            ChipSection(chips = listOf("Sweet Sleep", "Insomnia", "Depression"))
            DailyMediation()
            FeatureSection(features = listOf(
                    Features(
                        title = "Sleep meditation",
                        R.drawable.ic_headphone,
                        BlueViolet1,
                        BlueViolet2,
                        BlueViolet3
                    ),
                Features(
                    title = "Tips for sleeping",
                    R.drawable.ic_videocam,
                    LightGreen1,
                    LightGreen2,
                    LightGreen3
                ),
                Features(
                    title = "Night island",
                    R.drawable.ic_headphone,
                    OrangeYellow1,
                    OrangeYellow2,
                    OrangeYellow3
                ),
                Features(
                    title = "Calming sounds",
                    R.drawable.ic_headphone,
                    Beige1,
                    Beige2,
                    Beige3
                )
                ))
        }
        BottomMenu(items = listOf(
            BottomMenuuContent("Home", R.drawable.ic_home),
            BottomMenuuContent("Meditate", R.drawable.ic_bubble),
            BottomMenuuContent("Sleep", R.drawable.ic_moon),
            BottomMenuuContent("Music", R.drawable.ic_music),
            BottomMenuuContent("Profile", R.drawable.ic_profile)
        ),
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun BottomMenu(
    modifier: Modifier = Modifier,
    items : List<BottomMenuuContent>,
    activeHighnlight: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    initialSelectedItemIndex: Int = 0
) {
    var selectedItemIndex by remember {
        mutableIntStateOf(initialSelectedItemIndex)
    }

    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(DeepBlue)
            .padding(15.dp)
            .fillMaxWidth()
    ){
        items.forEachIndexed{
            index,
            item2 ->
            BottomMenuItem(
                item = item2,
                isSelected = index == selectedItemIndex,
                activeHighnlight = activeHighnlight,
                inactiveTextColor = inactiveTextColor,
                activeTextColor = activeTextColor
            )
            {
                selectedItemIndex = index
            }
        }
    }

}

@Composable
fun BottomMenuItem(
    item :BottomMenuuContent,
    isSelected: Boolean = false,
    activeHighnlight: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    onItemCLick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onItemCLick()
        }
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(
                    if (isSelected) activeHighnlight
                    else Color.Transparent
                )
                .padding(10.dp)
        ){
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription =item.title,
                tint = if (isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = item.title,
            color = if (isSelected) activeTextColor else inactiveTextColor
        )
    }
}


@Composable
fun GreetingSection(name : String = "Anubhav"){
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 60.dp, start = 15.dp, end = 15.dp, bottom = 15.dp),

    ){
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Good Morning, $name",
                style = MaterialTheme.typography.headlineSmall,
                color = TextWhite
            )
            Text(
                text = "We wish you have a good day!!",
                style = MaterialTheme.typography.titleMedium,
                color = PurpleGrey40
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Search",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )

    }

}

@Composable
fun ChipSection(chips: List<String>) {
    var selectedchipIndex by remember {
        mutableStateOf(0)
    }

    LazyRow {
       items(chips.size){
           Box(
               contentAlignment = Alignment.Center,
               modifier = Modifier
                   .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                   .clickable {
                       selectedchipIndex = it;
                   }
                   .clip(RoundedCornerShape(10.dp))
                   .background(
                       if (selectedchipIndex == it) ButtonBlue
                       else DarkerButtonBlue
                   )
                   .padding(15.dp)
           ){
                Text(text = chips[it], color = Color.White)
           }
       }  
    }
}

@Composable
fun DailyMediation(modifier: Modifier = Modifier, size: Dp = 50.dp) {
    BoxWithConstraints (
        modifier = Modifier
        .height(150.dp)
        .padding(15.dp)
        .clip(RoundedCornerShape(35.dp))
        .background(Color(0xFFFC8798))
        .fillMaxWidth(),
    ){

        val width = constraints.maxWidth
        val height = constraints.maxHeight

        val mediumColouredPoint1 = Offset(width * 0.19f, 0f)
        val mediumColouredPoint2 = Offset(width * 0.2f, 0f)
        val mediumColouredPoint3 = Offset(width * 0.1f, height * 0.29f)
        val mediumColouredPoint4 = Offset(width * 0.15f, height * 0.5f)
        val mediumColouredPoint5 = Offset(width * 0.22f, height * 0.625f)
        val mediumColouredPoint6 = Offset(width * 0.2f, height * 0.71f)
        val mediumColouredPoint7 = Offset(width * 0.25f, height * 1f)
        val mediumColouredPoint8 = Offset(width * 0.3f, height * 1.25f)
        val mediumColouredPoint9 = Offset(width * 0.52f, height * 1f)
        val mediumColouredPoint10 = Offset(width * 0.53f, height * 0.7f)
        val mediumColouredPoint11 = Offset(width * 0.57f, height * 0.63f)
        val mediumColouredPoint12 = Offset(width * 0.65f, height * 0.72f)
        val mediumColouredPoint13 = Offset(width * 0.7f, height * 0.57f)
        val mediumColouredPoint14 = Offset(width * 0.752f, height * 0.72f)
        val mediumColouredPoint15 = Offset(width * 0.748f, height * 1f)
        val mediumColouredPoint16 = Offset(width * 1.2f, height * 1.2f)
        val mediumColouredPoint17 = Offset(width * 1f, height * 0.5f)
        val mediumColouredPoint18 = Offset(width * 0.9f, height * 0.49f)
        val mediumColouredPoint19 = Offset(width * 0.85f, height * 0.20f)
        val mediumColouredPoint20 = Offset(width * 0.62f, height * 0.60f)
        val mediumColouredPoint21 = Offset(width * 0.58f, height * 0.40f)
        val mediumColouredPoint22 = Offset(width * 0.45f, height * 0.29f)
        val mediumColouredPoint23 = Offset(width * 0.50f, 0f)
        val mediumColouredPoint24 = Offset(width * 0.35f, 0f)

        val mediumColoredPath = Path().apply {
            moveTo(mediumColouredPoint1.x, mediumColouredPoint1.y)
            standardQuadFromTo(mediumColouredPoint1, mediumColouredPoint2)
            standardQuadFromTo(mediumColouredPoint2, mediumColouredPoint3)
            standardQuadFromTo(mediumColouredPoint3, mediumColouredPoint4)
            standardQuadFromTo(mediumColouredPoint4, mediumColouredPoint5)
            standardQuadFromTo(mediumColouredPoint5, mediumColouredPoint6)
            standardQuadFromTo(mediumColouredPoint6, mediumColouredPoint7)
            standardQuadFromTo(mediumColouredPoint7, mediumColouredPoint8)
            standardQuadFromTo(mediumColouredPoint8, mediumColouredPoint9)
            standardQuadFromTo(mediumColouredPoint9, mediumColouredPoint10)
            standardQuadFromTo(mediumColouredPoint10, mediumColouredPoint11)
            standardQuadFromTo(mediumColouredPoint11, mediumColouredPoint12)
            standardQuadFromTo(mediumColouredPoint12, mediumColouredPoint13)
            standardQuadFromTo(mediumColouredPoint13, mediumColouredPoint14)
            standardQuadFromTo(mediumColouredPoint14, mediumColouredPoint15)
            standardQuadFromTo(mediumColouredPoint15, mediumColouredPoint16)
            standardQuadFromTo(mediumColouredPoint16, mediumColouredPoint17)
            standardQuadFromTo(mediumColouredPoint17, mediumColouredPoint18)
            standardQuadFromTo(mediumColouredPoint18, mediumColouredPoint19)
            standardQuadFromTo(mediumColouredPoint19, mediumColouredPoint20)
            standardQuadFromTo(mediumColouredPoint20, mediumColouredPoint21)
            standardQuadFromTo(mediumColouredPoint21, mediumColouredPoint22)
            standardQuadFromTo(mediumColouredPoint22, mediumColouredPoint23)
            standardQuadFromTo(mediumColouredPoint23, mediumColouredPoint24)
            close()
        }
        Canvas(modifier = Modifier.fillMaxSize()){
            drawPath(
                path = mediumColoredPath,
                color = Color(0xFFF87482)
            )
        }




        Box(){
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,

                ){
                Column (
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ){
                    Text(
                        text = "Daily Thought",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                    Text(text = "Meditation  3-10 min",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }

                Box(
                    modifier = Modifier
                        .size(size)
                        .clip(RoundedCornerShape(size))
                        .background(ButtonBlue)
                        .clickable { },
                    contentAlignment = Alignment.Center

                ){
                    Icon(
                        painter = painterResource(id = R.drawable.ic_play),
                        contentDescription = "play",
                        modifier = Modifier.size(20.dp),
                        tint = Color.White,

                        )
                }

            }
        }
    }

}

@Composable
fun FeatureSection (modifier: Modifier = Modifier, features: List<Features>){
    Column (modifier = Modifier.fillMaxWidth()){
        Text(
            text = "Features",
            style = MaterialTheme.typography.headlineMedium,
            color = TextWhite,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .height(43.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(features.size){
                FeatureItem(features = features[it])
            }
        }

    }
}

@Composable
fun FeatureItem(features: Features) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(0.9f)
            .clip(RoundedCornerShape(10.dp))
            .background(features.darkColor)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        val mediumColouredPoint1 = Offset(0f, height * 0.3f)
        val mediumColouredPoint2 = Offset(width * 0.1f, height * 0.35f)
        val mediumColouredPoint3 = Offset(width * 0.4f, height * 0.05f)
        val mediumColouredPoint4 = Offset(width * 0.75f, height * 0.7f)
        val mediumColouredPoint5 = Offset(width * 1.4f, -height.toFloat())

        val mediumColoredPath = Path().apply {
            moveTo(mediumColouredPoint1.x, mediumColouredPoint1.y)
            standardQuadFromTo(mediumColouredPoint1, mediumColouredPoint2)
            standardQuadFromTo(mediumColouredPoint2, mediumColouredPoint3)
            standardQuadFromTo(mediumColouredPoint3, mediumColouredPoint4)
            standardQuadFromTo(mediumColouredPoint4, mediumColouredPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        // Light colored path
        val lightPoint1 = Offset(0f, height * 0.35f)
        val lightPoint2 = Offset(width * 0.1f, height * 0.4f)
        val lightPoint3 = Offset(width * 0.3f, height * 0.35f)
        val lightPoint4 = Offset(width * 0.65f, height.toFloat())
        val lightPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

        val lightColoredPath = Path().apply {
            moveTo(lightPoint1.x, lightPoint1.y)
            standardQuadFromTo(lightPoint1, lightPoint2)
            standardQuadFromTo(lightPoint2, lightPoint3)
            standardQuadFromTo(lightPoint3, lightPoint4)
            standardQuadFromTo(lightPoint4, lightPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }
        
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawPath(
                path = mediumColoredPath,
                color = features.mediumColor
            )
            drawPath(
                path = lightColoredPath,
                color = features.lightColor
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ){
            Text(
                text = features.title,
                color = TextWhite,
                style = MaterialTheme.typography.headlineMedium,
                lineHeight = 26.sp,
                modifier = Modifier.align(Alignment.TopStart),
            )
            Icon(painter = painterResource(id = features.iconID),
                contentDescription = features.title,
                tint = Color.White,
                modifier = Modifier.align(Alignment.BottomStart)
            )

            Text(text = "Start",
                color = TextWhite,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable { }
                    .align(Alignment.BottomEnd)
                    .clip(RoundedCornerShape(20.dp))
                    .background(ButtonBlue)
                    .padding(horizontal = 15.dp, vertical = 6.dp)
            )
        }
    }
}
