package com.roberta.filmes.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.roberta.filmes.model.Genero

class ConverterListGenero {

    @TypeConverter
    fun listaParaJson(generos: List<Genero>): String {
        return Gson().toJson(generos)
    }
    @TypeConverter
    fun jsonParaLista(json: String): List<Genero> {
        val itemType = object : TypeToken<List<Genero>>() {}.type
        return Gson().fromJson<List<Genero>>(json, itemType)
    }
}