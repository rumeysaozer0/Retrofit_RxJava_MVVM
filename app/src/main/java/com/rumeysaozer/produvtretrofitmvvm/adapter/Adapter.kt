package com.rumeysaozer.produvtretrofitmvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rumeysaozer.produvtretrofitmvvm.R
import com.rumeysaozer.produvtretrofitmvvm.mod.ProductItem
import com.rumeysaozer.produvtretrofitmvvm.util.downloadFromUrl
import com.rumeysaozer.produvtretrofitmvvm.util.placeholderProgressBar


class Adapter (val productList: ArrayList<ProductItem>): RecyclerView.Adapter<Adapter.ProductViewHolder>(){
    class ProductViewHolder (var view: View): RecyclerView.ViewHolder(view){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_row, parent,false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.title1).text = productList[position].title
        holder.view.findViewById<TextView>(R.id.description1).text = productList[position].category
        holder.view.setOnClickListener {

        }
        holder.view.findViewById<ImageView>(R.id.imageView).downloadFromUrl(productList[position].image,
            placeholderProgressBar(holder.view.context))
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun updateProductList(newProductList: List<ProductItem>){
        productList.clear()
        productList.addAll(newProductList)
        notifyDataSetChanged()
    }
}