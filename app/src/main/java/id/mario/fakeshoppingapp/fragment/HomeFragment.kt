package id.mario.fakeshoppingapp.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.mario.core.base.BaseFragment
import id.mario.core.util.gone
import id.mario.core.util.toast
import id.mario.fakeshoppingapp.adapters.ProductMarketAdapter
import id.mario.fakeshoppingapp.databinding.FragmentHomeBinding
import id.mario.fakeshoppingapp.viewmodel.ProductsViewModel
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private lateinit var productsItemAdapter: ProductMarketAdapter

    val productsViewModel by viewModels<ProductsViewModel>()
    override fun setViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)

    override fun setUpVariable() {
        subscribeToProductsObserver()
        productsViewModel.getProducts()
    }

    private fun subscribeToProductsObserver() {
        lifecycleScope.launch {
            productsViewModel.products.collect { productState ->
                binding.apply {
                    if (productState.isLoading) {
                        shimmerLayout.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                        shimmerLayout.startShimmer()
                    }

                    if (productState.error != null && !productState.isLoading) {
                        productState.error?.let {
                            toast(message = it)
                        }
                        shimmerLayout.stopShimmer()
                    }

                    if (productState.products.isNotEmpty() && !productState.isLoading) {
                        shimmerLayout.stopShimmer()
                        shimmerLayout.visibility = View.GONE
                        recyclerView.visibility = View.VISIBLE
                        productsItemAdapter = ProductMarketAdapter()
                        productsItemAdapter.differ.submitList(productState.products)
                        recyclerView.adapter = productsItemAdapter
                        recyclerView.layoutManager =
                            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    }

                    if (productState.products.isEmpty() && !productState.isLoading) {
                        binding.apply {
                            shimmerLayout.stopShimmer()
                            shimmerLayout.gone()
                            recyclerView.gone()
                        }
                    }
                }
            }
        }
    }
}