package com.example.homecookshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
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
            material_modal("", "", "", "", 0)
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
    private fun material_modal(str_name:String, str_word:String, str_meaning:String, str_food: String, position:Int){
        var builder  = AlertDialog.Builder(this)
        builder.setTitle("재료 추가")
        builder.setView(R.mipmap.ic_launcher)

        var v1 = layoutInflater.inflate(R.layout.material_modal,null)
        builder.setView(v1)
        val material_Dialog = builder.show()

        val confirm_Button = v1.findViewById<Button>(R.id.material_confirm)
        val cancle_button = v1.findViewById<Button>(R.id.material_cancle)
        val delete_button = v1.findViewById<Button>(R.id.material_delete)

        val edit_material = v1.findViewById<EditText>(R.id.edit_material)
        val edit_unit = v1.findViewById<EditText>(R.id.edit_unit)
        val edit_count = v1.findViewById<EditText>(R.id.edit_count)


        edit_material.setText(str_word)
        edit_count.setText(str_meaning)
        edit_unit.setText(str_food)

        confirm_Button.setOnClickListener {
            material_Dialog.dismiss()

            val intent = Intent(this, AddNewRecipe2::class.java)
            startActivity(intent)

        }
        cancle_button.setOnClickListener {
            material_Dialog.dismiss()
        }
        delete_button.setOnClickListener {
            Log.v("as", position.toString())
            material_Dialog.dismiss()
            //해당 재료 삭제
        }
    }
}