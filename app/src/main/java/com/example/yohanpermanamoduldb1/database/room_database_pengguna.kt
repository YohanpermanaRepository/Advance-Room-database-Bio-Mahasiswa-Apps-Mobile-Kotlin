package com.example.yohanpermanamoduldb1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.yohanpermanamoduldb1.akses_objek.akses_objek_pengguna
import com.example.yohanpermanamoduldb1.model.pengguna
import com.example.yohanpermanamoduldb1.utils.subscribeOnBackground

@Database(entities = [pengguna::class], version = 1, exportSchema = false)
abstract class room_database_pengguna : RoomDatabase() {
    abstract fun usersDao(): akses_objek_pengguna // Mendeklarasikan fungsi abstrak untuk mengakses objek pengguna

    companion object {
        private var instance: room_database_pengguna? = null // Variabel untuk menyimpan instance Room Database

        @Synchronized
        fun getDatabaseUsers(context: Context): room_database_pengguna {
            if (instance == null) {
                // Membuat instance Room Database jika belum ada
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    room_database_pengguna::class.java,
                    "users_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build()
            }
            return instance!!
        }

        private val roomCallBack = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                populatedDatabase(instance!!) // Memanggil fungsi populatedDatabase saat database dibuat
            }
        }

        private fun populatedDatabase(usersRoomDatabase: room_database_pengguna) {
            val usersDao = usersRoomDatabase.usersDao()
            subscribeOnBackground {
                // Menambahkan data pengguna awal ke database menggunakan DAO
                val users = pengguna(
                    "yohanpermana",
                    "Universitas Trunojoyo Madura",
                    "08223730121",
                    "yohan@email.com",
                    "22 mei 2001",
                    "pria"
                )
                usersDao.insert(users)
            }
        }
    }
}
