package com.roberta.filmes.model


data class Filmes(
    val backdrop_path: String,
    val id: Long,
    val original_language: String,
    val overview: String,
    val release_date: String?,
    val title: String
)
