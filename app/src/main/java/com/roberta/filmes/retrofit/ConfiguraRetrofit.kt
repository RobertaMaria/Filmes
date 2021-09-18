package com.roberta.filmes.retrofit

import com.roberta.filmes.retrofit.service.FilmesService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.themoviedb.org/3/"

class ConfiguraRetrofit {

    private val retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getFilmesService(): FilmesService {
      return  retrofit.create(FilmesService::class.java)
    }
}