package com.roberta.filmes.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.roberta.filmes.model.Filmes
import com.roberta.filmes.repository.FilmesRepository
import kotlinx.coroutines.flow.Flow

class ListaFilmesViewModel(private val repository: FilmesRepository): ViewModel() {

    fun buscaFilmes(): Flow<PagingData<Filmes>>{
        return repository.buscaFilmesApi().cachedIn(viewModelScope)
    }

}