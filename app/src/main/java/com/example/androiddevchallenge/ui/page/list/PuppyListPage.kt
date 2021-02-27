/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.page.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.domain.Puppy
import com.example.androiddevchallenge.ui.router.localRouter
import com.example.androiddevchallenge.ui.theme.MyTheme

@Composable
fun PuppyListPage(viewModel: PuppyListViewModel = viewModel()) {
    val uiState: PuppyListUiState by viewModel.uiState.collectAsState()
    val localRouter = localRouter.current
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Puppy List") }) }
    ) {
        Column {
            LazyColumn {
                items(uiState.puppys) { puppy ->
                    PuppyListCell(
                        puppy,
                        onClick = {
                            localRouter.toDetail(puppy)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun PuppyListCell(puppy: Puppy, onClick: (Puppy) -> Unit) {
    Row(
        modifier = Modifier
            .clickable { onClick(puppy) }
            .padding(4.dp)
            .height(60.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painterResource(id = puppy.imageResId),
            contentDescription = null,
            modifier = Modifier.aspectRatio(1f, matchHeightConstraintsFirst = true)
        )
        Text(
            text = puppy.name,
            fontSize = 14.sp,
            modifier = Modifier.padding(start = 16.dp),
        )
        Text(
            text = puppy.breed,
            fontSize = 14.sp,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(start = 16.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyTheme {
        PuppyListPage()
    }
}
