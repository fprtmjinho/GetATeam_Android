package com.example.getateam.ui.screeen.Login

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.getateam.ui.screeen.Login.component.*

@Preview
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = viewModel()
) {
    val state by viewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Logo()

        Spacer(modifier = Modifier.height(32.dp))

        LoginTextField(
            label = "아이디",
            value = state.id,
            onValueChange = viewModel::onIdChange
        )

        Spacer(modifier = Modifier.height(12.dp))

        LoginTextField(
            label = "비밀번호",
            value = state.password,
            onValueChange = viewModel::onPasswordChange,
            isPassword = true
        )

        Spacer(modifier = Modifier.height(20.dp))

        LoginButton(onClick = viewModel::login)

        Spacer(modifier = Modifier.height(24.dp))

        BottomMenu()
    }
}