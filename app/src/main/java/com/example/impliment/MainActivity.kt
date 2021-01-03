package com.example.impliment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.impliment.adapters.Mp3Adapter
import com.example.impliment.models.Mp3Class
import com.example.impliment.shered.MySharedPreferens
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.rv
import kotlinx.android.synthetic.main.activity_mp3_list_class.*

class MainActivity : AppCompatActivity() {
    lateinit var mp3List:ArrayList<Mp3Class>
    lateinit var mp3Adapter: Mp3Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MySharedPreferens.init(this)
        loadData()
         floatingActionButton.setOnClickListener {
             var intent = Intent(this,AddActivity::class.java)
             startActivity(intent)
         }
    }
    override fun onResume() {
        super.onResume()
        MySharedPreferens.init(this)
        loadData()
        mp3Adapter = Mp3Adapter(this, mp3List, object : Mp3Adapter.MyItemClickListener {
            override fun onItemClick(mp3Class: Mp3Class, position: Int) {
                var intent = Intent(this@MainActivity, Mp3ClickMusick::class.java)
                var bundle = Bundle()
                bundle.putString("name", mp3Class.mp3_name)
                bundle.putInt("img", mp3Class.img!!.img!!)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })
        rv.adapter = mp3Adapter
        var itemTouch = object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
               var dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                var swipeFlags = ItemTouchHelper.END
                return makeMovementFlags(dragFlags,swipeFlags)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                mp3Adapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                mp3Adapter.onItemDismiss(viewHolder.adapterPosition)
            }

        }
           var itemTouchHelper = ItemTouchHelper(itemTouch)
        itemTouchHelper.attachToRecyclerView(rv)
    }
    private fun loadData() {
        mp3List = ArrayList()
        var str = MySharedPreferens.text
        var gson = Gson()
        if (str!!.isNotEmpty()) {
            var type = object : TypeToken<ArrayList<Mp3Class>>() {}.type
            mp3List.addAll(gson.fromJson(str, type))
        }
    }
}