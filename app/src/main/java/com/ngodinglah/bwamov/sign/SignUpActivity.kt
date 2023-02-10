package com.ngodinglah.bwamov.sign

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.ngodinglah.bwamov.R
import com.ngodinglah.bwamov.utils.Preferences
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    lateinit var sUsername: String
    lateinit var sPassword: String
    lateinit var sNama: String
    lateinit var sEmail: String

    lateinit var mFirebaseInstance: FirebaseDatabase
    lateinit var mDatabaseReference: DatabaseReference

    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabaseReference = mFirebaseInstance.getReference("User")

        preferences = Preferences(this@SignUpActivity)

        btn_lanjutkan_sign_up.setOnClickListener {
            sUsername = et_username_sign_up.text.toString()
            sPassword = et_username_sign_up.text.toString()
            sNama = et_username_sign_up.text.toString()
            sEmail = et_username_sign_up.text.toString()

            if (sUsername.equals("")) {
                et_username_sign_up.error = "Silahkan isi username Anda"
                et_username_sign_up.requestFocus()
            } else if (sPassword.equals("")) {
                et_password_sign_up.error = "Silahkan isi password Anda"
                et_password_sign_up.requestFocus()
            } else if (sNama.equals("")) {
                et_nama_sign_up.error = "Silahkan isi nama Anda"
                et_nama_sign_up.requestFocus()
            } else if (sEmail.equals("")) {
                et_email_sign_up.error = "Silahkan isi email Anda"
                et_email_sign_up.requestFocus()
            } else {
                saveUsername(sUsername, sPassword, sNama, sEmail)
            }

        }

        iv_back_sign_up.setOnClickListener {
            var backSignIn = Intent(this@SignUpActivity, SignInActivity::class.java)
            startActivity(backSignIn)
            finish()
        }

    }

    private fun saveUsername(sUsername: String, sPassword: String, sNama: String, sEmail: String) {
        var user = User()
        user.email = sEmail
        user.password = sPassword
        user.nama = sNama
        user.username = sUsername

        if (sUsername != null) {
            checkingUsername(sUsername, user)
        }
    }

    private fun checkingUsername(iUsername: String, data: User) {
        mDatabaseReference.child(iUsername).addListenerForSingleValueEvent( object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var user = dataSnapshot.getValue(User::class.java)
                if (user == null) {
                    mDatabaseReference.child(iUsername).setValue(data)

                    preferences.setValues("nama", data.nama.toString())
                    preferences.setValues("user", data.username.toString())
                    preferences.setValues("saldo", "")
                    preferences.setValues("url", "")
                    preferences.setValues("email", data.email.toString())
                    preferences.setValues("status", "1")

                    finishAffinity()
                    var goSignUpPhotoScreen = Intent(this@SignUpActivity, SignUpPhotoScreenActivity::class.java)
                    goSignUpPhotoScreen.putExtra("data", data)
                    startActivity(goSignUpPhotoScreen)

                    Toast.makeText(this@SignUpActivity, "Selamat user terdaftar",
                        Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this@SignUpActivity, "User sudah digunakan",
                        Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignUpActivity, databaseError.message,
                    Toast.LENGTH_LONG).show()
            }
        })
    }
}