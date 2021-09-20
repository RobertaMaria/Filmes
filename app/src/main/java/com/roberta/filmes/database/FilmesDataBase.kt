package com.roberta.filmes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.roberta.filmes.dao.DetalhesFilmeDao
import com.roberta.filmes.dao.FilmesDao
import com.roberta.filmes.model.Filmes
import com.roberta.filmes.model.RetornoDetalhesFilme

private const val NOME_BANCO_DE_DADOS = "news.db"

@Database(entities = [Filmes::class, RetornoDetalhesFilme::class], version = 1, exportSchema = false)
abstract class FilmesDataBase : RoomDatabase() {

    abstract val filmesDao: FilmesDao
    abstract val detalhesFilmeDao: DetalhesFilmeDao

    companion object {

        fun getInstance(context: Context): FilmesDataBase {

            return Room.databaseBuilder(
                    context,
                    FilmesDataBase::class.java, NOME_BANCO_DE_DADOS
                ).build()
        }
    }
}