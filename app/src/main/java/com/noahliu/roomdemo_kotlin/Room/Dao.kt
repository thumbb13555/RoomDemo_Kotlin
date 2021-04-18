package com.noahliu.roomdemo_kotlin.Room

import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {
    /**=======================================================================================*/
    /**======================================================================================= */
    /**簡易新增所有資料的方法 */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun  //預設萬一執行出錯怎麼辦，REPLACE為覆蓋
            insertData(myData: MyData)

    /**複雜(?)新增所有資料的方法 */
    @Query("INSERT INTO MyTable (name,phone,hobby,elseInfo,age) VALUES(:name,:phone,:hobby,:elseData,:age)")
    fun insertData(
        name: String,
        phone: String,
        hobby: String,
        elseData: String,
        age: Int
    )

    /**=======================================================================================*/
    /**======================================================================================= */
    /**撈取全部資料 */
    @Query("SELECT * FROM MyTable")
    fun displayAll(): MutableList<MyData>

    /**撈取某個名字的相關資料 */
    @Query("SELECT * FROM MyTable WHERE name = :name")
    fun findDataByName(name: String): MutableList<MyData>

    /**=======================================================================================*/
    /**======================================================================================= */
    /**簡易更新資料的方法 */
    @Update
    fun updateData(myData: MyData)

    /**複雜(?)更新資料的方法 */
    @Query("UPDATE MyTable SET name = :name,phone=:phone,hobby=:hobby,elseInfo = :elseInfo,age= :age WHERE id = :id")
    fun updateData(
        id: Int,
        name: String,
        phone: String,
        hobby: String,
        elseInfo: String,
        age: Int
    )

    /**=======================================================================================*/
    /**======================================================================================= */
    /**簡單刪除資料的方法 */
    @Delete
    fun deleteData(myData: MyData)

    /**複雜(?)刪除資料的方法 */
    @Query("DELETE  FROM MyTable WHERE id = :id")
    fun deleteData(id: Int)
}