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
import com.roberta.filmes.model.Genero
import com.roberta.filmes.model.RetornoDetalhesFilme
import com.roberta.filmes.ui.viewmodel.DetalhesFilmesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetalhesFilmeFragment() : Fragment() {

    private lateinit var campoGenero: TextView
    private lateinit var campoDescricao: TextView
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
        viewModel.buscaDetalhesFilmeInterno().observe(viewLifecycleOwner) {
            if (it != null){
                mostraDetalhes(it)
            }else{
                viewModel.buscaDetalhesFilmeApi().observe(viewLifecycleOwner) {
                    it?.let {
                        it.detalhes?.let { detalhesFilme ->
                            viewModel.salvaDetalhesFilmeInterno(detalhesFilme)
                            mostraDetalhes(detalhesFilme)
                        }
                    }
                }
            }
        }

    }

    private fun mostraDetalhes(detalhesFilme: RetornoDetalhesFilme) {
        progressBar.visibility = GONE
        mostraTitulo(detalhesFilme.title)
        mostraImagem(detalhesFilme.poster_path)
        mostraDataLancamento(detalhesFilme.release_date)
        mostraIdioma(detalhesFilme.original_language)
        mostraDescricao(detalhesFilme.overview)
        mostraGenero( detalhesFilme.genres)
    }

    private fun mostraGenero(generosLista: List<Genero>) {
        campoGenero.apply {
            var generos = ""
            generosLista.forEach { genero ->
                generos += "${genero.name}, "
            }
            if (generos.isNotBlank())
                text = generos.substring(0, generos.count().minus(3))
        }
    }

    private fun mostraDescricao(descricao: String) {
        campoDescricao.text = descricao
    }

    private fun mostraIdioma(idioma: String) {
        campoIdioma.text = idioma
    }

    private fun mostraDataLancamento(dataLancamento: String) {
        campoDataLancamento.text = DataUtil.formataData(dataLancamento)
    }

    private fun mostraImagem(imagem: String?) {
        Glide.with(campoImagem)
            .load(
                campoImagem.context.resources.getString(
                    R.string.url_imagem,
                    imagem
                )
            ).placeholder(R.mipmap.carregando)
            .error(R.mipmap.falha)
            .into(campoImagem)
    }

    private fun mostraTitulo(titulo: String) {
        campoTitulo.title = titulo
    }

    private fun inicializaCampos(view: View) {
        campoTitulo = view.findViewById(R.id.detalhes_filme_collapsing)
        campoImagem = view.findViewById(R.id.detalhes_filme_imagem)
        campoDataLancamento = view.findViewById(R.id.detalhes_filme_lancamento_valor)
        campoIdioma = view.findViewById(R.id.detalhes_filme_idioma_valor)
        campoDescricao = view.findViewById(R.id.detalhes_filme_descricao_valor)
        campoGenero = view.findViewById(R.id.detalhes_filme_genero_valor)
        progressBar = view.findViewById(R.id.detalhes_filme_progressBar)
    }
}