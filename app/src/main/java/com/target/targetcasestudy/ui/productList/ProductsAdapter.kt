package com.target.targetcasestudy.ui.productList

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.target.targetcasestudy.databinding.ItemProductsBinding
import com.target.targetcasestudy.model.ProductData
import com.target.targetcasestudy.ui.ProductListFragmentDirections

class ProductsAdapter :
    ListAdapter<ProductData, RecyclerView.ViewHolder>(ProductsDiffUtil())  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProductViewHolder(
            ItemProductsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class ProductViewHolder(
        private val binding: ItemProductsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ProductData) {

            binding.apply {

                supportingText.text = item.title

                actionButton1.paintFlags =
                    if (!item.sale_price?.display_string.isNullOrBlank())
                        Paint.STRIKE_THRU_TEXT_FLAG
                    else
                        Paint.ANTI_ALIAS_FLAG

                actionButton1.text = item.regular_price?.display_string

                actionButton2.text = item.sale_price?.display_string
                
                Glide.with(root)
                    .load(item.image_url)
                    .into(mediaImage)

                root.setOnClickListener {
                    val direction =
                        ProductListFragmentDirections.actionDealListFragmentToProductDetailsFragment(
                            item.id
                        )
                    it.findNavController().navigate(direction)

                }
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val picture = getItem(position)
        (holder as ProductViewHolder).bind(picture)
    }
}

class ProductsDiffUtil : DiffUtil.ItemCallback<ProductData>() {
    override fun areItemsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
        return oldItem == newItem
    }
}

