package com.roberta.filmes.retrofit.service

import com.roberta.filmes.retrofit.RetornoFilmesApi
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmesService {

    @GET("discover/movie")
    suspend fun buscaTodosFilmes(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): RetornoFilmesApi
}
