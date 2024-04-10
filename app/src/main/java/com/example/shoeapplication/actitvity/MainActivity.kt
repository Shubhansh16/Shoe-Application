package com.example.shoeapplication.actitvity

import android.os.Bundle
import android.view.View
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.shoeapplication.adapter.BrandAdapter
import com.example.shoeapplication.adapter.PopularAdapter
import com.example.shoeapplication.adapter.SliderAdapter
import com.example.shoeapplication.databinding.ActivityMainBinding
import com.example.shoeapplication.models.SliderModel
import com.example.shoeapplication.viewModel.MainViewModel

class MainActivity : BaseActivity() {

    private val viewModel= MainViewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBanner()
        initBrand()
        initPopular()
    }

    private fun initBanner(){
        binding.progressBarBanner.visibility = View.VISIBLE
        viewModel.banners.observe(this, Observer { items->
           banners(items)
           binding.progressBarBanner.visibility=View.GONE
        })
        viewModel.loadBanners()
    }

    private fun initBrand(){
        binding.progressBarBrand.visibility=View.VISIBLE
        viewModel.brands.observe(this, Observer {
            binding.viewBrand.layoutManager=LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
            binding.viewBrand.adapter=BrandAdapter(it)
            binding.progressBarBrand.visibility=View.GONE
        })
        viewModel.loadBrand()
    }

    private fun initPopular(){
        binding.progressBarBrand.visibility=View.VISIBLE
        viewModel.popular.observe(this, Observer {
            binding.viewPopular.layoutManager= GridLayoutManager(this@MainActivity,2)
            binding.viewPopular.adapter= PopularAdapter(it)
            binding.progressBarPopular.visibility=View.GONE
        })
        viewModel.loadPopular()
    }


    private fun banners(image:List<SliderModel>){
        binding.viewPagerSlider.adapter= SliderAdapter(image,binding.viewPagerSlider)
        binding.viewPagerSlider.clipToPadding = false
        binding.viewPagerSlider.clipChildren = false
        binding.viewPagerSlider.offscreenPageLimit =3
        binding.viewPagerSlider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }
        binding.viewPagerSlider.setPageTransformer(compositePageTransformer)
        if (image.size>1){
            binding.dotsIndicator.visibility = View.VISIBLE
            binding.dotsIndicator.attachTo(binding.viewPagerSlider)
        }
    }
}