package id.mario.fakeshoppingapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.mario.core.model.ProductsItemsModel
import id.mario.core.util.loadImage
import id.mario.fakeshoppingapp.R
import id.mario.fakeshoppingapp.databinding.ItemProductBinding
import id.mario.fakeshoppingapp.fragment.HomeFragmentDirections

class ProductMarketAdapter : RecyclerView.Adapter<ProductMarketAdapter.MyViewHolder>() {
    private val diffCallBack = object : DiffUtil.ItemCallback<ProductsItemsModel>() {
        override fun areItemsTheSame(
            oldItem: ProductsItemsModel,
            newItem: ProductsItemsModel
        ): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(
            oldItem: ProductsItemsModel,
            newItem: ProductsItemsModel
        ): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, diffCallBack)

    inner class MyViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ProductsItemsModel) {
            binding.apply {
                tvPriceProductItem.text = "$" + data.price.toString()
                tvTitleProduct.text = data.title
                tvNameCategory.text = data.category
                ivProduct.loadImage(data.image)
                root.setOnClickListener {
                    val directions =
                        HomeFragmentDirections.actionHomeFragmentToDetailProductFragment(data)
                    it.findNavController().navigate(directions)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemProductBinding.bind(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_product, parent, false
                )
            )
        )
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }
}