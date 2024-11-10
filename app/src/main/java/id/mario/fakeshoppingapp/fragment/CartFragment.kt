package id.mario.fakeshoppingapp.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import id.mario.core.base.BaseFragment
import id.mario.fakeshoppingapp.databinding.FragmentCartBinding

class CartFragment : BaseFragment<FragmentCartBinding>() {
    override fun setViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCartBinding = FragmentCartBinding.inflate(layoutInflater, container, false)

    override fun setUpVariable() {
    }
}