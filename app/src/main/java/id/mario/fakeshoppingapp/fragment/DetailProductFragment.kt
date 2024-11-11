package id.mario.fakeshoppingapp.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import id.mario.core.base.BaseFragment
import id.mario.core.util.loadImage
import id.mario.fakeshoppingapp.databinding.FragmentDetailProductBinding
import id.mario.fakeshoppingapp.viewmodel.CartViewModel

@AndroidEntryPoint
class DetailProductFragment : BaseFragment<FragmentDetailProductBinding>() {
    private val viewModel by viewModels<CartViewModel>()
    private val args: DetailProductFragmentArgs by navArgs()
    override fun setViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailProductBinding =
        FragmentDetailProductBinding.inflate(layoutInflater, container, false)

    override fun setUpVariable() {
        binding.apply {
            val dataProductDetail = args.productitem
            tvNameDetailProduct.text = dataProductDetail.title
            tvPriceDetailProduct.text = "$" + dataProductDetail.price.toString()
            tvDescDetailProduct.text = dataProductDetail.description
            ivDetailImageProduct.loadImage(dataProductDetail.image)

            btnAddProductToCart.setOnClickListener {
                viewModel.insertItemToCartLine(dataProductDetail)
                findNavController().navigateUp()
            }
        }
    }
}