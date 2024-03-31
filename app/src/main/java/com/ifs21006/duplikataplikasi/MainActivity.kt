package com.ifs21006.duplikataplikasi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import com.ifs21006.duplikataplikasi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        setupAction()
    }
    private fun setupView() {
        binding.navView.setCheckedItem(R.id.nav_home)
        binding.inAppBar.toolbar.overflowIcon =
            ContextCompat.getDrawable(this, R.drawable.ic_more_vert)
        loadFragment(FLAG_FRAGMENT_CARI)
    }
    private fun setupAction() {
        binding.inAppBar.toolbar.setNavigationOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        binding.inAppBar.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_add -> {
                    loadFragment(FLAG_FRAGMENT_HOME, "Memilih menu Add!")
                    true
                }
                else -> true
            }
        }
        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    loadFragment(FLAG_FRAGMENT_HOME)
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_profile -> {
                    loadFragment(FLAG_FRAGMENT_HOME, "Memilih menu Profile!")
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_notes -> {
                    loadFragment(FLAG_FRAGMENT_HOME, "Memilih menu Notes!")
                    binding.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                else -> true
            }
        }
        binding.inAppBar.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    loadFragment(FLAG_FRAGMENT_HOME)
                    true
                }
                R.id.navigation_cari -> {
                    loadFragment(FLAG_FRAGMENT_CARI)
                    true
                }
                R.id.navigation_koleksi -> {
                    loadFragment(FLAG_FRAGMENT_KOLEKSI)
                    true
                }
                R.id.navigation_premium -> {
                    loadFragment(FLAG_FRAGMENT_PREMIUM)
                    true
                }
                else -> true
            }
        }
    }
    private fun loadFragment(flag: String, message: String? = null) {
        val fragmentManager = supportFragmentManager
        val fragmentContainerId =
            binding.inAppBar.inContentMain.frameContainer.id
        when (flag) {
            FLAG_FRAGMENT_HOME -> {
                binding.inAppBar.bottomNavView
                    .menu
                    .findItem(R.id.navigation_home)
                    .setChecked(true)
                val homeFragment = HomeFragment()
                val bundle = Bundle().apply {
                    this.putString(
                        HomeFragment.EXTRA_MESSAGE,
                        message ?: "Belum ada menu yang dipilih!"
                    )
                }
                homeFragment.arguments = bundle
                fragmentManager
                    .beginTransaction()
                    .replace(
                        fragmentContainerId,
                        homeFragment,
                        HomeFragment::class.java.simpleName
                    )
                    .commit()
            }
            FLAG_FRAGMENT_CARI -> {
                val cariFragment = CariFragment()
                val fragment = fragmentManager
                    .findFragmentByTag(CariFragment::class.java.simpleName)
                if (fragment !is CariFragment) {
                    fragmentManager
                        .beginTransaction()
                        .replace(
                            fragmentContainerId,
                            cariFragment,
                            CariFragment::class.java.simpleName
                        )
                        .commit()
                }
            }
            FLAG_FRAGMENT_KOLEKSI -> {
                val koleksiFragment = KoleksiFragment()
                val fragment = fragmentManager
                    .findFragmentByTag(KoleksiFragment::class.java.simpleName)
                if (fragment !is KoleksiFragment) {
                    fragmentManager
                        .beginTransaction()
                        .replace(
                            fragmentContainerId,
                            koleksiFragment,
                            KoleksiFragment::class.java.simpleName
                        )
                        .commit()
                }
            }
            FLAG_FRAGMENT_PREMIUM -> {
                val premiumFragment = PremiumFragment()
                val fragment = fragmentManager
                    .findFragmentByTag(PremiumFragment::class.java.simpleName)
                if (fragment !is PremiumFragment) {
                    fragmentManager
                        .beginTransaction()
                        .replace(
                            fragmentContainerId,
                            premiumFragment,
                            PremiumFragment::class.java.simpleName
                        )
                        .commit()
                }
            }
        }
    }
    companion object {
        const val FLAG_FRAGMENT_HOME = "fragment_home"
        const val FLAG_FRAGMENT_CARI = "fragment_cari"
        const val FLAG_FRAGMENT_KOLEKSI = "fragment_koleksi"
        const val FLAG_FRAGMENT_PREMIUM = "fragment_premium"
    }
}


