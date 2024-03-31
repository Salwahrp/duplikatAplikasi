package com.ifs21006.duplikataplikasi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.ifs21006.duplikataplikasi.databinding.FragmentKoleksiBinding

class KoleksiFragment : Fragment() {
    private lateinit var binding: FragmentKoleksiBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKoleksiBinding
            .inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = binding.vpFragmentKoleksi
        val pagerAdapter = KoleksiPagerAdapter(requireActivity())
        viewPager.adapter = pagerAdapter
        pagerAdapter.info1 = "Lagu Belum Diputar"
        pagerAdapter.info2 = "Lagu Sudah Diputar"
        val tabs = binding.tabFragmentKoleksi
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = TAB_TITLES[position]
        }.attach()
    }
    companion object {
        private val TAB_TITLES = arrayOf(
            "Belum Diputar",
            "Sudah Diputar",
        )
    }
}