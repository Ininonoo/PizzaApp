package com.example.pizzaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //hide title bar
        getSupportActionBar()?.hide()

        //instance text
        val txtUsername:EditText = findViewById(R.id.editTextEmail)
        val txtPassword:EditText = findViewById(R.id.editTextPassword)
        //instance button login
        val btnLogin:Button = findViewById(R.id.buttonLogin)

        //event button login
        btnLogin.setOnClickListener {
            val databaseHelper = DatabaseHelper(this)

            //check data
            val data:String = databaseHelper.checkData("nono@gmail.com")
            Toast.makeText(this@LoginActivity, "Result: " + data,
            Toast.LENGTH_SHORT).show()
            if (data== null){
                //insert data
                databaseHelper.addAccount("nono@gmail.com",
                "nono", "cashier","amikom")
            }

            val email = txtUsername.text.toString().trim()
            val password = txtPassword.text.toString().trim()

            //check login
            val result:Boolean = databaseHelper.checkLogin(email, password)
            if (result == true){
                Toast.makeText(this@LoginActivity,"Login succes",
                Toast.LENGTH_SHORT).show()
                val intentLogin =Intent (this@LoginActivity,
                MainActivity::class.java)
                startActivity(intentLogin)

            }else{
                Toast.makeText(this@LoginActivity, "Login Failed, Tray Again !!",
                Toast.LENGTH_SHORT).show()
            }
        }


    }
}