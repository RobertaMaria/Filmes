package com.roberta.filmes.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.roberta.filmes.R
import com.roberta.filmes.adapter.ListaFilmesAdapter
import com.roberta.filmes.ui.viewmodel.ListaFilmesViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListaFilmesFragment : Fragment() {

    private val viewModel: ListaFilmesViewModel by viewModel()
    private val adapter: ListaFilmesAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buscaFilmes()
    }

    private fun buscaFilmes() {
        lifecycleScope.launchWhenCreated {
            viewModel.getFilmes().collectLatest {
                adapter.submitData(it)
            }
        }
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

    }

    private fun configuraRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.lista_filmes_recyclerview)
        recyclerView.adapter = adapter
    }

}