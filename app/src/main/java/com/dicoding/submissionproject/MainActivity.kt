package com.dicoding.submissionproject

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

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
        val productHeadline = resources.getStringArray(R.array.data_headline)
        val productDescription = resources.getStringArray(R.array.data_description)
        val productLogo = resources.obtainTypedArray(R.array.data_logo)
        val listProduct = ArrayList<ProductData>()
        for(i in productName.indices){
            val product = ProductData(productName[i], productLogo.getResourceId(i, -1), productHeadline[i], productDescription[i])
            listProduct.add(product)
        }
        return listProduct
    }

    private fun showRecycleList() {
        rvProducts.layoutManager = LinearLayoutManager(this)
        val listProductAdapter = ListProductAdapter(list)
        rvProducts.adapter = listProductAdapter
        listProductAdapter.setItemOnClickCallback(object: ListProductAdapter.OnItemClickCallback {
            override fun onItemClicked(productData: ProductData) {
                showSelectedProduct(productData)
            }
        })
    }

    fun showSelectedProduct(productData: ProductData) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_PRODUCT, productData)
        startActivity(intent)
        Toast.makeText(this, "Anda memilih ${productData.name}", Toast.LENGTH_SHORT).show()
    }

}