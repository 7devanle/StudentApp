package com.example.studentapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.studentapp.databinding.ActivityLoginBinding
import com.example.studentapp.model.MainViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding
    val mainViewModel: MainViewModel by viewModels { MainViewModel.Factory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            loginButton.setOnClickListener{
                val intent = Intent(baseContext, MainActivity::class.java)
                val username = username.text.toString().trim()
                val password = passwordtext.text.toString().trim()

                mainViewModel.validateAdmin(username, password)
                mainViewModel.authenticationResult.observe(this@LoginActivity, Observer {isAuthenticated ->
                    if (isAuthenticated){
                        startActivity(intent)
                    }else{
                        binding.username.setText("")
                        binding.passwordtext.setText("")
                        Toast.makeText(
                            this@LoginActivity,
                            "Kindly check admin details again",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })


            }
        }
    }
}