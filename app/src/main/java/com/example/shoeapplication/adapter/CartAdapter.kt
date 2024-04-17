package com.example.shoeapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.shoeapplication.databinding.ActivityDetailBinding
import com.example.shoeapplication.databinding.ViewholderCartBinding
import com.example.shoeapplication.helper.ChangeNumberItemsListener
import com.example.shoeapplication.helper.ManagmentCart
import com.example.shoeapplication.models.ItemModel
import java.lang.reflect.Array
import kotlin.time.times

class CartAdapter(private val listItemSelected:ArrayList<ItemModel>,context: Context,
    var changedNumberItemsListener:ChangeNumberItemsListener?=null):RecyclerView.Adapter<CartAdapter.Viewholder>() {

        class Viewholder(val binding:ViewholderCartBinding):RecyclerView.ViewHolder(binding.root){

        }
    private val managementCart =ManagmentCart(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.Viewholder {
        val binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: CartAdapter.Viewholder, position: Int) {
        val item= listItemSelected[position]

        holder.binding.TitleId.text=item.title
        holder.binding.feeEachItem.text= "₹${item.price}"
        holder.binding.totalFeeItem.text ="₹${Math.round(item.numberInCart*item.price)}"
        holder.binding.numberItemTxt.text=item.numberInCart.toString()

        Glide.with(holder.itemView.context)
            .load(item.picUrl[0])
            .apply(RequestOptions().transform(CenterCrop()))
            .into(holder.binding.shoesId)

        holder.binding.plus.setOnClickListener {
          managementCart.plusItem(listItemSelected,position,object :ChangeNumberItemsListener{
              override fun onChanged() {
                  notifyDataSetChanged()
                  changedNumberItemsListener?.onChanged()
              }

          })
        }

        holder.binding.minus.setOnClickListener {
            managementCart.minusItem(listItemSelected,position,object :ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged()
                    changedNumberItemsListener?.onChanged()
                }

            })
        }
    }

    override fun getItemCount(): Int {
       return listItemSelected.size
    }
}