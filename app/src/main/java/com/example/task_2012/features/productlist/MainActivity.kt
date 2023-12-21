package com.example.task_2012.features.productlist

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.task_2012.R
import com.example.task_2012.databinding.ActivityMainBinding
import com.example.task_2012.features.Product
import com.example.task_2012.features.newproduct.ProductActivity

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
    }


    private val newProductShow = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val item = result.data?.getParcelableExtra<Product>("product")
            println(item)
        }
    }

    fun onNextScreen() {
        val intent = Intent(this, ProductActivity::class.java)
        newProductShow.launch(intent)
    }

    fun observeAll() {
        viewModel.showProductObserve.observe(this) {
            if (it) {
                onNextScreen()
            }
        }
    }

    fun removeObserveAll() {
        viewModel.showProductObserve.removeObservers(this)
        viewModel.showProductObserve.postValue(false)
    }

    override fun onResume() {
        super.onResume()
        observeAll()
    }

    override fun onPause() {
        super.onPause()
        removeObserveAll()
    }

}