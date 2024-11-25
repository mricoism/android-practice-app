package com.mricoism.newsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mricoism.newsapp.models.Article

@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class ArticleDatabase: RoomDatabase() {

    abstract fun getArticleDao(): ArticleDAO

    companion object { // basically this block ensures that only one thread can execute the code inside the block at a time
        @Volatile // ensure that changes made by one thread are immediately visible to other threads
        private var instance: ArticleDatabase? = null
        private val LOCK = Any() // use to synchronize the database creation process


        /*
            basically it allows you to create an instance of article database by calling article database context.
            in work function follows the Singleton pattern ensuring that only one instance is created.
         */
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            /*
            then this line check if the instance is already initialized
             if not then it enters a synchronized block, using lock object to ensure
             that only one thread can create the database instance at a time.
              then inside the block, again it checks if the instance is still null
              and then creates the database using the create database function.


             */
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ArticleDatabase::class.java,
            "article_db.db"
        ).build()


    }

}