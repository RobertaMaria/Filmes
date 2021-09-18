package com.roberta.filmes.di.modules

import com.roberta.filmes.BuildConfig
import com.roberta.filmes.adapter.ListaFilmesAdapter
import com.roberta.filmes.dao.FilmesDao
import com.roberta.filmes.database.FilmesDataBase
import com.roberta.filmes.repository.FilmesRepositoryImp
import com.roberta.filmes.retrofit.ConfiguraRetrofit
import com.roberta.filmes.retrofit.FilmesPagingSource
import com.roberta.filmes.retrofit.GithubRemoteMediator
import com.roberta.filmes.retrofit.service.FilmesService
import com.roberta.filmes.ui.viewmodel.ListaFilmesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    single<FilmesDataBase> {
        FilmesDataBase.getInstance(get())
    }
    single<FilmesPagingSource> {
        FilmesPagingSource(service = get(), chaveApi = BuildConfig.CHAVE)
    }
    single<FilmesService> {
        ConfiguraRetrofit().getFilmesService()
    }
    single<FilmesDao> {
        get<FilmesDataBase>().filmesDao
    }
    single<FilmesRepositoryImp> {
        FilmesRepositoryImp(service = get(), dao = get(), pagingSource = get())
    }
    single<GithubRemoteMediator> {
        GithubRemoteMediator(service = get(), dataBase = get())
    }
    factory<ListaFilmesAdapter> {
        ListaFilmesAdapter()
    }
    viewModel<ListaFilmesViewModel> {
        ListaFilmesViewModel(repository = get())
    }

}