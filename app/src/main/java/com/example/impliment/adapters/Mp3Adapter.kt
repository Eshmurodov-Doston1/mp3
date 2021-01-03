package com.example.impliment.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.impliment.R
import com.example.impliment.models.Mp3Class
import com.example.impliment.shered.MySharedPreferens
import com.example.impliment.utils.ItemTouchHelperAdaptr
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.mp3_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class Mp3Adapter(var context: Context,var list: ArrayList<Mp3Class>,var myItemClickListener: MyItemClickListener):
    RecyclerView.Adapter<Mp3Adapter.Vh>(),ItemTouchHelperAdaptr {
    inner class Vh(itemView:View):RecyclerView.ViewHolder(itemView){
          fun onBind(mp3Class: Mp3Class,position: Int){
              itemView.name_mp3.text = mp3Class.mp3_name
              itemView.name_se.text = mp3Class.name_se
              itemView.img_mp3.setImageResource(mp3Class.img!!.img!!)
              itemView.animation = AnimationUtils.loadAnimation(context,R.anim.anim)
              itemView.setOnClickListener {
                myItemClickListener.onItemClick(mp3Class,position)
              }
              itemView.setOnLongClickListener {
                  myItemClickListener.onItemLongClick(mp3Class,position)
                  true
              }
          }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(LayoutInflater.from(parent.context).inflate(R.layout.mp3_item,parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position],position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
    interface MyItemClickListener{
        fun onItemClick(mp3Class: Mp3Class,position: Int)
        fun onItemLongClick(mp3Class: Mp3Class,position: Int)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
            if (fromPosition<toPosition){
                for (i in fromPosition until toPosition){
                    Collections.swap(list,i,i+1)
                }
            }else{
                for (i in fromPosition downTo  toPosition+1){
                    Collections.swap(list,i,i-1)
                }
            }
        notifyItemMoved(fromPosition,toPosition)
    }

    override fun onItemDismiss(position: Int) {
       list.removeAt(position)
       notifyItemRemoved(position)
        var gson = Gson()
        MySharedPreferens.clear()
        if (MySharedPreferens.text!!.isEmpty()) {
            MySharedPreferens.text = gson.toJson(list)
        }
    }
}