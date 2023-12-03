package com.example.yohanpermanamoduldb1.konfigurasi_tampilan

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.yohanpermanamoduldb1.R

class yohan_hapus_pengguna : AppCompatActivity() {
    private var TAG = "MainActivity" // Tag untuk logcat
    private lateinit var namaAnda: TextView // TextView untuk nama pengguna
    private lateinit var universitasAnda: TextView // TextView untuk universitas pengguna
    private lateinit var Nimmahasiswa: TextView // TextView untuk NIM pengguna
    private lateinit var emailAnda: TextView // TextView untuk email pengguna
    private lateinit var dobAnda: TextView // TextView untuk tanggal lahir pengguna
    private lateinit var genderAnda: TextView // TextView untuk jenis kelamin pengguna
    private lateinit var deleteData: Button // Tombol untuk menghapus data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hapus_pengguna)

        // Inisialisasi TextView dan tombol
        namaAnda = findViewById(R.id.textViewFullNameDelete)
        universitasAnda = findViewById(R.id.textViewUniversitasDelete)
        Nimmahasiswa = findViewById(R.id.textViewNimDelete)
        emailAnda = findViewById(R.id.textViewEmailDelete)
        dobAnda = findViewById(R.id.textViewDobDelete)
        genderAnda = findViewById(R.id.textViewGenderDelete)
        deleteData = findViewById(R.id.buttonDeleteData)

        // Mengambil data dari intent dan menampilkannya di TextView
        val intent = intent
        if (intent.hasExtra(EXTRA_ID)) {
            namaAnda.text = intent.getStringExtra(EXTRA_FULLNAME)
            universitasAnda.text = intent.getStringExtra(EXTRA_UNIVERSITAS)
            Nimmahasiswa.text = intent.getStringExtra(EXTRA_NIM)
            emailAnda.text = intent.getStringExtra(EXTRA_EMAIL)
            dobAnda.text = intent.getStringExtra(EXTRA_DOB)
            genderAnda.text = intent.getStringExtra(EXTRA_GENDER)
        }

        deleteData.setOnClickListener {
            val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@yohan_hapus_pengguna)
            alertDialog.setMessage("Apa Anda Yakin Menghapus Data Ini ?")
            alertDialog.setPositiveButton("YAKIN!", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    val deleteDataUsers = Intent()
                    val nama = namaAnda.text.toString()
                    val universitas = universitasAnda.text.toString()
                    val nim = Nimmahasiswa.text.toString()
                    val email = emailAnda.text.toString()
                    val dob = dobAnda.text.toString()
                    val gender = genderAnda.text.toString()

                    // Menampilkan data yang dihapus di logcat
                    Log.d(TAG, "Nama: $nama , Universitas: $universitas, Nim: $nim, Email : $email, TTL: $dob, Jekel: $gender")

                    if (TextUtils.isEmpty(nama)
                        || TextUtils.isEmpty(universitas)
                        || TextUtils.isEmpty(nim)
                        || TextUtils.isEmpty(email)
                        || TextUtils.isEmpty(dob)
                        || TextUtils.isEmpty(gender)
                    ) {
                        // Menampilkan pesan jika ada kolom data yang kosong
                        Toast.makeText(applicationContext, "Kolom Data Wajib Di Isi", Toast.LENGTH_LONG).show()
                        setResult(Activity.RESULT_CANCELED, deleteDataUsers)
                    } else {
                        val id: Int = intent.getIntExtra(EXTRA_ID, -1)
                        Log.d(TAG, "ID: $id")

                        // Mengirim data pengguna yang dihapus kembali ke aktivitas pemanggil
                        deleteDataUsers.putExtra(EXTRA_FULLNAME, nama)
                        deleteDataUsers.putExtra(EXTRA_UNIVERSITAS, universitas)
                        deleteDataUsers.putExtra(EXTRA_NIM, nim)
                        deleteDataUsers.putExtra(EXTRA_EMAIL, email)
                        deleteDataUsers.putExtra(EXTRA_DOB, dob)
                        deleteDataUsers.putExtra(EXTRA_GENDER, gender)
                        deleteDataUsers.putExtra(EXTRA_ID, id)
                        Log.d(TAG, "onCreate Users: $deleteDataUsers")
                    }
                    finish()
                }
            }).setNegativeButton("TIDAK!") { dialog, which -> dialog.dismiss() }.create().show()
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
    }}
