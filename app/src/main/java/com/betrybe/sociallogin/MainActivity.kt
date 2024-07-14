package com.betrybe.sociallogin

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private val email: TextInputLayout by lazy { findViewById(R.id.email_text_input_layout) }
    private val password: TextInputLayout by lazy {
        findViewById(R.id.password_text_input_layout)
    }
    private val loginButton: Button by lazy { findViewById(R.id.login_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        email.editText?.addTextChangedListener {
            loginHandler()
            email.error = ""
        }
        password.editText?.addTextChangedListener {
            loginHandler()
            password.error = ""
        }

        loginButton.setOnClickListener {
            if (!isValidEmail(email.editText?.text.toString())) {
                email.error = "Email inválido"
            } else if (isValidPassword(password.editText?.text.toString())) {
                password.error = "Senha deve ter mais de 4 caracteres"
            } else {
                Snackbar.make(it, "Login efetuado com sucesso", Snackbar.LENGTH_LONG).show()
            }
        }

    }

    private fun loginHandler() {
        val checkEmail = email.editText?.text.toString().trim()
        val checkPassword = password.editText?.text.toString().trim()
        loginButton.isEnabled = checkEmail.isNotEmpty() && checkPassword.isNotEmpty()
    }

    private fun isValidEmail(email: String): Boolean {
        // Regex para validar o email conforme as regras especificadas
        val emailRegex = "^[a-zA-Z0-9.]+@[a-zA-Z]+\\.[a-zA-Z]+$".toRegex()
        return email.matches(emailRegex)
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length <= 4
    }

}
