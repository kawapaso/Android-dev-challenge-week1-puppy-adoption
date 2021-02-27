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

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.domain.Puppy
import com.example.androiddevchallenge.domain.PuppyId
import com.example.androiddevchallenge.ui.router.localRouter
import com.example.androiddevchallenge.ui.theme.MyTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PuppyListPage(viewModel: PuppyListViewModel = viewModel()) {
    val uiState: PuppyListUiState by viewModel.uiState.collectAsState()
    val localRouter = localRouter.current
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Puppy List") }) }
    ) {
        Column {
            LazyVerticalGrid(GridCells.Fixed(2)) {
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
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Column(
            modifier = Modifier
                .clickable { onClick(puppy) }
                .fillMaxWidth(),
        ) {
            Image(
                painterResource(id = puppy.imageResId),
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
            )
            Text(
                text = puppy.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp, start = 16.dp),
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = puppy.breed,
                    fontSize = 12.sp,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.padding(top = 8.dp, start = 16.dp),
                )
                if (puppy.hasNego) {
                    Text(
                        text = "Applying",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .border(
                                1.dp,
                                color = MaterialTheme.colors.primary,
                                shape = MaterialTheme.shapes.small
                            )
                            .padding(4.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyTheme {
        PuppyListPage()
    }
}

@Preview(showBackground = true)
@Composable
fun CellPreview() {
    MyTheme {
        PuppyListCell(
            Puppy(id = PuppyId(""), "", "", "", R.drawable.dog1, true),
            {}
        )
    }
}
