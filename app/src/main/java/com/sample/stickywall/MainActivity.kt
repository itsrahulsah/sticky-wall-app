package com.sample.stickywall

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sample.stickywall.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val navController = findNavController(R.id.nav_host_fragment)
//        setupActionBarWithNavController(navController)
    }


}