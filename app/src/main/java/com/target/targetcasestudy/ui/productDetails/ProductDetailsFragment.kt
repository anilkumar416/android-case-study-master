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
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.target.targetcasestudy.databinding.ProductDetailFragmentBinding
import com.target.targetcasestudy.model.ProductData
import com.target.targetcasestudy.utils.Resource
import com.target.targetcasestudy.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import android.os.AsyncTask
import android.os.Handler
import android.os.Looper

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {

    private var binding: ProductDetailFragmentBinding by autoCleared()
    private val viewModel: ProductDetailViewModel by viewModels()
    private val args: ProductDetailsFragmentArgs by navArgs()

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
        args.receiveProductId.let { viewModel.start(it) }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.product.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    bindCharacter(it.data!!)
                }

                Resource.Status.ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun bindCharacter(productData: ProductData) {

        binding.apply {
            actionButton1.paintFlags =
                if (!productData.sale_price?.display_string.isNullOrBlank())
                    Paint.STRIKE_THRU_TEXT_FLAG
                else
                    Paint.ANTI_ALIAS_FLAG

            primaryText.text = productData.title
            actionButton1.text = productData.regular_price?.display_string
            actionButton2.text = productData.sale_price?.display_string

            supportingText.text = productData.description

            // Using the below method we can populate large string value
            // but even TextView has a limit and it hangs/freezes
//            Dispatch.asyncOnBackground {
//                val value = productData.description
//                Dispatch.asyncOnMain { supportingText.text = value }
//            }

            Glide.with(root)
                .load(productData.image_url)
                .into(mediaImage)
        }
    }
}


//object Dispatch {
//    fun asyncOnBackground(call: () -> Unit) {
//        AsyncTask.execute {
//            call()
//        }
//    }
//
//    fun asyncOnMain(call: () -> Unit) {
//        Handler(Looper.getMainLooper()).post {
//            call()
//        }
//    }
//}