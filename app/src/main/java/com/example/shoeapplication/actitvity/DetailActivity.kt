package com.example.shoeapplication.actitvity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoeapplication.R
import com.example.shoeapplication.adapter.ColorAdapter
import com.example.shoeapplication.adapter.SizeAdapter
import com.example.shoeapplication.adapter.SliderAdapter
import com.example.shoeapplication.databinding.ActivityDetailBinding
import com.example.shoeapplication.helper.ManagmentCart
import com.example.shoeapplication.models.ItemModel
import com.example.shoeapplication.models.SliderModel


class DetailActivity : BaseActivity() {

    private lateinit var binding:ActivityDetailBinding
    private lateinit var item:ItemModel
    private var numberOder= 1
    private lateinit var managementCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managementCart = ManagmentCart(this)
        getBundle()
        banners()
        initLists()
    }

    private fun getBundle() {
        item = intent.getParcelableExtra("object")!!

        binding.titleTxt.text = item.title
        binding.descriptionTxt.text = item.description
        binding.priceTxt.text= "₹"+item.price
        binding.ratingTxt.text ="${item.rating} Rating"
        binding.addToCartBtn.setOnClickListener {
            item.numberInCart = numberOder
            managementCart.insertFood(item)
        }
        binding.ivBackBtn.setOnClickListener {
            finish()
        }
        binding.cartBtn.setOnClickListener{

        }
    }

    private fun banners(){
        val sliderItems= ArrayList<SliderModel>()
        for (imageUrl in item.picUrl){
            sliderItems.add(SliderModel(imageUrl))
        }
        binding.slider.adapter= SliderAdapter(sliderItems,binding.slider)

        binding.slider.clipToPadding = true
        binding.slider.clipChildren = true
        binding.slider.offscreenPageLimit =1

        if (sliderItems.size >0)
        {
            binding.dotsIndicator.visibility= View.VISIBLE
            binding.dotsIndicator.attachTo(binding.slider)
        }

    }

    private fun initLists(){
        val sizeList= ArrayList<String>()
        for (size in item.size){
            sizeList.add(size.toString())
        }

        binding.sizeList.adapter = SizeAdapter(sizeList)
        binding.sizeList.layoutManager= LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        val colorList=ArrayList<String>()
        for (imageUrl in item.size){
            colorList.add(imageUrl)
        }
        binding.colorList.adapter = ColorAdapter(colorList)
        binding.colorList.layoutManager= LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
    }
}