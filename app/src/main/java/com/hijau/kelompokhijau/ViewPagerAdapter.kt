package com.hijau.kelompokhijau

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.fragment.app.FragmentStatePagerAdapter


class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    private val JUMLAH_MENU = 3

    override fun createFragment(position: Int): Fragment {
        when (position){
            0 -> { return UtamaFragment()}
            1 -> { return MenuFragment()}
            2 -> { return PembayaranFragment()}
            else -> { return MenuFragment()}
        }
    }

    override fun getItemCount(): Int {
        return  JUMLAH_MENU
    }
}