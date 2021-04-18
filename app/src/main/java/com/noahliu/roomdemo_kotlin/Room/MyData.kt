package com.noahliu.roomdemo_kotlin.Room

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey




@Entity(tableName = DataBase.TABLENAME)
class MyData{


    @PrimaryKey(autoGenerate = true) //設置是否使ID自動累加
    var id = 0
    var name: String ="未填寫"
    var phone: String = "未填寫"
    var hobby: String = "未填寫"
    var elseInfo: String = "未填寫"
    var age = 18

    constructor(
        name: String,
        phone: String,
        hobby: String,
        elseInfo: String,
        age: Int
    ) {
        this.name = name
        this.phone = phone
        this.hobby = hobby
        this.elseInfo = elseInfo
        this.age = age
    }

    @Ignore //如果要使用多形的建構子，必須加入@Ignore
    constructor(
        id: Int,
        name: String,
        phone: String,
        hobby: String,
        elseInfo: String,
        age: Int
    ) {
        this.id = id
        this.name = name
        this.phone = phone
        this.hobby = hobby
        this.elseInfo = elseInfo
        this.age = age
    }

}