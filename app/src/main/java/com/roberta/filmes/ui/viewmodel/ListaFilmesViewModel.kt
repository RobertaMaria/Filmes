package com.roberta.filmes.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.roberta.filmes.model.Filmes
import com.roberta.filmes.repository.FilmesRepository
import com.roberta.filmes.retrofit.FilmesPagingSource
import kotlinx.coroutines.flow.Flow

class ListaFilmesViewModel(private val repository: FilmesRepository): ViewModel() {

    fun getFilmes(): Flow<PagingData<Filmes>>{
        return repository.getFilmesApi().cachedIn(viewModelScope)
    }

}