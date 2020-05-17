package com.salesianostriana.keepdam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import butterknife.BindView
import butterknife.ButterKnife
import com.salesianostriana.keepdam.common.Constantes
import com.salesianostriana.keepdam.common.MyApp
import com.salesianostriana.keepdam.common.MySharedPreferencesManager
import com.salesianostriana.keepdam.models.requests.LoginReq
import com.salesianostriana.keepdam.viewmodel.UserViewModel
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var userViewModel : UserViewModel

    @BindView(R.id.editTextLoginUsername)
    lateinit var username : EditText

    @BindView(R.id.editTextLoginPassword)
    lateinit var password : EditText

    @BindView(R.id.textViewLoginRegister)
    lateinit var register : TextView

    @BindView(R.id.buttonLogin)
    lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        (applicationContext as MyApp).getApplicationComponent().inject(this)
        ButterKnife.bind(this)
        val token : String? = MySharedPreferencesManager().getSharedPreferences()
            .getString(Constantes.SHARED_PREFERENCES_TOKEN_KEY,"")
        if(!token.isNullOrEmpty()){
            val autologin : Intent = Intent(MyApp.instance, NotasActivity::class.java).apply{
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(autologin)
            finish()
        }

        loginButton.setOnClickListener(View.OnClickListener {v->
            userViewModel.doLogin(
                LoginReq(
                    username.text.toString(),
                    password.text.toString()

                )
            ).observe(this, Observer {
                if(it!=null){
                    val login:Intent=Intent(MyApp.instance, NotasActivity::class.java).apply{
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                    startActivity(login)
                    finish()
                }
            })

        })

        register.setOnClickListener(View.OnClickListener {
            val registro : Intent=Intent(MyApp.instance,RegisterActivity::class.java).apply{
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(registro)
            finish()
        })
    }
}
