package br.com.vom.hive.recyclerView.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.vom.hive.CampanhaActivity
import br.com.vom.hive.R
import br.com.vom.hive.model.Campanha

class CampanhaAdapter(private val campanhas: List<Campanha>) :
    RecyclerView.Adapter<CampanhaAdapter.CampanhaViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    class CampanhaViewHolder(val view: View, listener: onItemClickListener) :
        RecyclerView.ViewHolder(view) {
        val nomeCampanha = view.findViewById<TextView>(R.id.nomeCampanha)
        val idCampanha = view.findViewById<TextView>(R.id.idCampanha)

        init {
            view.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CampanhaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_campanha, parent, false)
        return CampanhaViewHolder(view, mListener)
    }

    override fun getItemCount(): Int {
        return campanhas.size
    }

    override fun onBindViewHolder(holder: CampanhaViewHolder, position: Int) {
        val campanha = campanhas[position]
        holder.nomeCampanha.text = campanha.name
        holder.idCampanha.text = campanha.id.toString()


    }


}