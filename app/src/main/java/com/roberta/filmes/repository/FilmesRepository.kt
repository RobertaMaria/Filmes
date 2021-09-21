package com.roberta.filmes.repository

import androidx.paging.PagingData
import com.roberta.filmes.model.Filmes
import com.roberta.filmes.model.RetornoDetalhesFilme
import kotlinx.coroutines.flow.Flow

interface FilmesRepository {

    fun buscaFilmesApi() : Flow<PagingData<Filmes>>

    fun buscaDetalhes(quandoSucesso: (RetornoDetalhesFilme)-> Unit, quandoFalha: (String)->Unit, id: Long)

    fun buscaDetalhesInterno(filmeId: Long): RetornoDetalhesFilme?

    fun salvaDetalhesInterno(detalhesFilme: RetornoDetalhesFilme)
}