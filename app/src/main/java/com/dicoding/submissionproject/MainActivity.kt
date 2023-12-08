package com.dicoding.submissionproject

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.submissionproject.databinding.ActivityMainBinding
import java.lang.reflect.Array

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var rvProducts: RecyclerView
    private val list = ArrayList<ProductData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvProducts = findViewById(R.id.rv_products)
        rvProducts.setHasFixedSize(true)
        list.addAll(getListProducts())
        showRecycleList()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("Recycle")
    private fun getListProducts(): ArrayList<ProductData> {
        val productName = resources.getStringArray(R.array.data_name)
        val productDescription = resources.getStringArray(R.array.data_description)
        val productLogo = resources.obtainTypedArray(R.array.data_logo)
        val listProduct = ArrayList<ProductData>()
        for(i in productName.indices){
            val product = ProductData(productName[i], productDescription[i], productLogo.getResourceId(i, -1))
            listProduct.add(product)
        }
        return listProduct
    }

    private fun showRecycleList() {
        rvProducts.layoutManager = LinearLayoutManager(this)
        rvProducts.adapter = ListProductAdapter(list)
    }
}