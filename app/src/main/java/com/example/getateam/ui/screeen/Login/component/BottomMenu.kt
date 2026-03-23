package com.example.getateam.ui.screeen.Login.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BottomMenu() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text("아이디찾기")
        Spacer(modifier = Modifier.width(8.dp))
        Text("|")
        Spacer(modifier = Modifier.width(8.dp))
        Text("비밀번호찾기")
        Spacer(modifier = Modifier.width(8.dp))
        Text("|")
        Spacer(modifier = Modifier.width(8.dp))
        Text("회원가입")
    }
}