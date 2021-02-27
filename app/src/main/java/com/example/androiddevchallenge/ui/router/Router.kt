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
package com.example.androiddevchallenge.ui.router

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.domain.Puppy
import com.example.androiddevchallenge.domain.PuppyId
import com.example.androiddevchallenge.ui.page.detail.PuppyDetailPage
import com.example.androiddevchallenge.ui.page.detail.PuppyDetailViewModel
import com.example.androiddevchallenge.ui.page.list.PuppyListPage

val localRouter = compositionLocalOf<Router> { Router(null) }

@Composable
fun RouterComponent() {
    val navController = rememberNavController()
    CompositionLocalProvider(localRouter provides Router(navController)) {
        NavHost(navController = navController, startDestination = "list") {
            composable("list") { PuppyListPage() }
            composable(
                "detail/{puppyId}",
                arguments = listOf(navArgument("puppyId") { type = NavType.StringType })
            ) {
                val id = PuppyId(it.arguments?.getString("puppyId")!!)
                val viewModel: PuppyDetailViewModel = viewModel()
                PuppyDetailPage(
                    viewModel = viewModel.apply { setUp(id) }
                )
            }
        }
    }
}

data class Router(val navController: NavController?) {
    fun toDetail(puppy: Puppy) {
        navController?.navigate("detail/${puppy.id.value}")
    }

    fun back() {
        navController?.popBackStack()
    }
}
