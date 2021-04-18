package com.noahliu.roomdemo_kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.stetho.Stetho
import com.noahliu.roomdemo_kotlin.Room.MyData
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var myAdapter:MyAdapter? = null
    var nowSelectedData:MyData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Stetho.initializeWithDefaults(this);//設置資料庫監視
        initUI()
    }
    private fun initUI(){
        //初始化UI
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        setRecyclerFunction(recyclerView)
        myAdapter = MyAdapter(this)
        recyclerView.adapter = myAdapter
        //顯示目前的資料
        myAdapter!!.showData()
        //設置點擊
        myAdapter!!.onItemClick = object :MyAdapter.OnItemClickListener{
            override fun onItemClick(myData: MyData) {
                nowSelectedData = myData
                editText_Name.setText(nowSelectedData!!.name)
                editText_Phone.setText(nowSelectedData!!.phone)
                editText_Hobby.setText(nowSelectedData!!.hobby)
                editText_else.setText(nowSelectedData!!.elseInfo)
                editText_age.setText(nowSelectedData!!.age.toString())
            }
        }
        //清掉目前顯示
        button_Clear.setOnClickListener {
            nowSelectedData = null
            clear()
        }
        //修改資料
        button_Modify.setOnClickListener {
            if (nowSelectedData == null) return@setOnClickListener
            val data = MyData(
                nowSelectedData!!.id
                ,editText_Name.text.toString()
                ,editText_Phone.text.toString()
                ,editText_Hobby.text.toString()
                ,editText_else.text.toString()
                , try { editText_age.text.toString().toInt() } catch (e: Exception) { 18 }
            )
            myAdapter!!.updateData(data)
            nowSelectedData = null
            clear()
        }
        //新增資料
        button_Create.setOnClickListener {
            val data = MyData(editText_Name.text.toString()
                ,editText_Phone.text.toString()
                ,editText_Hobby.text.toString()
                ,editText_else.text.toString()
                , try { editText_age.text.toString().toInt() } catch (e: Exception) { 18 }
            )
            myAdapter!!.insertData(data)
            clear()
        }


    }
    private fun clear(){
        editText_Name.text.clear()
        editText_Phone.text.clear()
        editText_Hobby.text.clear()
        editText_else.text.clear()
        editText_age.text.clear()
    }



    /**設置RecyclerView的左滑刪除行為 */
    private fun setRecyclerFunction(recyclerView: RecyclerView) {
        val helper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
            //設置RecyclerView手勢功能
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                return makeMovementFlags(
                    0,
                    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                )
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                when (direction) {
                    ItemTouchHelper.LEFT, ItemTouchHelper.RIGHT -> myAdapter!!.deleteData(position)
                }
            }
        })
        helper.attachToRecyclerView(recyclerView)
    }
}