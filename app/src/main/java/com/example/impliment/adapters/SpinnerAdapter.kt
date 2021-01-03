package com.example.impliment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.impliment.R
import com.example.impliment.models.ImgClass
import com.example.impliment.models.Mp3Class
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_mp3_click_musick.view.*
import kotlinx.android.synthetic.main.activity_mp3_click_musick.view.img_mp3_1
import kotlinx.android.synthetic.main.spinner_item.view.*

class SpinnerAdapter(var list: ArrayList<ImgClass>):BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
      return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var itemView:View
        if (convertView == null){
            itemView = LayoutInflater.from(parent?.context).inflate(R.layout.spinner_item,parent,false)
        }else{
            itemView = convertView
        }
       // Picasso.get().load(list[position].img).into(itemView.img_m1)
        return itemView
    }
}