package id.mario.fakeshoppingapp.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import id.mario.core.base.BaseFragment
import id.mario.fakeshoppingapp.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun setViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)

    override fun setUpVariable() {
    }
}