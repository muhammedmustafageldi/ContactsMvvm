package com.swanky.contactsmvvm.activities

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.swanky.contactsmvvm.R
import com.swanky.contactsmvvm.adapters.ViewPagerAdapter
import com.swanky.contactsmvvm.databinding.ActivityAddContactBinding
import com.swanky.contactsmvvm.viewmodels.AddContactViewModel

class AddContactActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityAddContactBinding
    private val viewModel: AddContactViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_contact)
        dataBinding.master = this
        dataBinding.lifecycleOwner = this
        dataBinding.viewModel = viewModel

        setupViewPager()
    }

    private fun setupViewPager() {
        val viewPager2 = dataBinding.addViewPager2
        val viewPagerAdapter = ViewPagerAdapter(viewModel.getViewPagerFragments(), this.supportFragmentManager, lifecycle)
        viewPager2.adapter = viewPagerAdapter

        // Set dots indicator
        dataBinding.wormDotsIndicator.attachTo(viewPager2)

        // Handle back press
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (viewPager2.currentItem == 0) {
                    finish()
                } else {
                    previousPage()
                }
            }
        })
    }

    fun nextPage() {
        val viewPager2 = dataBinding.addViewPager2
        val currentPage = viewPager2.currentItem
        Handler(Looper.getMainLooper()).post {
            viewPager2.setCurrentItem(currentPage + 1, true)
        }
    }

    fun previousPage() {
        val viewPager2 = dataBinding.addViewPager2
        val currentPage = viewPager2.currentItem
        Handler(Looper.getMainLooper()).post {
            viewPager2.setCurrentItem(currentPage - 1, true)
        }
    }

    fun completeSave(){
        viewModel.saveContactToDatabase()
        finish()
    }


}