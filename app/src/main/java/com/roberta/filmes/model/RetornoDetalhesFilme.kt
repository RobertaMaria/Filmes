package com.roberta.filmes.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RetornoDetalhesFilme(val genres: List<Genero>,
                           @PrimaryKey
                           val id: Int,
                           val original_language: String,
                           val overview: String,
                           val poster_path: String,
                           val release_date: String,
                           val title: String )
