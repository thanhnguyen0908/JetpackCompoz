package com.example.jetpackcompoz.feature.main.home

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun HomeScreen(
    maxSize: Dp = 96.dp,
    minSize: Dp = 0.dp
) {
    var currentSize by remember { mutableStateOf(maxSize) }

    val animatedHeight by animateDpAsState(
        targetValue = currentSize,
        animationSpec = tween(durationMillis = 250, easing = LinearOutSlowInEasing),
        label = "storyHeightAnimation"
    )

    val friction = 0.3f     // 👈 shrink speed (smaller = slower)

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val deltaY = available.y
                // Apply friction only when collapsing (scroll up)
                val adjustedDelta = if (deltaY < 0) {
                    deltaY * friction
                } else {
                    deltaY
                }

                val newSize = currentSize + adjustedDelta.dp
                val previous = currentSize

                currentSize = newSize.coerceIn(minSize, maxSize)
                val consumed = currentSize - previous

                return Offset(0f, consumed.value)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            HomeHeader()
            HomePostList(nestedScrollConnection)
        }
        Column {
            Spacer(modifier = Modifier.height(64.dp))
            HomeStoryList(height = animatedHeight)
        }
    }
}

@Composable
fun HomeHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .drawBehind {
                val strokeWidth = 1.dp.toPx()
                val y = size.height - strokeWidth / 2
                drawLine(
                    color = Color.DarkGray,
                    start = Offset(0f, y),
                    end = Offset(size.width, y),
                    strokeWidth = strokeWidth
                )
            }
    ) {
        Icon(
            imageVector = Icons.Filled.AddCircle,
            contentDescription = "AddCircle",
            tint = Color.Black,
            modifier = Modifier
                .padding(16.dp)
                .size(32.dp)


        )
        Spacer(modifier = Modifier.weight(1f))   // takes up all remaining space
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "Search",
            tint = Color.Black,
            modifier = Modifier
                .padding(16.dp)
                .size(32.dp)

        )
    }
}

@Composable
fun HomeStoryList(height: Dp) {
    val items = (1..50).toList()   // or your own data list

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)              // 👈 Apply animated height
            .background(Color.LightGray)
            .clipToBounds(),
        contentPadding = PaddingValues(start = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),  // spacing between items
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items(items) { number ->
            Card(
                modifier = Modifier
                    .requiredSize(80.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(40.dp),
                border = BorderStroke(2.dp, Color.Gray)// 👈 sets the corner radius
            ) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    Text(text = "Item $number")
                }
            }
        }
    }
}

@Composable
fun HomePostList(nestedScrollConnection: NestedScrollConnection) {
    val items = (1..50).toList()   // or your own data list

    Box(Modifier.nestedScroll(nestedScrollConnection)) {
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight(),
            contentPadding = PaddingValues(top = 132.dp),
        ) {
            items(items) { number ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(start = 16.dp, bottom = 8.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Card(
                                modifier = Modifier
                                    .size(width = 40.dp, height = 40.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.White),
                                shape = RoundedCornerShape(20.dp),
                                border = BorderStroke(2.dp, Color.Gray)// 👈 sets the corner radius
                            ) {}
                            Text(text = "Item $number", modifier = Modifier.padding(start = 8.dp))
                        }
                        Box(
                            modifier = Modifier
                                .height(400.dp)
                                .fillMaxWidth()
                                .background(Color.Black)
                        ) {}
                        Row {
                            Icon(
                                imageVector = Icons.Outlined.FavoriteBorder,
                                contentDescription = "FavoriteBorder",
                                tint = Color.Black,
                                modifier = Modifier
                                    .padding(all = 16.dp)
                            )
                            Icon(
                                imageVector = Icons.Outlined.Email,
                                contentDescription = "Email",
                                tint = Color.Black,
                                modifier = Modifier
                                    .padding(top = 16.dp)
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Icon(
                                imageVector = Icons.Outlined.Delete,
                                contentDescription = "Delete",
                                tint = Color.Black,
                                modifier = Modifier
                                    .padding(top = 16.dp, end = 16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
