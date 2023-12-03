package com.example.yohanpermanamoduldb1.konfigurasi_tampilan

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yohanpermanamoduldb1.R
import com.example.yohanpermanamoduldb1.adapter.list_adapter_pengguna
import com.example.yohanpermanamoduldb1.model.pengguna
import com.example.yohanpermanamoduldb1.viewmodel.UsersViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var usersViewModel: UsersViewModel // ViewModel untuk pengguna
    private lateinit var adapter: list_adapter_pengguna // Adapter untuk RecyclerView
    private var insertUserActivityRequestCode = 1 // Kode permintaan untuk aktivitas tambah pengguna
    private var updateUsersActivityRequestCode = 2 // Kode permintaan untuk aktivitas ubah pengguna
    private var deleteUsersActivityRequestCode = 3 // Kode permintaan untuk aktivitas hapus pengguna
    private var TAG = "MainActivity" // Tag untuk logcat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = list_adapter_pengguna()
        val recyclerView: RecyclerView = findViewById(R.id.recyclerviewUsers)

        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        usersViewModel =
            ViewModelProviders.of(this)[UsersViewModel::class.java] // Menginisialisasi ViewModel pengguna

        usersViewModel.getAllUsers().observe(this) {
            Log.d(TAG, "Users Observer: $it")
            adapter.submitList(it) // Memperbarui daftar pengguna dalam adapter saat ada perubahan data
        }

        // Mengatur ItemTouchHelper untuk menghapus pengguna saat di-swipe
        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val user = adapter.getUsersAt(viewHolder.adapterPosition)
                usersViewModel.delete(user) // Menghapus pengguna menggunakan ViewModel
                Toast.makeText(applicationContext, "Users Delete", Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(recyclerView)

        // Mengatur listener untuk item di RecyclerView
        adapter.setOnItemClickListener(object : list_adapter_pengguna.OnItemCLickListener {
            override fun onItemClick(users: pengguna) {
                val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
                alertDialog.setMessage("Apa Yang Ingin Anda Lakukan Dengan Data Ini ?")

                // Tombol positif untuk memperbarui pengguna
                alertDialog.setPositiveButton(
                    getString(R.string.update),
                    object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            val updateData =
                                Intent(this@MainActivity, yohan_update_pengguna::class.java)
                            // Mengirim data pengguna ke aktivitas yohan_update_pengguna untuk diperbarui
                            updateData.putExtra(yohan_update_pengguna.EXTRA_ID, users.id)
                            updateData.putExtra(
                                yohan_update_pengguna.EXTRA_FULLNAME,
                                users.fullName
                            )
                            updateData.putExtra(
                                yohan_update_pengguna.EXTRA_UNIVERSITAS,
                                users.universitas
                            )
                            updateData.putExtra(yohan_update_pengguna.EXTRA_NIM, users.nim)
                            updateData.putExtra(yohan_update_pengguna.EXTRA_EMAIL, users.email)
                            updateData.putExtra(yohan_update_pengguna.EXTRA_DOB, users.dob)
                            updateData.putExtra(yohan_update_pengguna.EXTRA_GENDER, users.gender)
                            startActivityForResult(updateData, updateUsersActivityRequestCode)
                        }
                    })

                // Tombol negatif untuk menghapus pengguna
                alertDialog.setNegativeButton(
                    getString(R.string.delete),
                    object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface?, which: Int) {
                            val deleteData =
                                Intent(this@MainActivity, yohan_hapus_pengguna::class.java)
                            // Mengirim data pengguna ke aktivitas yohan_hapus_pengguna untuk penghapusan pengguna
                            deleteData.putExtra(yohan_hapus_pengguna.EXTRA_ID, users.id)
                            deleteData.putExtra(yohan_hapus_pengguna.EXTRA_FULLNAME, users.fullName)
                            deleteData.putExtra(
                                yohan_hapus_pengguna.EXTRA_UNIVERSITAS,
                                users.universitas
                            )
                            deleteData.putExtra(yohan_hapus_pengguna.EXTRA_NIM, users.nim)
                            deleteData.putExtra(yohan_hapus_pengguna.EXTRA_EMAIL, users.email)
                            deleteData.putExtra(yohan_hapus_pengguna.EXTRA_DOB, users.dob)
                            deleteData.putExtra(yohan_hapus_pengguna.EXTRA_GENDER, users.gender)
                            startActivityForResult(deleteData, deleteUsersActivityRequestCode)
                        }
                    }).create().show()
            }
        })

        val fabadd: FloatingActionButton = findViewById(R.id.fabAdd)
        fabadd.setOnClickListener {
            val insertData = Intent(this, yohan_tambah_pengguna::class.java)
            startActivityForResult(insertData, insertUserActivityRequestCode)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null && requestCode == insertUserActivityRequestCode && resultCode == RESULT_OK) {
            // Mendapatkan data tambahan dari aktivitas tambah pengguna
            val fullName: String =
                data.getStringExtra(yohan_tambah_pengguna.EXTRA_FULLNAME).toString()
            val universitas: String =
                data.getStringExtra(yohan_tambah_pengguna.EXTRA_UNIVERSITAS).toString()
            val nim: String = data.getStringExtra(yohan_tambah_pengguna.EXTRA_NIM).toString()
            val email: String = data.getStringExtra(yohan_tambah_pengguna.EXTRA_EMAIL).toString()
            val dob: String = data.getStringExtra(yohan_tambah_pengguna.EXTRA_DOB).toString()
            val gender: String = data.getStringExtra(yohan_tambah_pengguna.EXTRA_GENDER).toString()

            val users = pengguna(fullName, universitas, nim, email, dob, gender)
            usersViewModel.insert(users) // Menyimpan pengguna baru ke dalam database menggunakan ViewModel
            Log.d(TAG, "onActivityResult INsert: $users")

            Toast.makeText(applicationContext, "Users Inserted", Toast.LENGTH_SHORT).show()
        } else if (data != null && requestCode == updateUsersActivityRequestCode && resultCode == RESULT_OK) {


            // Mendapatkan data tambahan dari aktivitas ubah pengguna
            val id = data.getIntExtra(yohan_update_pengguna.EXTRA_ID, -1)
            Log.i(TAG, "ID Users: $id")
            if (id == -1) {
                Toast.makeText(applicationContext, "Update gagal", Toast.LENGTH_SHORT).show()
                return
            }
            val fullNameupdate: String =
                data.getStringExtra(yohan_update_pengguna.EXTRA_FULLNAME).toString()
            val universitasupdate: String =
                data.getStringExtra(yohan_update_pengguna.EXTRA_UNIVERSITAS).toString()
            val nimupdate: String = data.getStringExtra(yohan_update_pengguna.EXTRA_NIM).toString()
            val emailupdate: String =
                data.getStringExtra(yohan_update_pengguna.EXTRA_EMAIL).toString()
            val dobupdate: String = data.getStringExtra(yohan_update_pengguna.EXTRA_DOB).toString()
            val genderupdate: String =
                data.getStringExtra(yohan_update_pengguna.EXTRA_GENDER).toString()
            val usersupdate = pengguna(
                fullNameupdate,
                universitasupdate,
                nimupdate,
                emailupdate,
                dobupdate,
                genderupdate,
                id
            )
            usersViewModel.update(usersupdate) // Mengupdate pengguna ke dalam database menggunakan ViewModel
            Toast.makeText(applicationContext, "Update Berhasil", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "onActivityResult Update: $usersupdate")


        } else if (data != null && requestCode == deleteUsersActivityRequestCode && resultCode == RESULT_OK) {
            // Mendapatkan data tambahan dari aktivitas hapus pengguna
            val id = data.getIntExtra(yohan_hapus_pengguna.EXTRA_ID, -1)
            Log.i(TAG, "ID Users: $id")
            if (id == -1) {
                Toast.makeText(applicationContext, "Delete gagal", Toast.LENGTH_SHORT).show()
                return
            }
            val fullName: String =
                data.getStringExtra(yohan_hapus_pengguna.EXTRA_FULLNAME).toString()
            val universitas: String =
                data.getStringExtra(yohan_hapus_pengguna.EXTRA_UNIVERSITAS).toString()
            val nim: String = data.getStringExtra(yohan_hapus_pengguna.EXTRA_NIM).toString()
            val email: String = data.getStringExtra(yohan_hapus_pengguna.EXTRA_EMAIL).toString()
            val dob: String = data.getStringExtra(yohan_hapus_pengguna.EXTRA_DOB).toString()
            val gender: String = data.getStringExtra(yohan_hapus_pengguna.EXTRA_GENDER).toString()
            val users = pengguna(fullName, universitas, nim, email, dob, gender, id)


            usersViewModel.delete(users) // Menghapus pengguna dari database menggunakan ViewModel
            Toast.makeText(applicationContext, "Delete berhasil", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "onActivityResult Delete: $users")
        } else {
            Toast.makeText(applicationContext, "Users tidak tersimpan", Toast.LENGTH_SHORT).show()
        }
    }
}
