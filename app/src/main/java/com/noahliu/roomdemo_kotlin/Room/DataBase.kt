package com.noahliu.roomdemo_kotlin.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [(MyData::class)],version = 1, exportSchema = false)
abstract class DataBase: RoomDatabase() {
    companion object {
        val DB_NAME = "RecordData.db" //資料庫名稱
        const val TABLENAME = "MyTable"
        @Volatile
        private var instance: DataBase? = null
        @Synchronized
        fun getInstance(context: Context): DataBase? {
            if (instance == null) {
                instance = create(context) //創立新的資料庫
            }
            return instance
        }

        private fun create(context: Context): DataBase{
            return Room.databaseBuilder(context, DataBase::class.java, DB_NAME).build()
        }

    }
    abstract fun getDataUao(): Dao


}