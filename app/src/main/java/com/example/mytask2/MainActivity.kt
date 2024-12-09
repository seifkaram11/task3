package com.example.mytask2
import MovieAdapter
import MovieListActivity2
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val signInButton = findViewById<Button>(R.id.signInButton)
        signInButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            if (validateInput(username, password)) {
                val intent = Intent(this, MovieListActivity::class.java)
                intent.putExtra("username", username)
                startActivity(intent)
                finish()
            }
        }
//        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
//            Log.e("AppCrash", "Unhandled exception in thread ${thread.name}", throwable)
//        }
    }
    private fun validateInput(username: String, password: String): Boolean {
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_SHORT).show()
            return false
        }
        if (password.length < 8) {
            Toast.makeText(
                this,
                "Password must be at least 8 characters long",
                Toast.LENGTH_LONG
            ).show()
            return false
        }
        return true
    }
}
