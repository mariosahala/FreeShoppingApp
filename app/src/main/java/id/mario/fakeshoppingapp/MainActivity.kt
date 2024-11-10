package id.mario.fakeshoppingapp

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import id.mario.core.base.BaseActivity
import id.mario.fakeshoppingapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private lateinit var navController: NavController


    override fun setViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun setUpVariable() {
        binding.apply {
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.frag_content) as NavHostFragment
            navController = navHostFragment.findNavController()

            bnvContent.apply {
                setupWithNavController(navController)
            }
        }
    }
}