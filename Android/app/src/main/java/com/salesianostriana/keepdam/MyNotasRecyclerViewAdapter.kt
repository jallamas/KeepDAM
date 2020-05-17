package com.salesianostriana.keepdam

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.salesianostriana.keepdam.common.Constantes
import com.salesianostriana.keepdam.common.MyApp
import com.salesianostriana.keepdam.models.Nota

import kotlinx.android.synthetic.main.fragment_notas.view.*


class MyNotasRecyclerViewAdapter() : RecyclerView.Adapter<MyNotasRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener
    private var notas: List<Nota> = ArrayList()

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Nota
            var intent = Intent(MyApp.instance, NotaDetalleActivity::class.java).apply{
                putExtra(Constantes.INTENT_DETAIL_KEY_ID,item.id.toString())
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            MyApp.instance.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_notas, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = notas[position]
        holder.title.text = item.titulo
        holder.fecha.text = item.fechaModificacion
        holder.contenido.text = item.contenido

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = notas.size

    fun setData(notasList: List<Nota>){
        notas = notasList
        notifyDataSetChanged()
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val title: TextView = mView.textViewNotaTitle
        val fecha: TextView = mView.textViewFechaModificado
        val contenido: TextView = mView.TextViewContenido
    }
}
