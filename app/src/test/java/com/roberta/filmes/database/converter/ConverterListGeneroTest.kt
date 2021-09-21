package com.roberta.filmes.database.converter

import com.roberta.filmes.model.Genero
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class ConverterListGeneroTest {
    val converssor = ConverterListGenero()

    @Test
    fun deve_DevolverJson_QuandoRecebeList() {
        val jsonDevolvido = converssor.listaParaJson(
            ArrayList<Genero>(
                listOf(
                    Genero(28, "Action"),
                    Genero(12, "Adventure"),
                    Genero(14, "Fantasy")
                )
            )
        )

        assertThat(jsonDevolvido, `is`(equalTo(JSON)))
    }

    @Test
    fun deve_DevolverList_QuandoRecebeJson() {
        val listaDevolvida = converssor.jsonParaLista(JSON)

        assertThat(
            listaDevolvida, `is`(
                equalTo(
                    ArrayList<Genero>(
                        listOf(
                            Genero(28, "Action"),
                            Genero(12, "Adventure"),
                            Genero(14, "Fantasy")
                        )
                    )
                )
            )
        )
    }

    companion object {

        val JSON = "[{\"id\":28,\"name\":\"Action\"}," +
                "{\"id\":12,\"name\":\"Adventure\"}," +
                "{\"id\":14,\"name\":\"Fantasy\"}]"
    }

}