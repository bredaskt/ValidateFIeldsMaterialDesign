package com.bbreda.validatefields.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.bbreda.validatefields.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
    }

    override fun onResume() {
        super.onResume()
        setEmailTextWatcher()
    }

    private fun setListeners() {
        btLogin.setOnClickListener {
            validateForm()
        }
    }

    private fun setEmailTextWatcher() {
        etEmail.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int,
                count: Int
            ) {
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {
                if (isValidEmail(s.toString())) {
                    etEmail.error = "E-mail válido!"
                    btLogin.isEnabled = true
                } else {
                    etEmail.error = "Exemplo de email válido: seuemail@gmail.com"
                    btLogin.isEnabled = false
                }
            }
        })
    }

    private fun validateForm() {
        if (etEmail.text.toString().isEmpty()) {
            txtLayoutEmail.isErrorEnabled = true
            txtLayoutEmail.error = "Verifique se o e-mail foi preenchido corretamente!"
        } else {
            txtLayoutEmail.isErrorEnabled = false
        }

        if (etPassword.text.toString().isEmpty()) {
            txtLayoutPassword.isErrorEnabled = true
            txtLayoutPassword.error = "Verifique se sua senha foi preenchida corretamente!"
        } else {
            txtLayoutPassword.isErrorEnabled = false
        }

    }

    private val EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(
        ("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+")
    )

    fun isValidEmail(email: String): Boolean {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches()
    }

}
