package com.example.getateam.ui.screeen.Login.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import com.example.getateam.R

@Composable
fun Logo() {
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "로고",
        modifier = Modifier.size(120.dp),
        contentScale = ContentScale.Fit
    )
}