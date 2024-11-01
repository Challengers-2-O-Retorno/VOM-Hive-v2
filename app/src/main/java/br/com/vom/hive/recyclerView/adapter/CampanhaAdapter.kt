package br.com.vom.hive.recyclerView.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.vom.hive.R
import br.com.vom.hive.model.Campanha

class CampanhaAdapter(private val campanhas:List<Campanha>) : RecyclerView.Adapter<CampanhaAdapter.CampanhaViewHolder>() {

    class CampanhaViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val nomeCampanha = view.findViewById<TextView>(R.id.nomeCampanha)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CampanhaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_campanha, parent, false)
        return CampanhaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return campanhas.size
    }

    override fun onBindViewHolder(holder: CampanhaViewHolder, position: Int) {
        val campanha = campanhas[position]
        holder.nomeCampanha.text = campanha.nome
    }


}