package com.dicoding.submissionproject

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.submissionproject.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PRODUCT = "extra_product"
    }

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val product = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_PRODUCT, ProductData::class.java)
        }else {
            intent.getParcelableExtra(EXTRA_PRODUCT)
        }

        if (product != null) {
            binding.ivLogo.setImageResource(product.image)
            binding.tvName.text = product.name
            binding.tvDescription.text = product.description
        }

        binding.ivActionShare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, product!!.description)
            startActivity(Intent.createChooser(intent, "Share with"))
        }
    }
}