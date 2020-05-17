package com.salesianostriana.keepdam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import butterknife.BindView
import butterknife.ButterKnife
import com.salesianostriana.keepdam.common.Constantes
import com.salesianostriana.keepdam.common.MyApp
import com.salesianostriana.keepdam.viewmodel.NotasViewModel
import javax.inject.Inject

class NotaDetalleActivity : AppCompatActivity() {
    @Inject
    lateinit var notasViewModel: NotasViewModel

    @BindView(R.id.textViewDetalleTitle)
    lateinit var titulo: TextView

    @BindView(R.id.textViewDetalleCreacion)
    lateinit var fechaCreacion: TextView

    @BindView(R.id.textViewDetalleModificacion)
    lateinit var fechaModificacion: TextView

    @BindView(R.id.textViewDetalleContenido)
    lateinit var contenido: TextView

    @BindView(R.id.buttonDetalleDelete)
    lateinit var eliminar: Button

    @BindView(R.id.buttonDetalleEditar)
    lateinit var editar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nota_detalle)
        (applicationContext as MyApp).getApplicationComponent().inject(this)
        ButterKnife.bind(this)

        val notaId: String = intent.getStringExtra((Constantes.INTENT_DETAIL_KEY_ID))

        notasViewModel.getNota(notaId).observe(this, Observer {
            titulo.text = it.titulo
            fechaCreacion.text = it.fechaCreacion
            fechaModificacion.text = it.fechaModificacion
            contenido.text = it.contenido
        })

        eliminar.setOnClickListener(View.OnClickListener {
            notasViewModel.deleteNota(notaId)
            val lista: Intent = Intent(MyApp.instance, NotasActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(lista)
            finish()
        })

        editar.setOnClickListener(View.OnClickListener {
            val editar: Intent = Intent(MyApp.instance, NuevaNotaActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                putExtra(Constantes.INTENT_EDIT_KEY,notaId)
            }
            startActivity(editar)
        })
    }
}

