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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.domain.Puppy
import com.example.androiddevchallenge.domain.PuppyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PuppyListViewModel(
    private val repository: PuppyRepository = PuppyRepository.INSTANCE
) : ViewModel() {
    private val _uiState = MutableStateFlow(PuppyListUiState.DEFAULT)
    val uiState: StateFlow<PuppyListUiState> = _uiState

    init {
        viewModelScope.launch {
            runCatching {
                repository.findAll()
            }.onSuccess {
                val current = uiState.value
                _uiState.emit(current.copy(puppys = it))
            }.onFailure {
                // fixme
            }
        }
    }
}

data class PuppyListUiState(
    val puppys: List<Puppy>
) {
    companion object {
        val DEFAULT = PuppyListUiState(listOf())
    }
}
