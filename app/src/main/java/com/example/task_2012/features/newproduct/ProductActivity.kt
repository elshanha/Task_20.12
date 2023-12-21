package com.example.task_2012.features.newproduct

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.task_2012.databinding.ProductActivityBinding
import com.example.task_2012.features.Product
import java.time.Duration

class ProductActivity : AppCompatActivity() {
    lateinit var binding: ProductActivityBinding
    lateinit var viewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProductActivityBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
    }

    fun observeAllProduct() {
        viewModel.addProductObserve.observe(this) {
        }

        viewModel.getProduct.observe(this) {
            val intent = Intent()
            intent.putExtra("product", it)
            setResult(RESULT_OK, intent)
            finish()
        }

        viewModel.errorMessage.observe(this) {
            if (!it.isNullOrEmpty()) {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun removeObserveAllProduct() {
        viewModel.addProductObserve.removeObservers(this)
        viewModel.addProductObserve.postValue(false)
    }

    override fun onResume() {
        super.onResume()
        observeAllProduct()
    }

    override fun onPause() {
        super.onPause()
        removeObserveAllProduct()
    }

}