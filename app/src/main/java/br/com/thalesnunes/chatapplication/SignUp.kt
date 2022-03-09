package br.com.thalesnunes.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {

    // Declarando as variáveis necessárias
    private lateinit var edtName: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnSignUp: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Retirando a barra superior
        supportActionBar?.hide()

        // Inicializando a autentificação
        mAuth = FirebaseAuth.getInstance()

        // Referenciando os elementos
        edtName = findViewById(R.id.edt_name)
        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)
        btnSignUp = findViewById(R.id.btnSignUp)

        btnSignUp.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            signUp(email, password)
        }
    }

    private fun signUp(email: String, password: String){

        // logica de criação de usuário
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // alternando pra home
                    val intent = Intent(this@SignUp, MainActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this@SignUp, "Ocorreu algum erro!", Toast.LENGTH_SHORT).show()
                }
            }
    }
}