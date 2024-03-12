package com.example.alejandro_sanabria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.lifecycleScope

class MainActivity : AppCompatActivity() {

    private val vm by lazy { ViewModelMain() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm.loading.observe(this){
            if (it == true){
                findViewById<ProgressBar>(R.id.progress_circular)
                    .visibility = VISIBLE
                findViewById<EditText>(R.id.et_user_name).visibility = GONE
                findViewById<EditText>(R.id.et_user_password).visibility = GONE
                findViewById<Button>(R.id.btn_login).visibility = GONE

            } else {
                findViewById<ProgressBar>(R.id.progress_circular)
                    .visibility = GONE
            }
        }

        vm.result.observe(this){
            if (it == true){
                Toast.makeText(this@MainActivity, "el login se ejecuto correctamente", Toast.LENGTH_SHORT).show()
            }

            if (it == false){
                Toast.makeText(this@MainActivity, "el usuario o contraseña son incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.btn_login).setOnClickListener {
            vm.doLogin(
                UserModel(
                    findViewById<EditText>(R.id.et_user_name).text.toString(),
                    findViewById<EditText>(R.id.et_user_password).text.toString()
                )
            )
        }

    }
}