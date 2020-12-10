package com.example.authentication

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
// ...
// Initialize Firebase Auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        auth = Firebase.auth
        init()
    }
    private fun init(){
        signupactivitybutton.setOnClickListener {
            signup()
        }
    }

    private fun signup() {

        val email = signupemail.text.toString()
        val password = signupassword.text.toString()
        if ("@" in email) {
            progressBar.visibility = View.VISIBLE
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        d("sign up", "createUserWithEmail:success")
                        val toast = Toast.makeText(this, "SignUp is Success!", Toast.LENGTH_SHORT)
                        toast.show()
                        progressBar.visibility = View.GONE
                        intent = Intent(this, SignInActivity::class.java)
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        d("sign up", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                        val toast = Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT)
                        toast.show()
                        progressBar.visibility = View.GONE
                    }

                    // ...
                }
        }else{
            val toast = Toast.makeText(this, "Email format is not Correct", Toast.LENGTH_SHORT)
            toast.show()
        }
    }







}