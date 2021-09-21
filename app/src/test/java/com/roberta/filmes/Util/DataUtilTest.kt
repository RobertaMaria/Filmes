package com.roberta.filmes.Util

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.hamcrest.CoreMatchers.`is`


class DataUtilTest{

    @Test
     fun deve_DevolverDataFormatada_QunadoRecebeData(){

        val dataDevolvida = DataUtil.formataData("2021-09-20")

        assertThat(dataDevolvida, `is`(equalTo("20/09/2021")))
    }

    @Test
    fun deve_DevolverStringVazia_QuandoRecebeStringVazia(){

        val dataRetornada = DataUtil.formataData("")

        assertThat(dataRetornada, `is`(equalTo("")))
    }

    @Test
    fun deve_DevolverStringVazia_QuandoRecebeNull() {

        val formataData = DataUtil.formataData(null)

        assertThat(formataData, `is`(equalTo("")))
    }
}