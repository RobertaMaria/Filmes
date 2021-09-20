package com.roberta.filmes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.roberta.filmes.R
import com.roberta.filmes.Util.DataUtil
import com.roberta.filmes.model.Filmes

class ListaFilmesAdapter :
    PagingDataAdapter<Filmes, ListaFilmesAdapter.ListaFilmesViewHolder>(FILM_COMPARATOR) {

    var onItemClickListener: (filme: Filmes) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaFilmesViewHolder {
        val viewCriada =
            LayoutInflater.from(parent.context).inflate(R.layout.item_filmes, parent, false)
        return ListaFilmesViewHolder(viewCriada)
    }

    override fun onBindViewHolder(holder: ListaFilmesViewHolder, position: Int) {
        holder.vincula(getItem(position)!!)
    }

    inner class ListaFilmesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var filme: Filmes
        private val campoImagem by lazy { itemView.findViewById<ImageView>(R.id.item_filme_imagem) }
        private val campoDataLancamento by lazy { itemView.findViewById<TextView>(R.id.item_filme_data) }
        private val campoTituo by lazy { itemView.findViewById<TextView>(R.id.item_filme_nome) }

        init {
            itemView.setOnClickListener {
                if (::filme.isInitialized) {
                    onItemClickListener(filme)
                }
            }
        }

        fun vincula(filme: Filmes) {
            this.filme = filme
            mostraTitulo(filme.title)
            mostraDataLancamento(filme.release_date)
            mostraImagem(filme.backdrop_path)
        }

        private fun mostraImagem(imagem: String) {
            Glide.with(campoImagem)
                .load(campoImagem.context.resources.getString(R.string.url_imagem, imagem))
                .into(campoImagem)
        }

        private fun mostraDataLancamento(lancamento: String) {
            campoDataLancamento.text = DataUtil.formataData(lancamento)
        }

        private fun mostraTitulo(titulo: String) {
            campoTituo.text = titulo
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
