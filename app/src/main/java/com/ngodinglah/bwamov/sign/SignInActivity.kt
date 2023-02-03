package com.ngodinglah.bwamov.sign

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.ngodinglah.bwamov.home.HomeScreenActivity
import com.ngodinglah.bwamov.R
import com.ngodinglah.bwamov.utils.Preferences
import kotlinx.android.synthetic.main.activity_sign_in.*


class SignInActivity : AppCompatActivity() {

    lateinit var iUsername:String
    lateinit var iPassword:String

    lateinit var mDatabase: DatabaseReference
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        mDatabase = FirebaseDatabase.getInstance().getReference("User")
        preferences = Preferences(this@SignInActivity)

        preferences.setValues("onboarding", "1")

        if (preferences.getValues("status").equals("1")) {
            finishAffinity()

            var goHome = Intent(this@SignInActivity, HomeScreenActivity::class.java)
            startActivity(goHome)
        }

        btn_masuk_akun_sign_in.setOnClickListener {
            iUsername = et_username.text.toString()
            iPassword = et_password.text.toString()

            if (iUsername.equals("")) {
                et_username.error = "Silahkan tulis username Anda"
                et_username.requestFocus()
            } else if (iPassword.equals("")) {
                et_password.error = "Silahkan tulis password Anda"
                et_password.requestFocus()
            } else {
                pushLogin(iUsername, iPassword)
            }
        }

        btn_daftar_akun_sign_in.setOnClickListener {
            var signUp = Intent(this@SignInActivity, SignUpActivity::class.java)
            startActivity(signUp)
        }
    }

    private fun pushLogin(iUsername: String, iPassword: String) {
        mDatabase.child(iUsername).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                var user = dataSnapshot.getValue(User::class.java)

                if (user == null) {
                    Toast.makeText(this@SignInActivity, "User tidak ditemukan",
                        Toast.LENGTH_LONG).show()
                    et_username.requestFocus()
                } else {
                    if (user.password.equals(iPassword)) {
                        preferences.setValues("nama", user.nama.toString())
                        preferences.setValues("userName", user.username.toString())
                        preferences.setValues("url", user.url.toString())
                        preferences.setValues("email", user.email.toString())
                        preferences.setValues("saldo", user.saldo.toString())
                        preferences.setValues("status", "1")
                        finishAffinity()

                        var home = Intent(this@SignInActivity, HomeScreenActivity:: class.java)
                        startActivity(home)
                    } else {
                        Toast.makeText(this@SignInActivity, "Password Anda Salah",
                            Toast.LENGTH_LONG).show()
                        et_password.requestFocus()
                    }
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignInActivity, databaseError.message,
                    Toast.LENGTH_LONG).show()
            }

        })
    }
}