package com.tsybulnik.shoppingsumin.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.WARN
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.tsybulnik.shoppingsumin.R

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var tx: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tx = findViewById(R.id.tvMa)
        tx.text = "mama"
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this){
          tx.text = it.toString()
            Log.d("TAG1","mistake")
            Log.d("TAG1",it.toString())
        }
        viewModel.getShopList()
    }
}