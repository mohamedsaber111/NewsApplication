package com.androiddevs.mvvmnewsapp.dp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.androiddevs.mvvmnewsapp.models.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    //return long that is id we inserted
    suspend fun upsert(article: Article) : Long


    @Query("select * from articles")
    //return fun because it will return a livedata object and that doesn't work with suspend fun
    fun getAllArticles(): LiveData<List<Article>>


    @Delete
    suspend fun deleteArticle(article: Article)
}