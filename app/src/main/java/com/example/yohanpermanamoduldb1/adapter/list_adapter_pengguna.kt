package com.example.yohanpermanamoduldb1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.yohanpermanamoduldb1.R
import com.example.yohanpermanamoduldb1.model.pengguna

class list_adapter_pengguna : ListAdapter<pengguna, list_adapter_pengguna.UsersViewHolder>(userDiffCallBack) {
    lateinit var listener: OnItemCLickListener // Menyimpan instance OnItemCLickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        // Membuat tampilan ViewHolder baru
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.menggunakan_rcycleview, parent, false)
        return UsersViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val users = getItem(position) // Mendapatkan data pengguna pada posisi tertentu dalam daftar
        // Mengisi data item ke ViewHolder
        holder.namaAnda.text = users.fullName
        holder.alamatAnda.text = users.universitas
        holder.Nimmahasiswa.text = users.nim
        holder.emailAnda.text = users.email
        holder.dobAnda.text = users.dob
        holder.genderAnda.text = users.gender
    }

    inner class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Elemen tampilan dalam item RecyclerView
        val namaAnda: TextView = itemView.findViewById(R.id.textViewfullnameItem)
        val alamatAnda: TextView = itemView.findViewById(R.id.textViewUniversitasItem)
        val Nimmahasiswa: TextView = itemView.findViewById(R.id.textViewnimItem)
        val emailAnda: TextView = itemView.findViewById(R.id.textViewemailItem)
        val dobAnda: TextView = itemView.findViewById(R.id.textViewdobItem)
        val genderAnda: TextView = itemView.findViewById(R.id.textViewgenderItem)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition // Mendapatkan posisi item yang diklik
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(getItem(position)) // Memanggil metode onItemClick pada listener
                }
            }
        }
    }

    fun getUsersAt(position: Int) = getItem(position) // Mendapatkan item pengguna pada posisi tertentu dalam daftar

    companion object {
        private val userDiffCallBack = object : DiffUtil.ItemCallback<pengguna>() {
            // Menentukan perbedaan antara item lama dan item baru saat memperbarui daftar
            override fun areItemsTheSame(oldItem: pengguna, newItem: pengguna) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: pengguna, newItem: pengguna) =
                oldItem.fullName == newItem.fullName &&
                        oldItem.universitas == newItem.universitas &&
                        oldItem.email == newItem.email &&
                        oldItem.nim == newItem.nim &&
                        oldItem.dob == newItem.dob &&
                        oldItem.gender == newItem.gender
        }
    }

    interface OnItemCLickListener {
        fun onItemClick(users: pengguna) // Metode yang dipanggil saat item di RecyclerView diklik
    }

    fun setOnItemClickListener(listener: OnItemCLickListener) {
        this.listener = listener // Mengatur listener untuk item yang diklik
    }
}
