package com.roberta.filmes.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.roberta.filmes.repository.FilmesRepository
import com.roberta.filmes.repository.Resource

class DetalhesFilmesViewModel(private val repository: FilmesRepository, private val filmeId: Int) :
    ViewModel() {

    fun buscaDetalhesFilme(): LiveData<Resource> {

        val mutableLiveData = MutableLiveData<Resource>()

        repository.buscaDetalhes(quandoSucesso = {
            mutableLiveData.postValue(Resource(detalhes = it))
        }, quandoFalha = {
            mutableLiveData.postValue(Resource(mensagem = it))
        }, filmeId)
        return mutableLiveData
    }
}