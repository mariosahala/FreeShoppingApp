package id.mario.fakeshoppingapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.mario.core.model.CartEntity
import id.mario.core.util.loadImage
import id.mario.fakeshoppingapp.R
import id.mario.fakeshoppingapp.databinding.ItemCartMarketBinding

class CartAdapter : RecyclerView.Adapter<CartAdapter.MyViewHolder>() {
    var onClick: ((CartEntity) -> Unit) = {}


    private val diffCallBack = object : DiffUtil.ItemCallback<CartEntity>() {
        override fun areItemsTheSame(
            oldItem: CartEntity,
            newItem: CartEntity
        ): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(
            oldItem: CartEntity,
            newItem: CartEntity
        ): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, diffCallBack)

    inner class MyViewHolder(private val binding: ItemCartMarketBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CartEntity) {
            binding.apply {
                ivProductCart.loadImage(data.image)
                tvProductName.text = data.title
                tvPriceProduct.text= "$" + data.price.toString()
                btnRemoveProductCart.setOnClickListener {
                    onClick.invoke(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemCartMarketBinding.bind(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_cart_market, parent, false
                )
            )
        )

    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }
}