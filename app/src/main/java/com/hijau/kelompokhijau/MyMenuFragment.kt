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
import android.widget.Toast
import kotlinx.android.synthetic.main.layout_edit.view.*


class MyMenuFragment(private val items: ArrayList<MyMenu>):
    RecyclerView.Adapter<MyMenuFragment.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.menu_item,parent,false)
    )

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onBindViewHolder(holder:MyMenuFragment.ViewHolder, position: Int) {
            holder.bindItem(items.get(position), position)
        }

        class ViewHolder(override val containerView:View): RecyclerView.ViewHolder(containerView), LayoutContainer{

            val text_menu: TextView = containerView.findViewById(R.id.text_menu)
            val text_varian: TextView = containerView.findViewById(R.id.text_varian)
            val text_harga: TextView = containerView.findViewById(R.id.text_harga)
            val img_menu: ImageView = containerView.findViewById(R.id.img_menu)
            val img_edit: ImageView = containerView.findViewById(R.id.img_edit)
            val card_item: ConstraintLayout = containerView.findViewById(R.id.card_item)
            val mContext: Context = containerView.context
            val shared = mContext.getSharedPreferences("keranjang" , Context.MODE_PRIVATE)


            fun bindItem(item:MyMenu, pos: Int) {
                Picasso.get().load(item.url).fit().centerCrop().into(img_menu)
                text_menu.text = item.nama
                text_varian.text = "Varian :"+item.varian
                text_harga.text = "Rp. "+item.harga
                img_edit.setOnClickListener {
                    val mDialogView = LayoutInflater.from(mContext).inflate(R.layout.layout_edit, null)
                    //AlertDialogBuilder
                    val mBuilder = AlertDialog.Builder(mContext)
                        .setView(mDialogView)
                        .setTitle("Form Edit")
                    //show dialog
                    val  mAlertDialog = mBuilder.show()

                    mDialogView.dialog_nama.setText(item.nama.toString())
                    mDialogView.dialog_harga.setText(item.harga.toString())
                    mDialogView.dialog_varian.setText(item.varian.toString())
                    mDialogView.dialog_url.setText(item.url.toString())
                    //login button click of custom layout
                    mDialogView.dialogSaveBtn.setOnClickListener {
                        //dismiss dialog
                        mAlertDialog.dismiss()
                        //get text from EditTexts of custom layout
                        val name = mDialogView.dialog_nama.text.toString()
                        val harga = mDialogView.dialog_harga.text.toString()
                        val varian = mDialogView.dialog_varian.text.toString()
                        val url1 = mDialogView.dialog_url.text.toString()
                        item.harga = harga
                        item.nama = name
                        item.url = url1
                        item.varian = varian
                        text_menu.text = name.toString()
                        text_varian.text = "Varian :"+varian.toString()
                        text_harga.text = "Rp. "+harga.toString()
                        Picasso.get().load(url1).fit().centerCrop().into(img_menu)
                    }
                    //cancel button click of custom layout
                    mDialogView.dialogCancelBtn.setOnClickListener {
                        //dismiss dialog
                        mAlertDialog.dismiss()
                    }
                }
                card_item.setOnClickListener {
                    val builder = AlertDialog.Builder(mContext)
                    builder.setTitle("Masukan Keranjang")
                    builder.setMessage("Apakah Anda Yakin?")
                    builder.setPositiveButton("Masukan Keranjang") { dialog, which ->
                        val edit = shared.edit()
                        edit.putString("id"+pos.toString(), pos.toString())
                        edit.putString("tot"+pos.toString(), "0")
                        Toast.makeText(mContext, "Berhasil Dimasukan Keranjang" , Toast.LENGTH_SHORT).show()
                        edit.apply()
                    }

                    builder.setNegativeButton("Tidak") { dialog, which ->
                    }
                    builder.show()
                }
            }

        }

    }