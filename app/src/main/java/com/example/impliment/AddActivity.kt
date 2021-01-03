package com.example.impliment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SpinnerAdapter
import android.widget.Toast
import com.example.impliment.adapters.SpinnerAdapterView___
import com.example.impliment.models.ImgClass
import com.example.impliment.models.Mp3Class
import com.example.impliment.shered.MySharedPreferens
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {
    lateinit var list:ArrayList<Mp3Class>
    lateinit var gson:Gson
    lateinit var spinnerAdapterView:SpinnerAdapterView___
    lateinit var imgList:ArrayList<ImgClass>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        MySharedPreferens.init(this)
        imgList = arrayListOf(ImgClass(R.drawable.mp3_2,"Image 1"),
            ImgClass(R.drawable.mp31,"Image 2"),
            ImgClass(R.drawable.mp3_1,"Image 3"),
            ImgClass(R.drawable.mp3_3,"Image 4"))
        spinnerAdapterView = SpinnerAdapterView___(imgList)

        spinner_view.adapter = spinnerAdapterView
        add_btn.setOnClickListener {
            var mp3_name = edite1.text.toString()
            var name_se = edite2.text.toString()
            var info = edite3.text.toString()
            var img_N = imgList[spinner_view.selectedItemPosition]
            list = ArrayList()
            if (mp3_name.isNullOrBlank() && name_se.isNullOrBlank() && info.isNullOrBlank()){
                Toast.makeText(this, "Malumotlar mavjud emas", Toast.LENGTH_SHORT).show()
            }else if (mp3_name.isNullOrBlank()){
                Toast.makeText(this, "Qo`q nomi kiritilmagan", Toast.LENGTH_SHORT).show()
            }else if (name_se.isNullOrBlank()){
                Toast.makeText(this, "Qo`qchining nomi mavjud emas", Toast.LENGTH_SHORT).show()
            }else if (info.isNullOrBlank()){
                Toast.makeText(this, "Kim kiritdi?", Toast.LENGTH_SHORT).show()
            }else if (mp3_name.isNotEmpty() && name_se.isNotEmpty() && info.isNotEmpty()){
              var str = MySharedPreferens.text
                 gson = Gson()
                if(str!!.isNotEmpty()){
                    var type = object:TypeToken<ArrayList<Mp3Class>>(){}.type
                    list.addAll(gson.fromJson(str,type))
                }
                var isHave= false
                println(img_N.img)
                var mp3Class = Mp3Class(mp3_name,name_se,info,img_N)
                for (i in list.indices){
                    if (list[i].mp3_name!! ==mp3_name ){
                        isHave=true
                    }
                }
                if (!isHave){
                    list.add(mp3Class)
                    MySharedPreferens.text = gson.toJson(list)
                    println(MySharedPreferens.text)
                    finish()
                }else{
                    Toast.makeText(this, "Bu Qo`shiq mavjud", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }


}