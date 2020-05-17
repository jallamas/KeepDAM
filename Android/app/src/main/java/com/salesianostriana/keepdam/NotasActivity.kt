package com.salesianostriana.keepdam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.salesianostriana.keepdam.common.MyApp
import kotlinx.android.synthetic.main.activity_notas.*

class NotasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notas)

        addNote.setOnClickListener{
            view->
            val anyadir: Intent = Intent(MyApp.instance, NuevaNotaActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(anyadir)
            finish()
        }
    }
}
