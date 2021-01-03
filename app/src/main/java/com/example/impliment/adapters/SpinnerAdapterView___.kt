package com.example.impliment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.impliment.R
import com.example.impliment.models.ImgClass
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.spinner_item.view.*

class SpinnerAdapterView___(var list: ArrayList<ImgClass>):BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): ImgClass {
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
        itemView.img_m1.setImageResource(list[position].img!!)
        itemView.text_m1.text = list[position].imgName
        return itemView
    }
}