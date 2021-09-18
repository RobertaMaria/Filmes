package com.roberta.filmes.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.roberta.filmes.dao.FilmesDao
import com.roberta.filmes.model.Filmes
import com.roberta.filmes.retrofit.FilmesPagingSource
import com.roberta.filmes.retrofit.RetornoFilmesApi
import com.roberta.filmes.retrofit.service.FilmesService
import kotlinx.coroutines.flow.Flow

class FilmesRepositoryImp(private val service: FilmesService, private val dao: FilmesDao, private val pagingSource: FilmesPagingSource) : FilmesRepository {
    override fun getFilmesApi(): Flow<PagingData<Filmes>> {
        return Pager(
            config = PagingConfig(pageSize = 20, prefetchDistance = 60),
            pagingSourceFactory = {pagingSource}
        ).flow
    }

}