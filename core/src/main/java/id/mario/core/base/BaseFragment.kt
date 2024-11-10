package id.mario.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<B : ViewBinding> : Fragment() {
    lateinit var binding: B

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = setViewBinding(inflater, container)

        setUpVariable()
        return binding.root
    }

    abstract fun setViewBinding(inflater: LayoutInflater, container: ViewGroup?): B
    abstract fun setUpVariable()

}