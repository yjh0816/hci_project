package com.example.homecookshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_add_new_recipe.*
import kotlinx.android.synthetic.main.activity_add_voc.*
import kotlinx.android.synthetic.main.activity_add_voc.addFood
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row.*
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class AddVocActivity : AppCompatActivity() {
    var data:ArrayList<MyData2> = ArrayList()
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: MyAdapter2
    var pos: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_voc)

        if(intent.hasExtra("isOpen")){
            //val isOpen = intent.getStringExtra("isOpen")
            val isOpen = intent.getBooleanExtra("isOpen",false)

            //Log.v("isOpen",isOpen.toString())
        }
        if(intent.hasExtra("pos")){
            //val isOpen = intent.getStringExtra("isOpen")
            pos = intent.getIntExtra("pos",0)

            //Log.v("pos", pos.toString())
        }
        if(pos==0){
            menu_name.setText("된장찌개")
        }else if(pos==1){
            menu_name.setText("김치찌개")
        }else if(pos==2){
            menu_name.setText("청국장")
        }else if(pos==3){
            menu_name.setText("카레")
        }
        addFood.setOnClickListener {
            val intent = Intent(this, material_add::class.java)
            startActivity(intent)
        }
        recipe_add2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        shop_add2.setOnClickListener {
            val intent = Intent(this, ShopActivity::class.java)
            startActivity(intent)
        }
        cook_add2.setOnClickListener {
            val intent = Intent(this, CookActivity::class.java)
            startActivity(intent)
        }
        init()
        initData()
        initRecyclerView()
    }

    private fun init() {
//        val addbtn = findViewById<Button>(R.id.button3)
//        val cancelbtn = findViewById<Button>(R.id.button4)
//        val editword = findViewById<EditText>(R.id.word)
//        val editmeaning = findViewById<EditText>(R.id.meaning)
//
//        addbtn.setOnClickListener {
//            val word = editword.text.toString()
//            val meaning = editword.text.toString()
//            writeFile(word, meaning)
//        }
//
//        cancelbtn.setOnClickListener {
//            setResult(Activity.RESULT_CANCELED)
//            finish()
//        }
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
            val intent = Intent(this, material_confirm::class.java)
            startActivity(intent)
            material_Dialog.dismiss()

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

    private fun initRecyclerView() {
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL, false)
        adapter = MyAdapter2(data)
        adapter.itemClickListener = object : MyAdapter2.OnItemClickListener{
            override fun OnItemClick(
                holder: MyAdapter2.ViewHolder,
                view: View,
                data: MyData2,
                position: Int
            ) {
                //adapter.showMeaning(holder,data,position)
                material_modal(data.menu_name, data.word, data.meaning, data.food, position)
            }

        }
        recyclerView.adapter = adapter
        val simpleCallBack = object: ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.DOWN or ItemTouchHelper.UP,
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
    fun readFileScan(scan: Scanner){
        var count = 0
        while(scan.hasNextLine()){
            val word = scan.nextLine()
            val meaning = scan.nextLine()
            val food = scan.nextLine()

            val arr = food.split('/')

            for(i in arr.indices){
                //Log.v("as",arr[i])
                if(pos != count){
                    break;
                }
                var arr2 = arr[i].split(' ')
                data.add(MyData2(word, arr2[0], arr2[1], arr2[2],false))
            }
            count = count + 1
        }
        scan.close()
    }

    private fun initData() {
        try{
            val scan2 = Scanner(openFileInput( "out.txt"))
            readFileScan(scan2)
        }catch (e: Exception){
            //Toast.makeText(this, "추가된 단어가 없음", Toast.LENGTH_SHORT).show()
        }

        val scan = Scanner(resources.openRawResource(R.raw.words))
        readFileScan(scan)
    }
    private fun writeFile(menuname:String, material:String, unit:String, count:String, position: Int) {
        var count = 0
        val scan = Scanner(resources.openRawResource(R.raw.words))
        while(scan.hasNextLine()){
            val word = scan.nextLine()
            val meaning = scan.nextLine()
            val food = scan.nextLine()
            val input =  material + " " + unit + " " + count + "/"
            val arr = food.split('/')

            if(menuname == word){
                for(i in arr.indices){
                    //기존의 정보와 같다면 -> 현재 arr[i]를 input으로 변경
                }
            }
            else{
                continue
            }
            /*for(i in arr.indices){
                if(pos != count){
                    break;
                }
                var arr2 = arr[i].split(' ')
                data.add(MyData2(word, arr2[0], arr2[1], arr2[2],false))
            }
            count = count + 1*/
        }
        scan.close()
    }

}