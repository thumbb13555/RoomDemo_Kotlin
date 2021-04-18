package com.noahliu.roomdemo_kotlin

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.noahliu.roomdemo_kotlin.Room.DataBase
import com.noahliu.roomdemo_kotlin.Room.MyData


class MyAdapter(val activity:Activity) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    var arrayList:MutableList<MyData> = ArrayList()
    lateinit var onItemClick:OnItemClickListener

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.findViewById<TextView>(android.R.id.text1)
    }
    //顯示
    fun showData(){
        Thread{
            arrayList = DataBase.getInstance(activity)!!.getDataUao().displayAll()
            activity.runOnUiThread { notifyDataSetChanged() }
        }.start()
    }
    //新增
    fun insertData(myData: MyData){
        Thread{
            DataBase.getInstance(activity)!!.getDataUao().insertData(myData)
            activity.runOnUiThread {
                showData()
            }
        }.start()
    }
    //刪除
    fun deleteData(position: Int){
        Thread{
            DataBase.getInstance(activity)!!.getDataUao().deleteData(arrayList[position].id)
            activity.runOnUiThread {
                showData()
            }
        }.start()
    }
    //更新
    fun updateData(myData: MyData){
        Thread{
            DataBase.getInstance(activity)!!.getDataUao().updateData(myData)
            activity.runOnUiThread {
                showData()
            }
        }.start()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1,null))
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvTitle.text = arrayList[position].name
        holder.itemView.setOnClickListener{ onItemClick.onItemClick(arrayList[position]) }
    }
    interface OnItemClickListener {
        fun onItemClick(myData: MyData)
    }
}