package tn.esprit.nascar

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import com.google.android.material.snackbar.Snackbar
import tn.esprit.nascar.databinding.ActivityLoginBinding

const val PREF_FILE = "NASCAR_PREF"
const val EMAIL = "EMAIL"
const val PASSWORD = "PASSWORD"
const val IS_REMEMBERED = "IS_REMEMBERED"

class LoginActivity : AppCompatActivity() {
    private val PREF_FILE = "NASCAR_PREF"
    private lateinit var mSharedPreferences: SharedPreferences
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TODO 4 Create a val mSharedPreferences and initialise it
        mSharedPreferences = getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)



        binding.tiEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                return
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validateEmail()
            }

            override fun afterTextChanged(s: Editable?) {
                return
            }
        })

        binding.tiPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                return
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validatePassword()
            }

            override fun afterTextChanged(s: Editable?) {
                return
            }
        })

        //TODO 5 Check if the user is already authenticated and navigate to MainActivity
        val isAuthenticated = mSharedPreferences.getBoolean(IS_REMEMBERED, false)
        if (isAuthenticated) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        binding.btnLogin.setOnClickListener {
            if (validateEmail() && validatePassword()) {
                // Check if rememberMe is true
                val rememberMe = binding.btnRememberMe.isChecked

                // Save data in SharedPreferences
                val editor = mSharedPreferences.edit()
                editor.putString(EMAIL, binding.tiEmail.text.toString())
                editor.putString(PASSWORD, binding.tiPassword.text.toString())
                editor.putBoolean(IS_REMEMBERED, rememberMe)
                editor.apply()

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Snackbar.make(
                    binding.contextView,
                    getString(R.string.msg_error_inputs),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }


    }

    private fun validateEmail(): Boolean {
        binding.tiEmailLayout.isErrorEnabled = false

        if (binding.tiEmail.text.toString().isEmpty()) {
            binding.tiEmailLayout.error = getString(R.string.msg_must_not_be_empty)
            binding.tiEmail.requestFocus()
            return false
        }else{
            binding.tiEmailLayout.isErrorEnabled = false
        }

        if (Patterns.EMAIL_ADDRESS.matcher(binding.tiEmail.text.toString()).matches()) {
            binding.tiEmailLayout.error = getString(R.string.msg_check_your_email)
            binding.tiEmail.requestFocus()
            return false
        }else{
            binding.tiEmailLayout.isErrorEnabled = false
        }

        return true
    }

    private fun validatePassword(): Boolean {
        binding.tiPasswordLayout.isErrorEnabled = false

        if (binding.tiPassword.text.toString().isEmpty()) {
            binding.tiPasswordLayout.error = getString(R.string.msg_must_not_be_empty)
            binding.tiPassword.requestFocus()
            return false
        }else{
            binding.tiPasswordLayout.isErrorEnabled = false
        }

        if (binding.tiPassword.text.toString().length < 6) {
            binding.tiPasswordLayout.error = getString(R.string.msg_check_your_characters)
            binding.tiPassword.requestFocus()
            return false
        }else{
            binding.tiPasswordLayout.isErrorEnabled = false
        }

        return true
    }

}