package com.example.homecookshop

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_shop3.third
import kotlinx.android.synthetic.main.activity_shop4.*

class ShopActivity5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop5)
        addRecipe.setOnClickListener {
            material_modal("","장보기를 종료합니다.","","",0)
        }
        recipe_add10.setOnClickListener {
            val intent = Intent(this, AddNewRecipe5::class.java)
            startActivity(intent)
        }
        shop_add10.setOnClickListener {
            val intent = Intent(this, ShopActivity::class.java)
            startActivity(intent)
        }
        cook_add10.setOnClickListener {
            val intent = Intent(this, CookActivity::class.java)
            startActivity(intent)
        }
    }
    private fun material_modal(str_name:String, str_word:String, str_meaning:String, str_food: String, position:Int){
        var builder  = AlertDialog.Builder(this)
        //builder.setTitle("재료 추가")
        builder.setView(R.mipmap.ic_launcher)

        var v1 = layoutInflater.inflate(R.layout.material_modal4,null)
        builder.setView(v1)
        val material_Dialog = builder.show()

        val confirm_Button = v1.findViewById<Button>(R.id.material_confirm)
        val cancle_button = v1.findViewById<Button>(R.id.material_cancle)

        val edit_material = v1.findViewById<EditText>(R.id.edit_material)



        edit_material.setText(str_word)

        confirm_Button.setOnClickListener {
            val material = edit_material.text.toString()


            //writeFile(input_text)

            material_Dialog.dismiss()
            val intent = Intent(this, ShopActivity2::class.java)
            startActivity(intent)
        }
        cancle_button.setOnClickListener {
            material_Dialog.dismiss()
        }

    }
}