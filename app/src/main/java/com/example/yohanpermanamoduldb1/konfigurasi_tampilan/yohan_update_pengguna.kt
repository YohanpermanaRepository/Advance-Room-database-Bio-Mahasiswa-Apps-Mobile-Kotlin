package com.example.yohanpermanamoduldb1.konfigurasi_tampilan

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.yohanpermanamoduldb1.R

class yohan_update_pengguna : AppCompatActivity() {
    private var TAG = "MainActivity"
    private lateinit var namaAnda : EditText
    private lateinit var universitasAnda : EditText
    private lateinit var Nimmahasiswa : EditText
    private lateinit var emailAnda : EditText
    private lateinit var dobAnda : EditText
    private lateinit var genderAnda : EditText
    private lateinit var simpanData : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_pengguna)
        namaAnda = findViewById(R.id.EditTextFullNameUpdate)
        universitasAnda = findViewById(R.id.EditTextUniversitasUpdate)
        Nimmahasiswa = findViewById(R.id.EditTextNIMUpdate)
        emailAnda = findViewById(R.id.EditTextEmailUpdate)
        dobAnda = findViewById(R.id.EditTextDobUpdate)
        genderAnda = findViewById(R.id.EditTextGenderUpdate)
        simpanData = findViewById(R.id.buttonUpdateData)


        // ambil data
        val intent = intent
        if (intent.hasExtra(EXTRA_ID)){
            namaAnda.setText(intent.getStringExtra(EXTRA_FULLNAME))
            universitasAnda.setText(intent.getStringExtra(EXTRA_UNIVERSITAS))
            Nimmahasiswa.setText(intent.getStringExtra(EXTRA_NIM))
            emailAnda.setText(intent.getStringExtra(EXTRA_EMAIL))
            dobAnda.setText(intent.getStringExtra(EXTRA_DOB))
            genderAnda.setText(intent.getStringExtra(EXTRA_GENDER))
        }
        simpanData.setOnClickListener {
            val updateData = Intent()
            val nama = namaAnda.text.toString()
            val universitas = universitasAnda.text.toString()
            val nim = Nimmahasiswa.text.toString()
            val email = emailAnda.text.toString()
            val dob = dobAnda.text.toString()
            val gender = genderAnda.text.toString()

            Log.d(TAG, "Nama: $nama , Universitas: $universitas, Nim: $nim, Email : $email, TTL: $dob, Jekel: $gender")

            if (TextUtils.isEmpty(nama)
                || TextUtils.isEmpty(universitas)
                || TextUtils.isEmpty(nim)
                || TextUtils.isEmpty(email)
                || TextUtils.isEmpty(dob)
                || TextUtils.isEmpty(gender))
            {
                Toast.makeText(applicationContext,"Kolom Data Wajib Di Isi", Toast.LENGTH_LONG).show()
                setResult(Activity.RESULT_CANCELED , updateData)
            }else
            {
                val id : Int = intent.getIntExtra(EXTRA_ID, -1)
                Log.d(TAG, "ID: $id")
                updateData.putExtra(EXTRA_FULLNAME, nama)
                updateData.putExtra(EXTRA_UNIVERSITAS,universitas)
                updateData.putExtra(EXTRA_NIM,nim)
                updateData.putExtra(EXTRA_EMAIL,email)
                updateData.putExtra(EXTRA_DOB,dob)
                updateData.putExtra(EXTRA_GENDER,gender)
                updateData.putExtra(EXTRA_ID,id);
                setResult(Activity.RESULT_OK, updateData)
                Log.d(TAG, "onCreate Users: $updateData")
            }
            finish()
        }
    }
    companion object {
        const val EXTRA_ID = "com.example.yohanpermanamoduldb1.EXTRA_ID"
        const val EXTRA_FULLNAME = "com.example.yohanpermanamoduldb1.EXTRA_FULLNAME"
        const val EXTRA_UNIVERSITAS = "com.example.yohanpermanamoduldb1.EXTRA_UNIVERSITAS"
        const val EXTRA_NIM = "com.example.yohanpermanamoduldb1.EXTRA_NIM"
        const val EXTRA_EMAIL = "com.example.yohanpermanamoduldb1.EXTRA_EMAIL"
        const val EXTRA_DOB = "com.example.yohanpermanamoduldb1.EXTRA_DOB"
        const val EXTRA_GENDER = "com.example.yohanpermanamoduldb1.EXTRA_GENDER"
    }
}

