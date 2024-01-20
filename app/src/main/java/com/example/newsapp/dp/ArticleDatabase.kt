package com.example.newsapp.dp

import android.content.Context
import androidx.room.*
import com.example.newsapp.models.Article

@Database(entities = [Article::class], version = 1)
@TypeConverters(Converters::class)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao

    companion object {
        //means that other threads can immediately see when the thread changes this instance
        @Volatile
        private var INSTANCE: ArticleDatabase? = null

        private val LOCK = Any()
        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
            //everything that happens inside of block code here can't be accessed by other threads at same time
            return INSTANCE ?: createDatabase(context).also { INSTANCE = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_db.db"
            ).build()

    }

//    companion object {
//        @Volatile
//        private var INSTANCE: ArticleDatabase? = null
//
//        fun getInstance(context: Context): ArticleDatabase {
//            synchronized(this) {
//                return INSTANCE ?: Room.databaseBuilder(
//                    context.applicationContext,
//                    ArticleDatabase::class.java,
//                    "article_db.db"
//                ).build().also {
//                    INSTANCE = it
//                }
//            }
//        }
//    }
}