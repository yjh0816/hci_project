package com.example.homecookshop

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row.*
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var data:ArrayList<MyData> = ArrayList()
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: MyAdapter
    lateinit var tts:TextToSpeech
    var isTtsReady = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recipe.setOnClickListener {
            material_modal()
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

        initData()
        initRecyclerView()
        initTTS()
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

    private fun initTTS() {
        tts = TextToSpeech(this, TextToSpeech.OnInitListener {
            isTtsReady = true
            tts.language = Locale.US
        })
    }

    override fun onStop() {
        super.onStop()
        tts.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        tts.shutdown()
    }

    private fun initRecyclerView() {
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false)
        adapter = MyAdapter(data)
        adapter.itemClickListener = object : MyAdapter.OnItemClickListener{
            override fun OnItemClick(
                    holder: MyAdapter.ViewHolder,
                    view: View,
                    data: MyData,
                    position: Int
            ) {
                if(isTtsReady)
                    tts.speak(data.word, TextToSpeech.QUEUE_ADD, null, null)
                //Toast.makeText(applicationContext, data.meaning, Toast.LENGTH_SHORT).show()
                adapter.showMeaning(holder,data,position)
            }

        }
        recyclerView.adapter = adapter
        val simpleCallBack = object:ItemTouchHelper.SimpleCallback(ItemTouchHelper.DOWN or ItemTouchHelper.UP,
                ItemTouchHelper.RIGHT){
            override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
            ): Boolean {
                adapter.moveItem(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.removeItem(viewHolder.adapterPosition)
            }

        }
        val itemTouchHelper = ItemTouchHelper(simpleCallBack)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
    fun readFileScan(scan:Scanner){
        while(scan.hasNextLine()){
            val word = scan.nextLine()
            val meaning = scan.nextLine()
            data.add(MyData(word, meaning, false))
        }
        scan.close()
    }

    private fun initData() {
        try{
            val scan2 = Scanner(openFileInput("out.txt"))
            readFileScan(scan2)
        }catch (e:Exception){
            Toast.makeText(this, "추가된 단어가 없음", Toast.LENGTH_SHORT).show()
        }

        val scan = Scanner(resources.openRawResource(R.raw.words))
        readFileScan(scan)
    }
}