package com.roberta.filmes.model

import androidx.room.*

@Entity
data class Filmes(
    val backdrop_path: String,
    //@Ignore
    //val genre_ids: List<Int> = arrayListOf(),
    //@TypeConverters(ConversorLista::class)
    //var genre_names: ArrayList<String>,
    @PrimaryKey
    val id: Int,
    val original_language: String,
    val overview: String,
    val release_date: String,
    val title: String
)
