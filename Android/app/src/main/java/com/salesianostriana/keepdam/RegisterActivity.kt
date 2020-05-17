package com.salesianostriana.keepdam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import butterknife.BindView
import butterknife.ButterKnife
import com.salesianostriana.keepdam.common.MyApp
import com.salesianostriana.keepdam.models.requests.RegisterReq
import com.salesianostriana.keepdam.viewmodel.UserViewModel
import javax.inject.Inject

class RegisterActivity : AppCompatActivity() {

    @Inject
    lateinit var userViewModel: UserViewModel

    @BindView(R.id.buttonRegister)
    lateinit var register: Button

    @BindView(R.id.editTextRegisterUsername)
    lateinit var username: EditText

    @BindView(R.id.editTextRegisterFullname)
    lateinit var fullname: EditText

    @BindView(R.id.editTextRegisterPassword1)
    lateinit var password1: EditText

    @BindView(R.id.editTextRegisterPassword2)
    lateinit var password2: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        (applicationContext as MyApp).getApplicationComponent().inject(this)
        ButterKnife.bind(this)

        register.setOnClickListener(View.OnClickListener {
            if(password1.text.toString().equals(password2.text.toString())) {

                userViewModel.signup(
                    RegisterReq(
                        username.text.toString(),
                        fullname.text.toString(),
                        password1.text.toString(),
                        password2.text.toString()
                    )
                ).observe(this, Observer {
                    val register:Intent = Intent(MyApp.instance,LoginActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                    startActivity(register)
                    finish()
                })
            }else{
                Toast.makeText(MyApp.instance,"Las contraseñas no coinciden",Toast.LENGTH_SHORT).show()
            }

        })
    }
}
