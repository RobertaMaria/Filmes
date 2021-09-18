package com.roberta.filmes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.roberta.filmes.R
import com.roberta.filmes.model.Filmes

class ListaFilmesAdapter :
    PagingDataAdapter<Filmes, ListaFilmesAdapter.ListaFilmesViewHolder>(FILM_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaFilmesViewHolder {
        val viewCriada =
            LayoutInflater.from(parent.context).inflate(R.layout.item_filmes, parent, false)
        return ListaFilmesViewHolder(viewCriada)
    }

    override fun onBindViewHolder(holder: ListaFilmesViewHolder, position: Int) {
        holder.vincula(getItem(position)!!)
    }


    inner class ListaFilmesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun vincula(filme: Filmes) {
            val campoNome = itemView.findViewById<TextView>(R.id.item_filme_nome)
            campoNome.text = filme.title
            val campoDataLancamento = itemView.findViewById<TextView>(R.id.item_filme_data)
            campoDataLancamento.text = filme.release_date
        }

    }
}

val FILM_COMPARATOR = object : DiffUtil.ItemCallback<Filmes>() {
    override fun areItemsTheSame(oldItem: Filmes, newItem: Filmes): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Filmes, newItem: Filmes): Boolean {
        return oldItem == newItem
    }

}
