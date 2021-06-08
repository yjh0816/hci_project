package com.example.homecookshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_shop.*
import kotlinx.android.synthetic.main.activity_shop.first

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

//        recipe_add7.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }
//        shop_add7.setOnClickListener {
//            val intent = Intent(this, ShopActivity::class.java)
//            startActivity(intent)
//        }
        cook_add99.setOnClickListener {
            val intent = Intent(this, CookActivity2::class.java)
            startActivity(intent)
        }

        first99.setOnClickListener {
            material_modal3("된장찌개","된장","100","g",0)
        }
        gobackhome2.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }
    }
    private fun material_modal3(str_name:String, str_word:String, str_meaning:String, str_food: String, position:Int){
        var builder  = AlertDialog.Builder(this)
        //builder.setTitle("재료 추가")
        builder.setView(R.mipmap.ic_launcher)

        var v1 = layoutInflater.inflate(R.layout.material_modal3,null)
        builder.setView(v1)
        val material_Dialog = builder.show()

        val confirm_Button = v1.findViewById<Button>(R.id.material_confirm)
        val cancle_button = v1.findViewById<Button>(R.id.material_cancle)

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
            val intent = Intent(this, ShopActivity2::class.java)
            startActivity(intent)
        }
        cancle_button.setOnClickListener {
            material_Dialog.dismiss()
        }
    }
}