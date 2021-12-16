package com.hijau.kelompokhijau

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.menu_item.*
import java.util.ArrayList
import androidx.fragment.app.FragmentActivity
import android.content.Intent
import android.widget.EditText
import android.widget.Toast
import android.text.Editable

import android.text.TextWatcher

class MyKeranjangAdapter(private val items: ArrayList<MyKeranjang>, val delivFrag: PembayaranFragment): RecyclerView.Adapter<MyKeranjangAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.item_keranjang,parent,false)
    )

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onBindViewHolder(holder:MyKeranjangAdapter.ViewHolder, position: Int) {
            holder.bindItem(items.get(position), position, delivFrag)
        }

        class ViewHolder(override val containerView:View): RecyclerView.ViewHolder(containerView), LayoutContainer{
            val text_menu_keranjang: TextView = containerView.findViewById(R.id.text_menu_keranjang)
            val text_varian_keranjang: TextView = containerView.findViewById(R.id.text_varian_keranjang)
            val text_harga_keranjang: TextView = containerView.findViewById(R.id.text_harga_keranjang)
            val img_menu_keranjang: ImageView = containerView.findViewById(R.id.img_menu_keranjang)
            val img_delete: ImageView = containerView.findViewById(R.id.img_delete)
            val jumlah_barang: EditText = containerView.findViewById(R.id.jumlah_barang)
            val tot: TextView = containerView.findViewById(R.id.tot)
            val card_item_keranjang: ConstraintLayout = containerView.findViewById(R.id.card_item_keranjang)
            val mContext: Context = containerView.context
            val sharedpref = mContext.getSharedPreferences("keranjang" , Context.MODE_PRIVATE)

            fun bindItem(item:MyKeranjang, pos: Int, delivFrag: PembayaranFragment) {
                val sess = sharedpref.getString("id"+pos, "defauld")
                val tooot = sharedpref.getString("tot"+item.posi, "0")

                    Picasso.get().load(item.url).fit().centerCrop().into(img_menu_keranjang)
                    text_menu_keranjang.text = item.nama
                    text_varian_keranjang.text = "Varian :"+item.varian
                    text_harga_keranjang.text = "Rp. "+item.harga
                    img_delete.setOnClickListener {
                        val edit = sharedpref.edit()
                        edit.putString("id"+item.posi.toString(), "defauld")
                        edit.putString("tot"+item.posi.toString(), "0")
                        val cl = tot.getText()
                        Toast.makeText(mContext, "Berhasil Menghapus Pesanan "+cl.toString() , Toast.LENGTH_SHORT).show()
                        edit.apply()
                        delivFrag.countMinInit(cl.toString())
                        delivFrag.reload()
                    }
                    jumlah_barang.setText(tooot.toString());
                    val toti: Int = tooot.toString().toInt() * item.harga.toInt()
                    tot.setText(toti.toString())
                    val cl = tot.getText()
                    delivFrag.countInit2(tot.getText().toString())
                    jumlah_barang.addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(s: Editable) {
                            var c = "0"
                            if (s.toString().equals("")) {
                                c = "0"
                            } else {
                                c = s.toString()
                            }
                            val cl = tot.getText()

                            val edit_pref = sharedpref.edit()
                            edit_pref.putString("tot"+item.posi.toString(), c)
                            edit_pref.apply()
                            val totii: Int = c.toInt() * item.harga.toInt()
                            tot.setText(totii.toString())
                            delivFrag.countInit(tot.getText().toString(), cl.toString())
                        }

                        override fun beforeTextChanged(
                            s: CharSequence,
                            start: Int,
                            count: Int,
                            after: Int
                        ) {
                        }

                        override fun onTextChanged(
                            s: CharSequence,
                            start: Int,
                            before: Int,
                            count: Int
                        ) {
                        }
                    })
            }

        }

    }