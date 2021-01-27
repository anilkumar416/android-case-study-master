package com.target.targetcasestudy.ui.productList

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.target.targetcasestudy.databinding.ItemCharacterBinding
import com.target.targetcasestudy.model.ProductData

class ProductsAdapter(private val listener: CharacterItemListener) :
    RecyclerView.Adapter<ProductViewHolder>() {

    interface CharacterItemListener {
        fun onClickedCharacter(characterId: Int)
    }

    private val items = ArrayList<ProductData>()

    fun setItems(items: ArrayList<ProductData>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding: ItemCharacterBinding =
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) =
        holder.bind(items[position])

}

class ProductViewHolder(
    private val itemBinding: ItemCharacterBinding,
    private val listener: ProductsAdapter.CharacterItemListener
) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var product: ProductData

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: ProductData) {
        this.product = item
        itemBinding.supportingText.text = item.title

        itemBinding.actionButton1.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

        if (item.sale_price?.display_string.isNullOrBlank()) {
            itemBinding.actionButton1.paintFlags = Paint.ANTI_ALIAS_FLAG
        }

        itemBinding.actionButton1.text = item.regular_price?.display_string
        itemBinding.actionButton2.text = item.sale_price?.display_string
        Glide.with(itemBinding.root)
            .load(item.image_url)
            .into(itemBinding.mediaImage)
    }

    override fun onClick(v: View?) {
        listener.onClickedCharacter(product.id)
    }

}