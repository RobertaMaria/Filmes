package com.roberta.filmes.retrofit.service

import com.roberta.filmes.model.GeneroFilme

class RetornoDetalhesFilmeApi(val adult: Boolean,
                              val backdrop_path: String,
                              val budget: Int,
                              val genres: List<GeneroFilme>,
                              val homepage: String,
                              val id: Int,
                              val imdb_id: String,
                              val original_language: String,
                              val original_title: String,
                              val overview: String)