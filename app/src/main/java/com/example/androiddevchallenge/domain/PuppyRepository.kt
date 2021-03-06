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
package com.example.androiddevchallenge.domain

import com.example.androiddevchallenge.R
import kotlin.random.Random

class PuppyRepository {
    companion object {
        val INSTANCE = PuppyRepository()
    }

    private val cache: Map<PuppyId, Puppy> =
        (1 until 100)
            .map { puppy() }
            .associateBy { it.id }

    suspend fun findAll(): List<Puppy> = cache.values.toList()

    suspend fun find(id: PuppyId): Puppy = cache[id]!!
}

private fun puppy(): Puppy = Puppy(
    id = PuppyId(Random.Default.nextInt().toString()),
    name = nameCandidates.random() + (1 until 100).random().toString(),
    breed = typeCandidates.random(),
    description = descriptionCandidates.random(),
    imageResId = imageResCandidates.random(),
    hasApply = Random.Default.nextFloat() <= 0.2
)

private val nameCandidates = listOf(
    "Pochi",
    "Taro",
    "bob",
)

private val typeCandidates = listOf(
    "bulldog",
    "Chihuahua",
    "Pomeranian",
    "beagle",
    "Yorkshire terrier",
)

private val descriptionCandidates = listOf(
    "Love like a shadow flies when substance love pursues; Pursuing that that flies, and flying what pursues.",
    "Women are as roses, whose fairflower being once displayed, doth fall that very hour.",
    "Tomorrow, and tomorrow, and tomorrow.Creeps in this petty pace from day to day. To the last syllable of recorded time.",
    "There are more things in heaven and earth,Horatio Than are dreamt of in your philosophy.",
    "There is a tide in the affairs of men. Which, taken at the flood, leads on to fortune; Omitted, all the voyage of their life. Is bound in shallows and in miseries."
)

private val imageResCandidates = listOf(
    R.drawable.dog1,
    R.drawable.dog2,
    R.drawable.dog3,
    R.drawable.dog4,
    R.drawable.dog5,
    R.drawable.dog6,
)
