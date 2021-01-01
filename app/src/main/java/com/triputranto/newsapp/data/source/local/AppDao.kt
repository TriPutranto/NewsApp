package com.triputranto.newsapp.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.triputranto.newsapp.data.entity.Articles

/**
 * Created by Ahmad Tri Putranto on 01/01/2021.
 * */
@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToLocalData(articles: Articles)

    @Query("SELECT * FROM articles  ORDER BY id_article DESC")
    fun getAllLocalData(): DataSource.Factory<Int, Articles>

    @Query("SELECT * FROM articles WHERE title = :title")
    fun getLocalDataByTitle(title: String): LiveData<Articles>

    @Query("DELETE FROM articles WHERE title IN(:title)")
    fun deleteLocalDataByTitle(title: String)

}