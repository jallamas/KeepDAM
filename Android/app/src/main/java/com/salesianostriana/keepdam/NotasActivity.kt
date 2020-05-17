package com.salesianostriana.keepdam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.salesianostriana.keepdam.common.Constantes
import com.salesianostriana.keepdam.common.MyApp
import com.salesianostriana.keepdam.common.MySharedPreferencesManager
import kotlinx.android.synthetic.main.activity_notas.*

class NotasActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.up_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.logout){
            MySharedPreferencesManager().removeStringValue(Constantes.SHARED_PREFERENCES_TOKEN_KEY)
            Log.d("REMOVE TOKEN",MySharedPreferencesManager().getSharedPreferences().getString(Constantes.SHARED_PREFERENCES_TOKEN_KEY,""))
            val exit :Intent = Intent(MyApp.instance, LoginActivity::class.java).apply{
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            startActivity(exit)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

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
