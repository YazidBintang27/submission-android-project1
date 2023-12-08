package com.dicoding.submissionproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.submissionproject.databinding.ItemRowProductBinding

class ListProductAdapter (private val listProduct: ArrayList<ProductData>) : RecyclerView.Adapter<ListProductAdapter.ListViewHolder>() {

    class ListViewHolder (var binding: ItemRowProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount() = listProduct.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, logo) = listProduct[position]
        holder.binding.ivLogo.setImageResource(logo)
        holder.binding.tvName.text = name
        holder.binding.tvDescription.text = description
    }

}