package com.example.impliment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.impliment.adapters.Mp3Adapter
import com.example.impliment.models.Mp3Class
import com.example.impliment.shered.MySharedPreferens
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_mp3_list_class.*

class Mp3ListClass : AppCompatActivity() {
    lateinit var mp3List:ArrayList<Mp3Class>
    lateinit var mp3Adapter: Mp3Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mp3_list_class)
        MySharedPreferens.init(this)
        loadData()
        mp3Adapter = Mp3Adapter(this,mp3List,object:Mp3Adapter.MyItemClickListener{
            override fun onItemClick(mp3Class: Mp3Class, position: Int) {
              var intent = Intent(this@Mp3ListClass,Mp3ClickMusick::class.java)
                var bundle = Bundle()
                bundle.putString("name",mp3Class.mp3_name)
                bundle.putInt("img", mp3Class.img!!.img!!)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })
        rv.adapter = mp3Adapter
    }

    private fun loadData() {
        mp3List = ArrayList()
        var str = MySharedPreferens.text
        var gson = Gson()
        if (str!!.isNotEmpty()){
            var type = object:TypeToken<ArrayList<Mp3Class>>(){}.type
            mp3List.addAll(gson.fromJson(str,type))
        }
    }
}