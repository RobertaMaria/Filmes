package com.roberta.filmes.di.modules

import com.roberta.filmes.BuildConfig
import com.roberta.filmes.adapter.ListaFilmesAdapter
import com.roberta.filmes.dao.DetalhesFilmeDao
import com.roberta.filmes.dao.FilmesDao
import com.roberta.filmes.database.FilmesDataBase
import com.roberta.filmes.repository.FilmesRepository
import com.roberta.filmes.repository.FilmesRepositoryImp
import com.roberta.filmes.retrofit.ConfiguraRetrofit
import com.roberta.filmes.retrofit.FilmesPagingSource
import com.roberta.filmes.retrofit.service.FilmesService
import com.roberta.filmes.ui.viewmodel.DetalhesFilmesViewModel
import com.roberta.filmes.ui.viewmodel.ListaFilmesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModules = module {
    single<FilmesDataBase> {
        FilmesDataBase.getInstance(get())
    }
    single<FilmesService> {
        ConfiguraRetrofit().getFilmesService()
    }
    single<FilmesDao> {
        get<FilmesDataBase>().filmesDao
    }
    single<DetalhesFilmeDao> {
        get<FilmesDataBase>().detalhesFilmeDao
    }
    single<FilmesRepository> {
        FilmesRepositoryImp(service = get(), detalhesDao = get(), chaveApi = get(), pagingSource = get())
    }
    single<String> {
        BuildConfig.CHAVE
    }
    single<FilmesPagingSource> {
        FilmesPagingSource(service = get(), chaveApi = get())
    }

    factory<ListaFilmesAdapter> {
        ListaFilmesAdapter()
    }
    viewModel<ListaFilmesViewModel> {
        ListaFilmesViewModel(repository = get())
    }
    viewModel<DetalhesFilmesViewModel> {(id : Int)->
        DetalhesFilmesViewModel(repository = get(), filmeId = id)
    }

}