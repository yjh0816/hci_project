package com.example.homecookshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_add_new_recipe.*
import kotlinx.android.synthetic.main.activity_add_voc.*
import kotlinx.android.synthetic.main.activity_add_voc.addFood
import kotlinx.android.synthetic.main.activity_main.*

class AddNewRecipe : AppCompatActivity() {
    var data:ArrayList<MyData> = ArrayList()
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: MyAdapter
    lateinit var tts: TextToSpeech
    var isTtsReady = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_recipe)

        addFood.setOnClickListener {
            val intent = Intent(this, AddNewRecipe::class.java)
            startActivity(intent)
        }
        recipe_add11.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        shop_add11.setOnClickListener {
            val intent = Intent(this, ShopActivity::class.java)
            startActivity(intent)
        }
        cook_add11.setOnClickListener {
            val intent = Intent(this, CookActivity::class.java)
            startActivity(intent)
        }
    }
}