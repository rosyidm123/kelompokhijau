package com.hijau.kelompokhijau

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_menu.*

class MenuFragment : Fragment() {
    lateinit var listMenu:ArrayList<MyMenu>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu,container,false)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    private fun listMenu() {
        listMenu = ArrayList()
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

    }
    private fun tampilmenu() {
        rv_listMyMenu.layoutManager=LinearLayoutManager(activity)
        rv_listMyMenu.adapter=MyMenuFragment(listMenu)
    }

    private fun initView(){
        listMenu()
        tampilmenu()
    }
    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }
}