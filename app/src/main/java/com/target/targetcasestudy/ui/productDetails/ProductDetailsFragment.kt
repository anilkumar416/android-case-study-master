package com.target.targetcasestudy.ui.productDetails

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.target.targetcasestudy.databinding.ProductDetailFragmentBinding
import com.target.targetcasestudy.model.ProductData
import com.target.targetcasestudy.utils.Resource
import com.target.targetcasestudy.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {

    private var binding: ProductDetailFragmentBinding by autoCleared()
    private val viewModel: ProductDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProductDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("id")?.let { viewModel.start(it) }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.product.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    bindCharacter(it.data!!)
                }

                Resource.Status.ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {
                }
            }
        })
    }

    private fun bindCharacter(productData: ProductData) {

        binding.actionButton1.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

        if (productData.sale_price?.display_string.isNullOrBlank()) {
            binding.actionButton1.paintFlags = Paint.ANTI_ALIAS_FLAG
        }

        binding.primaryText.text = productData.title
        binding.actionButton1.text = productData.regular_price?.display_string
        binding.actionButton2.text = productData.sale_price?.display_string
        binding.supportingText.text = productData.description
        Glide.with(binding.root)
            .load(productData.image_url)
            .into(binding.mediaImage)

    }
}