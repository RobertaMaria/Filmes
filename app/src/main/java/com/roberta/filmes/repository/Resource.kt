package com.roberta.filmes.repository

import com.roberta.filmes.model.RetornoDetalhesFilme

class Resource(var detalhes: RetornoDetalhesFilme? = null,
               var mensagem: String? = null)
