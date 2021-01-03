package com.example.impliment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_mp3_click_musick.*

class Mp3ClickMusick : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mp3_click_musick)
        val extras = intent.extras
        var name = extras?.getString("name")
        var img = extras?.getInt("img")
        name_mp3_z.text = name
        img_mp3_1.setImageResource(img!!)
    }
}