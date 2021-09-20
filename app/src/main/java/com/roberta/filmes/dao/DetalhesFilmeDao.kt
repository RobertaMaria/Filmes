package com.roberta.filmes.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.roberta.filmes.model.RetornoDetalhesFilme

@Dao
interface DetalhesFilmeDao {

    @Query("SELECT * FROM RetornoDetalhesFilme WHERE id = :filmeId")
    fun buscaDetalhesInterno(filmeId: Int): RetornoDetalhesFilme?

    @Insert
    fun salvaDetalhesInterno(detalhesFilme: RetornoDetalhesFilme)
}