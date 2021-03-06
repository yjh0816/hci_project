package com.example.homecookshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_add_new_recipe.*
import kotlinx.android.synthetic.main.activity_add_voc.*
import kotlinx.android.synthetic.main.activity_add_voc.addFood
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_material_confirm.*

class material_confirm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_confirm)
        val textView4: LinearLayout = findViewById(R.id.textView4)

        recipe_add4.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        shop_add4.setOnClickListener {
            val intent = Intent(this, ShopActivity::class.java)
            startActivity(intent)
        }
        cook_add4.setOnClickListener {
            val intent = Intent(this, CookActivity::class.java)
            startActivity(intent)
        }
        textView4.setOnClickListener{
            material_modal("","청양고추","반","개",0)
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

        }
        cancle_button.setOnClickListener {
            material_Dialog.dismiss()
        }
        delete_button.setOnClickListener {
            val intent = Intent(this, material_delete::class.java)
            startActivity(intent)
            material_Dialog.dismiss()
            //해당 재료 삭제
        }
    }
}