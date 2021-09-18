package com.roberta.filmes.retrofit

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.roberta.filmes.model.Filmes
import com.roberta.filmes.retrofit.service.FilmesService
import java.lang.Exception

class FilmesPagingSource(private val service: FilmesService, private val chaveApi: String): PagingSource<Int, Filmes>() {

    override fun getRefreshKey(state: PagingState<Int, Filmes>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Filmes> {
        return try {
            vericaProximaPaginaOffset(params)

        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    private suspend fun vericaProximaPaginaOffset(params: LoadParams<Int>): LoadResult.Page<Int, Filmes> {
        val page = params.key ?: PAGE_INDEX
        val response = service.buscaTodosFilmes(page = page, api_key = chaveApi)
        val nextPage = if (response.results.isEmpty()) {
            null
        } else {
            page + 1
        }

        return LoadResult.Page(
            data = response.results,
            prevKey = null,
            nextKey = nextPage
        )
    }
    companion object {
        private const val PAGE_INDEX = 1
    }
}