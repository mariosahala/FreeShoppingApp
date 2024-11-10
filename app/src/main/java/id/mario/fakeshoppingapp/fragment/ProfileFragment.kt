package id.mario.fakeshoppingapp.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import id.mario.core.base.BaseFragment
import id.mario.fakeshoppingapp.databinding.FragmentProfileBinding


class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    override fun setViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProfileBinding = FragmentProfileBinding.inflate(layoutInflater, container, false)


    override fun setUpVariable() {
    }
}