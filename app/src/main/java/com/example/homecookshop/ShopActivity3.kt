package com.example.homecookshop

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.cook
import kotlinx.android.synthetic.main.activity_main.recipe
import kotlinx.android.synthetic.main.activity_main.shop
import kotlinx.android.synthetic.main.activity_shop.*
import kotlinx.android.synthetic.main.activity_shop2.*
import kotlinx.android.synthetic.main.activity_shop3.*
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class ShopActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop3)
        third.setOnClickListener {
            material_modal("된장찌개","호박","1","개",0)
        }
        recipe.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        shop.setOnClickListener {
            val intent = Intent(this, ShopActivity::class.java)
            startActivity(intent)
        }
        cook.setOnClickListener {
            val intent = Intent(this, CookActivity::class.java)
            startActivity(intent)
        }
        linegogo3.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG)

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
            val material = edit_material.text.toString()
            val unit = edit_unit.text.toString()
            val count  = edit_count.text.toString()
            val input_text = material+unit+count
            //writeFile(input_text)

            material_Dialog.dismiss()
            val intent = Intent(this, ShopActivity4::class.java)
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