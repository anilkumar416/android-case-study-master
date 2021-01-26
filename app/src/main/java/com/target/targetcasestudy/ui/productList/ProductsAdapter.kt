package com.target.targetcasestudy.ui.productList

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.target.targetcasestudy.databinding.ItemCharacterBinding
import com.target.targetcasestudy.model.ProductData

class ProductsAdapter : RecyclerView.Adapter<ProductViewHolder>() {

    private val items = ArrayList<ProductData>()

    fun setItems(items: ArrayList<ProductData>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding: ItemCharacterBinding =
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) =
        holder.bind(items[position])

}

class ProductViewHolder(private val itemBinding: ItemCharacterBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    private lateinit var product: ProductData

    @SuppressLint("SetTextI18n")
    fun bind(item: ProductData) {
        this.product = item
        itemBinding.name.text = item.title
        itemBinding.speciesAndStatus.text =
            """${item.sale_price?.display_string} - ${item.regular_price?.display_string}"""
        Glide.with(itemBinding.root)
            .load(item.image_url)
            .transform(CircleCrop())
            .into(itemBinding.image)
    }

}
