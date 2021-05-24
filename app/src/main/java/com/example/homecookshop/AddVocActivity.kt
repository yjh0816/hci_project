package com.example.homecookshop

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_add_voc.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.PrintStream

class AddVocActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_voc)
        addFood.setOnClickListener {
            material_modal()
        }
        init()
    }

    private fun init() {
        val addbtn = findViewById<Button>(R.id.button3)
        val cancelbtn = findViewById<Button>(R.id.button4)
        val editword = findViewById<EditText>(R.id.word)
        val editmeaning = findViewById<EditText>(R.id.meaning)

        addbtn.setOnClickListener {
            val word = editword.text.toString()
            val meaning = editword.text.toString()
            writeFile(word, meaning)
        }

        cancelbtn.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
    private fun material_modal(){
        var builder  = AlertDialog.Builder(this)
        builder.setTitle("재료 추가")
        builder.setView(R.mipmap.ic_launcher)

        var v1 = layoutInflater.inflate(R.layout.material_modal,null)
        builder.setView(v1)

        var listener = DialogInterface.OnClickListener { dialog, which ->
            var alert = dialog as AlertDialog
            var edit_material: EditText?= alert.findViewById(R.id.edit_material)
            var edit_count: EditText?= alert.findViewById(R.id.edit_count)
            var edit_unit: EditText?= alert.findViewById(R.id.edit_unit)

            /*tv1.text = "${edit1?.text}"
            tv1.append("${edit2?.text}")*/
        }

        builder.show()
    }

    private fun writeFile(word: String, meaning: String) {
        val output = PrintStream(openFileOutput("out.txt", Context.MODE_APPEND))
        output.println(word)
        output.println(meaning)
        output.close()
        val intent = Intent()
        intent.putExtra("voc", MyData(word,meaning,false))
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}