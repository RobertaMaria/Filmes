package com.roberta.filmes.repository

import androidx.paging.PagingData
import com.roberta.filmes.model.Filmes
import com.roberta.filmes.retrofit.RetornoFilmesApi
import kotlinx.coroutines.flow.Flow

interface FilmesRepository {
    fun getFilmesApi() : Flow<PagingData<Filmes>>
}