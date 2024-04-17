package com.example.shoeapplication.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.shoeapplication.actitvity.DetailActivity
import com.example.shoeapplication.databinding.ViewholderBrandBinding
import com.example.shoeapplication.databinding.ViewholderRecommendationsBinding
import com.example.shoeapplication.models.ItemModel

class PopularAdapter(val items:MutableList<ItemModel>):RecyclerView.Adapter<PopularAdapter.Viewholder>() {

    private var context: Context?=null
 class Viewholder(val binding: ViewholderRecommendationsBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularAdapter.Viewholder {
        context=parent.context
        val binding = ViewholderRecommendationsBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: PopularAdapter.Viewholder, position: Int) {
        holder.binding.titleTxt.text= items[position].title
        holder.binding.price.text="₹"+items[position].price.toString()
        holder.binding.starNum.text=items[position].rating.toString()

        val requestOptions = RequestOptions().transform(CenterCrop())
        Glide.with(holder.itemView.context)
            .load(items[position].picUrl[0])
            .apply(requestOptions)
            .into(holder.binding.recommendationIv1
            )

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context,DetailActivity::class.java)
            intent.putExtra("object", items[position])
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}