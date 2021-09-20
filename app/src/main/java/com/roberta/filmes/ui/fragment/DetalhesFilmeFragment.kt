package com.roberta.filmes.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.roberta.filmes.R
import com.roberta.filmes.Util.DataUtil
import com.roberta.filmes.dao.DetalhesFilmeDao
import com.roberta.filmes.model.Genero
import com.roberta.filmes.ui.viewmodel.DetalhesFilmesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetalhesFilmeFragment(private val detalhesFilmeDao: DetalhesFilmeDao) : Fragment() {

    private lateinit var campoGenero: TextView
    private lateinit var mostraDescricao: TextView
    private lateinit var campoIdioma: TextView
    private lateinit var campoDataLancamento: TextView
    private lateinit var campoImagem: ImageView
    private lateinit var campoTitulo: CollapsingToolbarLayout
    private lateinit var progressBar: ProgressBar
    private val argumentos by navArgs<DetalhesFilmeFragmentArgs>()
    private val filmeId by lazy {
        argumentos.filmeId
    }
    private val viewModel: DetalhesFilmesViewModel by viewModel { parametersOf(filmeId) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detalhes_filme, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inicializaCampos(view)
        buscaFilme()
    }

    private fun buscaFilme() {
        viewModel.buscaDetalhesFilme().observe(viewLifecycleOwner) {
            it?.let {
                progressBar.visibility = GONE
                it.detalhes?.let { detalhesFilme ->

                    mostraTitulo(campoTitulo, detalhesFilme.title)
                    mostraImagem(campoImagem, detalhesFilme.poster_path)
                    mostraDataLancamento(campoDataLancamento, detalhesFilme.release_date)
                    mostraIdioma(campoIdioma, detalhesFilme.original_language)
                    mostraDescricao(mostraDescricao, detalhesFilme.overview)
                    mostraGenero(campoGenero, detalhesFilme.genres)
                }
            }
        }
    }

    private fun mostraGenero(
        campoGenero: TextView,
        generosLista: List<Genero>
    ) {
        campoGenero.apply {
            var generos = ""
            generosLista.forEach { genero ->
                generos += "${genero.name}, "
            }
            if (generos.isNotBlank())
                text = generos.substring(0, generos.count().minus(3))
        }
    }

    private fun mostraDescricao(
        mostraDescricao: TextView,
        descricao: String
    ) {
        mostraDescricao.text = descricao
    }

    private fun mostraIdioma(
        campoIdioma: TextView,
        idioma: String
    ) {
        campoIdioma.text = idioma
    }

    private fun mostraDataLancamento(
        campoDataLancamento: TextView,
        dataLancamento: String
    ) {
        campoDataLancamento.text = DataUtil.formataData(dataLancamento)
    }

    private fun mostraImagem(
        campoImagem: ImageView,
        imagem: String
    ) {
        Glide.with(campoImagem)
            .load(
                campoImagem.context.resources.getString(
                    R.string.url_imagem,
                    imagem
                )
            )
            .into(campoImagem)
    }

    private fun mostraTitulo(
        campoTitulo: CollapsingToolbarLayout,
        titulo: String
    ) {
        campoTitulo.title = titulo
    }

    private fun inicializaCampos(view: View) {
        campoTitulo = view.findViewById(R.id.detalhes_filme_collapsing)
        campoImagem = view.findViewById(R.id.detalhes_filme_imagem)
        campoDataLancamento = view.findViewById(R.id.detalhes_filme_lancamento_valor)
        campoIdioma = view.findViewById(R.id.detalhes_filme_idioma_valor)
        mostraDescricao = view.findViewById(R.id.detalhes_filme_descricao_valor)
        campoGenero = view.findViewById(R.id.detalhes_filme_genero_valor)
        progressBar = view.findViewById(R.id.detalhes_filme_progressBar)
    }
}