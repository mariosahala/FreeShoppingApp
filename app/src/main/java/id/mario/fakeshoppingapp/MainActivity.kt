package id.mario.fakeshoppingapp

import id.mario.core.base.BaseActivity
import id.mario.fakeshoppingapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun setViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun setUpVariable() {
        binding.apply {

        }
    }
}