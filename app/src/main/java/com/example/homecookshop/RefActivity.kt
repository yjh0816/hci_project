package com.example.homecookshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_ref.*

class RefActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ref)

        addRef.setOnClickListener {
            material_modal("", "", "", "", 0)
        }
    }

    private fun material_modal(str_name:String, str_word:String, str_meaning:String, str_food: String, position:Int){
        var builder  = AlertDialog.Builder(this)
        builder.setTitle("현재 보유중인 재료 추가")
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

            val intent = Intent(this, RefActivity2::class.java)
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