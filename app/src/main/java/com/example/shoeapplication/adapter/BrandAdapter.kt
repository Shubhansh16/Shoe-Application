package com.example.shoeapplication.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shoeapplication.R
import com.example.shoeapplication.databinding.ViewholderBrandBinding
import com.example.shoeapplication.models.BrandModel

class BrandAdapter(val items:MutableList<BrandModel>):RecyclerView.Adapter<BrandAdapter.Viewholder>() {

    private var selectedPosition=-1
    private var lastSelectedPosition = -1
    private lateinit var context: Context

    class Viewholder(val binding:ViewholderBrandBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandAdapter.Viewholder {
       context=parent.context
        val binding= ViewholderBrandBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: BrandAdapter.Viewholder, position: Int) {
        val item =items[position]
        holder.binding.viewholderTitle.text=item.title

        Glide.with(holder.itemView.context)
            .load(item.picUrl)
            .into(holder.binding.pic)

        holder.binding.root.setOnClickListener {
            lastSelectedPosition=selectedPosition
            selectedPosition=position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }
        holder.binding.viewholderTitle.setTextColor(context.resources.getColor(R.color.white))
        if (selectedPosition==position){
            holder.binding.pic.setBackgroundResource(0)
            holder.binding.mainLayout.setBackgroundResource(R.drawable.purple_btn_bg)
            ImageViewCompat.setImageTintList(holder.binding.pic, ColorStateList.valueOf(context.getColor(R.color.white)))

            holder.binding.viewholderTitle.visibility = View.VISIBLE

        } else {
            holder.binding.pic.setBackgroundResource(R.drawable.grey_bg)
            holder.binding.mainLayout.setBackgroundResource(0)
            ImageViewCompat.setImageTintList(holder.binding.pic, ColorStateList.valueOf(context.getColor(R.color.black)))

            holder.binding.viewholderTitle.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
       return items.size
    }
}