package com.roberta.filmes.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.roberta.filmes.dao.DetalhesFilmeDao
import com.roberta.filmes.model.Filmes
import com.roberta.filmes.model.RetornoDetalhesFilme
import com.roberta.filmes.retrofit.FilmesPagingSource
import com.roberta.filmes.retrofit.service.FilmesService
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmesRepositoryImp(
    private val service: FilmesService,
    private val chaveApi: String,
    private val pagingSource: FilmesPagingSource,
    private val detalhesFilmeDao: DetalhesFilmeDao
) : FilmesRepository {

    override fun buscaFilmesApi(): Flow<PagingData<Filmes>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 60,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { pagingSource }
        ).flow
    }

    override fun buscaDetalhes(
        quandoSucesso: (RetornoDetalhesFilme) -> Unit,
        quandoFalha: (String) -> Unit,
        id: Int
    ) {
        val call = service.buscaDetalhesFilmesApi(id, chaveApi)
        call.enqueue(object : Callback<RetornoDetalhesFilme> {
            override fun onResponse(
                call: Call<RetornoDetalhesFilme>,
                response: Response<RetornoDetalhesFilme>
            ) {
                if (response.isSuccessful) {
                    val resultado = response.body()
                    if (resultado != null) {
                        quandoSucesso(resultado)
                    } else {
                        quandoFalha("Resposta mal sucedida")
                    }
                }
            }

            override fun onFailure(call: Call<RetornoDetalhesFilme>, t: Throwable) {
                quandoFalha("Falha na comunicação" + t.message)
            }

        })
    }

    override fun buscaDetalhesInterno(filmeId: Int): RetornoDetalhesFilme? {
        return detalhesFilmeDao.buscaDetalhesInterno(filmeId)
    }

    override fun salvaDetalhesInterno(detalhesFilme: RetornoDetalhesFilme) {
        detalhesFilmeDao.salvaDetalhesInterno(detalhesFilme)
    }
}