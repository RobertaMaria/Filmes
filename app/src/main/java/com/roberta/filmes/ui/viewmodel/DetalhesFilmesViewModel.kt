package com.roberta.filmes.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roberta.filmes.model.RetornoDetalhesFilme
import com.roberta.filmes.repository.FilmesRepository
import com.roberta.filmes.repository.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetalhesFilmesViewModel(private val repository: FilmesRepository,
                              private val filmeId: Int) : ViewModel() {

    fun buscaDetalhesFilmeInterno(): LiveData<RetornoDetalhesFilme> {
        val mutableLiveData = MutableLiveData<RetornoDetalhesFilme>()
        viewModelScope.launch {
            val detalhesFilme = withContext(IO) {
                repository.buscaDetalhesInterno(filmeId)
            }
            mutableLiveData.value = detalhesFilme
        }
        return mutableLiveData
    }

    fun buscaDetalhesFilmeApi(): LiveData<Resource> {
        val mutableLiveData = MutableLiveData<Resource>()

        repository.buscaDetalhes(quandoSucesso = {
            mutableLiveData.postValue(Resource(detalhes = it))
        }, quandoFalha = {
            mutableLiveData.postValue(Resource(mensagem = it))
        }, filmeId)
        return mutableLiveData
    }

    fun salvaDetalhesFilmeInterno(detalhesFilme: RetornoDetalhesFilme) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.salvaDetalhesInterno(detalhesFilme)
            }
        }
    }
}