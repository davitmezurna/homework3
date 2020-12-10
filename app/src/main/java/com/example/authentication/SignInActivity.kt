package com.example.authentication
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        auth = Firebase.auth
        init()
    }
    private fun init(){
        signinactivitybutton.setOnClickListener {
            signin()
        }
    }
    private fun signin(){
        val email1 = signinemail.text.toString()
        val password1 = signinpassword.text.toString()
        progressBar1.visibility = View.VISIBLE
        auth.signInWithEmailAndPassword(email1, password1)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    d("sign in", "signInWithEmail:success")
                    progressBar1.visibility = View.GONE
                    val toast = Toast.makeText(this, "Authentication is Success!", Toast.LENGTH_SHORT)
                    toast.show()
                } else {
                    // If sign in fails, display a message to the user.
                    d("sign in", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    progressBar1.visibility = View.GONE
                    // ...
                }

                // ...
            }
    }


}