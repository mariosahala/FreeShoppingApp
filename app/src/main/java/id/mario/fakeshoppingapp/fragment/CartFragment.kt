package id.mario.fakeshoppingapp.fragment

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.mario.core.base.BaseFragment
import id.mario.core.util.gone
import id.mario.core.util.visible
import id.mario.fakeshoppingapp.adapters.CartAdapter
import id.mario.fakeshoppingapp.databinding.FragmentCartBinding
import id.mario.fakeshoppingapp.viewmodel.CartViewModel

@AndroidEntryPoint
class CartFragment : BaseFragment<FragmentCartBinding>() {
    private lateinit var cartAdapter: CartAdapter

    private val cartViewModel by viewModels<CartViewModel>()
    override fun setViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentCartBinding = FragmentCartBinding.inflate(layoutInflater, container, false)

    override fun setUpVariable() {
        binding.apply {
            cartAdapter = CartAdapter()
            rvCartMarket.adapter = cartAdapter
            rvCartMarket.layoutManager = LinearLayoutManager(requireContext())

            cartViewModel.cartLineItems.observe(viewLifecycleOwner) { cartItem ->

                cartAdapter.differ.submitList(cartItem)
                cartAdapter.onClick = { data ->
                    cartViewModel.removeOnlyOneItemFromCartLine(data)
                }
                checkCartQuantity()
            }
        }
    }

    private fun checkCartQuantity() {
        binding.apply {
            if (cartAdapter.itemCount == 0) {
                Toast.makeText(requireContext(), "Cart Empty", Toast.LENGTH_SHORT).show()

                ivCartImage.visible()
                tvEmptyItemCart.visible()
                btnStartShopping.visible()
                rvCartMarket.gone()
                btnCheckout.isEnabled = false

            } else {
                btnCheckout.setOnClickListener {
                    showAlertDialog()
                }
            }
        }
    }

    private fun showAlertDialog() {
        AlertDialog.Builder(requireActivity()).setTitle("ORDER")
            .setMessage("Are you sure you want to order ?").setPositiveButton("Yes") { _, _ ->
            }.setNegativeButton("Not Now") { dialog, _ ->
                dialog.dismiss()
            }

            .create().show()
    }
}