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

class yohan_tambah_pengguna : AppCompatActivity() {
    private lateinit var namaAnda : EditText
    private lateinit var universitasAnda : EditText
    private lateinit var Nimmahasiswa : EditText
    private lateinit var emailAnda : EditText
    private lateinit var dobAnda : EditText
    private lateinit var genderAnda : EditText
    private lateinit var simpanData : Button
    private var TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tambah_pengguna)

        // Mendapatkan referensi ke elemen UI dari layout menggunakan ID-nya
        namaAnda = findViewById(R.id.EditTextFullNameAdd)
        universitasAnda = findViewById(R.id.EditTextUniversitasAdd)
        Nimmahasiswa = findViewById(R.id.EditTextnimAdd)
        emailAnda = findViewById(R.id.EditTextEmailAdd)
        dobAnda = findViewById(R.id.EditTextDobAdd)
        genderAnda = findViewById(R.id.EditTextGenderAdd)
        simpanData = findViewById(R.id.buttonInsertData)

        // Menambahkan listener untuk tombol simpanData
        simpanData.setOnClickListener {

            // Membuat objek Intent
            val insertData = Intent()

            // Mendapatkan nilai dari EditText ke dalam variabel-variabel
            val nama = namaAnda.text.toString()
            val universitas = universitasAnda.text.toString()
            val nim = Nimmahasiswa.text.toString()
            val email = emailAnda.text.toString()
            val dob = dobAnda.text.toString()
            val gender = genderAnda.text.toString()

            // Menampilkan informasi ke dalam log
            Log.i(TAG, "Nama: $nama ,  Universitas: $universitas, Nim: $nim, Email : $email, TTL: $dob, Jekel: $gender")

            // Memeriksa apakah ada kolom yang kosong
            if (TextUtils.isEmpty(nama)
                || TextUtils.isEmpty(universitas)
                || TextUtils.isEmpty(nim)
                || TextUtils.isEmpty(email)
                || TextUtils.isEmpty(dob)
                || TextUtils.isEmpty(gender))
            {
                // Menampilkan pesan Toast jika ada kolom yang kosong
                Toast.makeText(applicationContext,"Kolom Data Wajib Di Isi",Toast.LENGTH_LONG).show()

                // Mengatur hasil Activity menjadi RESULT_CANCELED dan mengirimkan data melalui Intent
                setResult(Activity.RESULT_CANCELED , insertData)
            } else
            {
                // Memasukkan data ke dalam Intent
                insertData.putExtra(EXTRA_FULLNAME, nama)
                insertData.putExtra(EXTRA_UNIVERSITAS,universitas)
                insertData.putExtra(EXTRA_NIM,nim)
                insertData.putExtra(EXTRA_EMAIL,email)
                insertData.putExtra(EXTRA_DOB,dob)
                insertData.putExtra(EXTRA_GENDER,gender)

                // Mengatur hasil Activity menjadi RESULT_OK dan mengirimkan data melalui Intent
                setResult(Activity.RESULT_OK, insertData)

                // Menampilkan informasi ke dalam log
                Log.i(TAG, "onCreate Users: $insertData")
            }

            // Mengakhiri aktivitas saat tombol simpanData diklik
            finish()
        }
    }


    companion object {
        const val EXTRA_FULLNAME = "com.example.crudroom.EXTRA_FULLNAME"
        const val EXTRA_UNIVERSITAS = "com.example.crudroom.EXTRA_UNIVERSITAS"
        const val EXTRA_NIM = "com.example.crudroom.EXTRA_NIM"
        const val EXTRA_EMAIL = "com.example.crudroom.EXTRA_EMAIL"
        const val EXTRA_DOB = "com.example.crudroom.EXTRA_DOB"
        const val EXTRA_GENDER = "com.example.crudroom.EXTRA_GENDER"
    }
}