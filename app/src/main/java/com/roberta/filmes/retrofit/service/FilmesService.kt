package com.roberta.filmes.retrofit.service

import com.roberta.filmes.model.RetornoDetalhesFilme
import com.roberta.filmes.model.GeneroFilme
import com.roberta.filmes.retrofit.RetornoFilmesApi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmesService {

    @GET("discover/movie")
    suspend fun buscaTodosFilmes(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): RetornoFilmesApi

    @GET("genre/movie/list")
    suspend fun buscaGenero(@Query("api_key") api_key: String): GeneroFilme

    @GET("movie/{id}")
    fun buscaPorId(@Path("id") id: Int,
                   @Query("api_key") api_key: String): Call<RetornoDetalhesFilme>
}
