package com.ngodinglah.bwamov.sign

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.single.PermissionListener
import com.ngodinglah.bwamov.R
import com.ngodinglah.bwamov.home.HomeScreenActivity
import com.ngodinglah.bwamov.utils.Preferences
import kotlinx.android.synthetic.main.activity_sign_up_photoscreen.*
import java.util.*

class SignUpPhotoScreenActivity : AppCompatActivity(), PermissionListener {

    //    val REQUEST_IMAGE_CAPTURE = 1
    var statusAdd: Boolean = false
    lateinit var filePath: Uri

    lateinit var storage: FirebaseStorage
    lateinit var storageReference: StorageReference
    lateinit var preferences: Preferences

    lateinit var user: User
    lateinit var mFirebaseDatabase: DatabaseReference
    lateinit var mFirebaseIntance: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_photoscreen)

        preferences = Preferences(this@SignUpPhotoScreenActivity)
        storage = FirebaseStorage.getInstance()
        storageReference = storage.getReference()

        mFirebaseIntance = FirebaseDatabase.getInstance()
        mFirebaseDatabase = mFirebaseIntance.getReference("User")

        user = intent.getParcelableExtra("data")!!
//        user = intent.getParcelableExtra<User>("data")!!
        tv_welcome.text = "Welcome\n" + user.nama

        iv_add.setOnClickListener {
            if (statusAdd) {
                statusAdd = false
                btn_simpan_lanjutkan.visibility = View.VISIBLE
                iv_add.setImageResource(R.drawable.ic_btn_add)
                iv_profile.setImageResource(R.drawable.user_pic)
            } else {
//                Dexter.withActivity(this@SignUpPhotoScreenActivity)
//                    .withPermission(Manifest.permission.CAMERA)
//                    .withListener(this@SignUpPhotoScreenActivity)
//                    .check()

//                ImagePicker.with(this)
//                    .cameraOnly()    //User can only capture image using Camera
//                    .start()
            }
        }

        iv_back_photo.setOnClickListener {
            var backSignUp = Intent(this@SignUpPhotoScreenActivity, SignUpActivity::class.java)
            startActivity(backSignUp)
            finish()
        }

        btn_upload_nanti.setOnClickListener {
            finishAffinity()

            var goHome = Intent(this@SignUpPhotoScreenActivity, HomeScreenActivity::class.java)
            startActivity(goHome)
        }

        btn_simpan_lanjutkan.setOnClickListener {
            if (filePath != null) {
                var progresDialog = ProgressDialog(this@SignUpPhotoScreenActivity)
                progresDialog.setTitle("Uploading...")
                progresDialog.show()

                val ref = storageReference.child("images/" + UUID.randomUUID().toString())
                ref.putFile(filePath)
                    .addOnSuccessListener {
                        progresDialog.dismiss()
                        Toast.makeText(
                            this@SignUpPhotoScreenActivity,
                            "Uploaded",
                            Toast.LENGTH_LONG
                        ).show()

                        ref.downloadUrl.addOnSuccessListener {
                            saveToFirebase(it.toString())
                        }
                    }
                    .addOnFailureListener { e ->
                        progresDialog.dismiss()
                        Toast.makeText(
                            this@SignUpPhotoScreenActivity,
                            "Failed" + e.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    .addOnProgressListener { taskSnapshot ->
                        val progress =
                            100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                        progresDialog.setMessage("Upload " + progress.toInt() + " %")
                    }
            }
        }
    }

    private fun saveToFirebase(url: String) {
        mFirebaseDatabase.child(user.username!!).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                user.url = url
                mFirebaseDatabase.child(user.username!!).setValue(user)

                preferences.setValues("nama", user.nama.toString())
                preferences.setValues("user", user.username.toString())
                preferences.setValues("saldo", "")
                preferences.setValues("url", "")
                preferences.setValues("email", user.email.toString())
                preferences.setValues("status", "1")
                preferences.setValues("url", url)

                finishAffinity()
                val goHome = Intent(this@SignUpPhotoScreenActivity, HomeScreenActivity::class.java)
                startActivity(goHome)
            }

            override fun onCancelled(dataBaseError: DatabaseError) {
                Toast.makeText(
                    this@SignUpPhotoScreenActivity,
                    "" + dataBaseError.message,
                    Toast.LENGTH_LONG
                ).show()
            }

        })
    }

    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
//        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
//            takePictureIntent.resolveActivity(packageManager)?.also {
//                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
//            }
//        }

//        ImagePicker.with(this)
//            .cameraOnly()    //User can only capture image using Camera
//            .start()
    }

    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
        Toast.makeText(
            this@SignUpPhotoScreenActivity,
            "Anda tidak bisa menambahkan photo Profile",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onBackPressed() {
        Toast.makeText(
            this@SignUpPhotoScreenActivity,
            "Tergesah? klik tombol upload nanti aja",
            Toast.LENGTH_LONG
        ).show()
    }

//    @SuppressLint("MissingSuperCall")
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
//            var bitmap = data?.extras?.get("data") as Bitmap
//            statusAdd = true
//
//            filePath = data.getData()!!
//            Glide.with(this@SignUpPhotoScreenActivity)
//                .load(bitmap)
//                .apply(RequestOptions.circleCropTransform())
//                .into(iv_profile)
//
//            btn_simpan_lanjutkan.visibility = View.VISIBLE
//            iv_add.setImageResource(R.drawable.ic_btn_delete)
//        }
//    }

    override fun onPermissionRationaleShouldBeShown(
        permission: com.karumi.dexter.listener.PermissionRequest?,
        token: PermissionToken?
    ) {

    }
}