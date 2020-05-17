package com.salesianostriana.keepdam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import butterknife.BindView
import butterknife.ButterKnife
import com.salesianostriana.keepdam.common.Constantes
import com.salesianostriana.keepdam.common.MyApp
import com.salesianostriana.keepdam.models.requests.CreateNotaReq
import com.salesianostriana.keepdam.viewmodel.NotasViewModel
import javax.inject.Inject

class NuevaNotaActivity : AppCompatActivity() {
    @Inject
    lateinit var notasViewModel: NotasViewModel

    @BindView(R.id.editTextNuevanotaTitle)
    lateinit var title: EditText

    @BindView(R.id.editTextNuevanotaContenido)
    lateinit var contenido: EditText

    @BindView(R.id.buttonNuevanotaGuardar)
    lateinit var guardar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nueva_nota)
        (applicationContext as MyApp).getApplicationComponent().inject(this)
        ButterKnife.bind(this)

        val editar = intent.getStringExtra(Constantes.INTENT_EDIT_KEY)
        if(!editar.isNullOrEmpty()){
            notasViewModel.getNota(editar).observe(this, Observer{
                title.setText(it.titulo)
                contenido.setText(it.contenido)
            })
            guardar.setOnClickListener(View.OnClickListener {
                notasViewModel.editNota(editar, CreateNotaReq(title.text.toString(),contenido.text.toString()))
                    .observe(this, Observer { nuevoIntent() })
            })
        } else {
            guardar.setOnClickListener(View.OnClickListener {
                notasViewModel.createNota(CreateNotaReq(title.text.toString(),contenido.text.toString()))
                    .observe(this, Observer{ nuevoIntent() })
            })
        }
    }

    fun nuevoIntent(){
        val lista: Intent = Intent(MyApp.instance, NotasActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(lista)
        finish()
    }
}
