package com.roberta.filmes.repository

import androidx.paging.PagingData
import com.roberta.filmes.model.RetornoDetalhesFilme
import com.roberta.filmes.model.Filmes
import kotlinx.coroutines.flow.Flow

interface FilmesRepository {

    fun getFilmesApi() : Flow<PagingData<Filmes>>

    fun buscaDetalhes(quandoSucesso: (RetornoDetalhesFilme)-> Unit, quandoFalha: (String)->Unit, id: Int)
}