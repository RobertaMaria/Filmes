package com.roberta.filmes.Util

import java.text.SimpleDateFormat
import java.util.*


 object DataUtil {

      fun formataData(data: String?): String {

          data?.takeIf { it.isNotEmpty() && it.isNotBlank() }?.let {
                  val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                  val mDate = formatter.parse(it)

                  val formatado = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                  return formatado.format(mDate)
            }

         return ""
     }
 }