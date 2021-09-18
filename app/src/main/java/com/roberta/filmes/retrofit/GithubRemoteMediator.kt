package com.roberta.filmes.retrofit

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.roberta.filmes.database.FilmesDataBase
import com.roberta.filmes.model.Filmes
import com.roberta.filmes.retrofit.service.FilmesService

@OptIn(ExperimentalPagingApi::class)
class GithubRemoteMediator(private val service: FilmesService,
                           private val dataBase: FilmesDataBase): RemoteMediator<Int, Filmes>() {
    override suspend fun load(loadType: LoadType, state: PagingState<Int, Filmes>): MediatorResult {
        TODO("Not yet implemented")
    }
}