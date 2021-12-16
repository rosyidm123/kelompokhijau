package com.hijau.kelompokhijau

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_menu.*

class PembayaranFragment : Fragment() {

    var rv_keranjang: RecyclerView ?= null
    var total: TextView ?= null
    var btn_beli: Button ?= null
    var v:View ?= null
    var tot: Int = 0;
    lateinit var listMenu:ArrayList<MyMenu>
    lateinit var listKeranjang:ArrayList<MyKeranjang>
    lateinit var shared: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_pembayaran, container, false)
        //EditText
        rv_keranjang = v?.findViewById(R.id.rv_keranjang)
        total = v?.findViewById(R.id.total)
        btn_beli = v?.findViewById(R.id.btn_beli)
        btn_beli?.setOnClickListener {
            shared.edit().clear().commit();
            Toast.makeText(requireContext(), "Pembelian Success" , Toast.LENGTH_SHORT).show()
            reload()
        }
        return v
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shared = requireContext().getSharedPreferences("keranjang" , Context.MODE_PRIVATE)
        listViewMenu()
    }

    override fun onResume() {
        super.onResume()
        tot = 0;
        listViewMenu()
    }

    fun reload() {
        tot = 0;
        listViewMenu()
    }

    fun countInit(toti: String, cl: String) {
        tot = tot - cl.toInt()
        tot += toti.toInt()
        total?.setText("Total :"+tot.toString())
    }

    fun countInit2(toti: String) {
        tot += toti.toInt()
        total?.setText("Total :"+tot.toString())
    }

    fun countMinInit(jum: String) {
        tot = tot - jum.toInt()
        total?.setText("Total :"+tot.toString())
    }

    fun listViewMenu() {
        listMenu = ArrayList()
        listKeranjang = ArrayList()
        listMenu.add(MyMenu("1", "Black Tea", "22000", "hot/iced", "https://blog.pinjammodal.id/wp-content/uploads/2019/07/coffeee-latte-696x494.jpg"))
        listMenu.add(MyMenu("2", "Lemon Tea", "24000", "hot/iced", "https://blog.pinjammodal.id/wp-content/uploads/2019/07/coffeee-latte-696x494.jpg"))
        listMenu.add(MyMenu("3", "Naughty Regal", "24000", "hot/iced", "https://blog.pinjammodal.id/wp-content/uploads/2019/07/coffeee-latte-696x494.jpg"))
        listMenu.add(MyMenu("4", "Naughty Oreo", "24000", "hot/iced", "https://blog.pinjammodal.id/wp-content/uploads/2019/07/coffeee-latte-696x494.jpg"))
        listMenu.add(MyMenu("5", "Americano", "15000", "hot/iced", "https://blog.pinjammodal.id/wp-content/uploads/2019/07/coffeee-latte-696x494.jpg"))
        listMenu.add(MyMenu("6", "Latte", "16000", "hot/iced", "https://blog.pinjammodal.id/wp-content/uploads/2019/07/coffeee-latte-696x494.jpg"))
        listMenu.add(MyMenu("7", "Irish", "17000", "hot/iced", "https://blog.pinjammodal.id/wp-content/uploads/2019/07/coffeee-latte-696x494.jpg"))
        listMenu.add(MyMenu("8", "Long Black", "18000", "hot", "https://blog.pinjammodal.id/wp-content/uploads/2019/07/coffeee-latte-696x494.jpg"))
        listMenu.add(MyMenu("9", "Manual Brew", "19000", "hot", "https://blog.pinjammodal.id/wp-content/uploads/2019/07/coffeee-latte-696x494.jpg"))
        listMenu.add(MyMenu("10", "Sukoco", "20000", "hot", "https://blog.pinjammodal.id/wp-content/uploads/2019/07/coffeee-latte-696x494.jpg"))
        listMenu.add(MyMenu("11", "Brownies", "15000", "dessert", "https://asset.kompas.com/crops/njbQ9wV94htOpofcxAPBH9SePok=/0x0:751x501/750x500/data/photo/2020/07/17/5f10ecfcc96bd.jpg"))
        listMenu.add(MyMenu("12", "Choco Ball", "8000", "dessert", "https://asset.kompas.com/crops/njbQ9wV94htOpofcxAPBH9SePok=/0x0:751x501/750x500/data/photo/2020/07/17/5f10ecfcc96bd.jpg"))
        listMenu.add(MyMenu("13", "Risoles", "9000", "dessert", "https://asset.kompas.com/crops/njbQ9wV94htOpofcxAPBH9SePok=/0x0:751x501/750x500/data/photo/2020/07/17/5f10ecfcc96bd.jpg"))
        listMenu.add(MyMenu("14", "Bitter Ballen", "8000", "dessert", "https://asset.kompas.com/crops/njbQ9wV94htOpofcxAPBH9SePok=/0x0:751x501/750x500/data/photo/2020/07/17/5f10ecfcc96bd.jpg"))
        listMenu.add(MyMenu("15", "Bleberry Cheese Cake", "25000", "dessert", "https://asset.kompas.com/crops/njbQ9wV94htOpofcxAPBH9SePok=/0x0:751x501/750x500/data/photo/2020/07/17/5f10ecfcc96bd.jpg"))

        for (i in 0..20) {
            val sess = shared.getString("id" + i.toString(), "defauld")
            if (sess.equals("") || sess.equals(null) || sess.equals("defauld")) {
            } else {
                listKeranjang.add(
                    MyKeranjang(
                        i.toString(),
                        listMenu.get(i).nama,
                        listMenu.get(i).harga,
                        listMenu.get(i).varian,
                        listMenu.get(i).url
                    )
                )
            }
        }
        tampilmenu()
    }
    fun tampilmenu() {
        rv_keranjang?.layoutManager= LinearLayoutManager(activity)
        rv_keranjang?.adapter=MyKeranjangAdapter(listKeranjang, this)
    }

}