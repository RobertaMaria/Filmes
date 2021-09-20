package com.roberta.filmes.model

import androidx.room.*

@Entity
data class Filmes(
    val backdrop_path: String,
    @PrimaryKey
    val id: Int,
    val original_language: String,
    val overview: String,
    val release_date: String,
    val title: String
)
