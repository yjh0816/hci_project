package com.example.homecookshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_cook.*
import kotlin.collections.ArrayList

class CookActivity : AppCompatActivity() {
    var data:ArrayList<MyData> = ArrayList()
    lateinit var recyclerView: RecyclerView
    var isTtsReady = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cook)
        addRecipe.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }
        kimchi_rate.setOnClickListener {
            val intent = Intent(this, CookActivity2::class.java)
            startActivity(intent)
        }
        kimchi_percent.setOnClickListener {
            val intent = Intent(this, CookActivity2::class.java)
            startActivity(intent)
        }
        curry_rate.setOnClickListener {
            val intent = Intent(this, CookActivity3::class.java)
            startActivity(intent)
        }
        curry_percent.setOnClickListener {
            val intent = Intent(this, CookActivity3::class.java)
            startActivity(intent)
        }
        recipe_add31.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        shop_add31.setOnClickListener {
            val intent = Intent(this, ShopActivity4::class.java)
            startActivity(intent)
        }
        cook_add31.setOnClickListener {
            val intent = Intent(this, CookActivity::class.java)
            startActivity(intent)
        }
    }
}