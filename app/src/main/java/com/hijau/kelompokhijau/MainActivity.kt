package com.hijau.kelompokhijau

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val menuTeks = arrayOf("Home", "Menu", "Keranjang")
    val menuIcon = arrayOf(R.drawable.ic_home, R.drawable.ic_menu, R.drawable.ic_penjualan)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ViewPagerAdapter(this)
        view_pager.setAdapter(adapter);
        TabLayoutMediator(tab_layout, view_pager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text = menuTeks[position]
                tab.icon = ResourcesCompat.getDrawable(resources, menuIcon[position], null)
            }).attach()
    }
    fun reload() {
        val fragment: PembayaranFragment =
            getSupportFragmentManager().findFragmentByTag("PembayaranFragment") as PembayaranFragment
        getSupportFragmentManager().beginTransaction().detach(fragment).attach(fragment).commit()
    }
}