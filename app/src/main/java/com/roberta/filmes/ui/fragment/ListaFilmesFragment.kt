package com.roberta.filmes.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.roberta.filmes.R
import com.roberta.filmes.adapter.ListaFilmesAdapter
import com.roberta.filmes.ui.viewmodel.ListaFilmesViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListaFilmesFragment : Fragment() {

    private val viewModel: ListaFilmesViewModel by viewModel()
    private val adapter: ListaFilmesAdapter by inject()
    private lateinit var progressBar: ProgressBar
    private lateinit var includeTelaErro: View
    private val controlador by lazy {
        findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buscaFilmes()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.lista_filmes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraRecyclerView(view)
        configuraProgressBar(view)
        observaCarregamentoDaTela()
        configuraBotaoTentarNovamente(view)
    }

    private fun configuraBotaoTentarNovamente(view: View) {
        val botaoTentarNovamente = view.findViewById<Button>(R.id.erro_aquisicao_lista_botao)
        botaoTentarNovamente.setOnClickListener {
            buscaFilmes()
        }
    }

    private fun configuraProgressBar(view: View) {
        progressBar = view.findViewById((R.id.lista_filmes_progessbar))
        includeTelaErro = view.findViewById(R.id.tela_erro)
    }

    private fun buscaFilmes() {
        lifecycleScope.launchWhenCreated {
            viewModel.getFilmes().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun observaCarregamentoDaTela() {
        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { statusDeCarregamento ->
                progressBar.isVisible = statusDeCarregamento.refresh is LoadState.Loading
                includeTelaErro.isVisible = statusDeCarregamento.refresh is LoadState.Error
            }
        }
    }

    private fun configuraRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.lista_filmes_recyclerview)
        adapter.onItemClickListener = {
            vaiParaDetalhesFilme(it.id)
        }
        recyclerView.adapter = adapter
    }

    private fun vaiParaDetalhesFilme(id: Int) {
        val direcao = ListaFilmesFragmentDirections.acaoListaFilmesParaDetalhesFilmes(id)
        controlador.navigate(direcao)
    }

}