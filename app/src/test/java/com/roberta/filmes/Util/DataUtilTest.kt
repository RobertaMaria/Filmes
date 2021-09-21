package com.roberta.filmes.Util

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.hamcrest.CoreMatchers.`is`


class DataUtilTest{

    @Test
     fun Deve_devolverDataFormatada_QunadoRecebeData(){

        val dataDevolvida = DataUtil.formataData("2021-09-20")

        assertThat(dataDevolvida, `is`(equalTo("20/09/2021")))
    }
}