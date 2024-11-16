package com.mricoism.wishlistapp

import android.content.Context
import androidx.room.Room
import com.mricoism.wishlistapp.data.WishDatabase
import com.mricoism.wishlistapp.data.WishRepository

object Graph {
    lateinit var database: WishDatabase

    val wishRepository by lazy {
        WishRepository(wishDao = database.wishDao())
    }

    fun provide(context: Context) {
        database = Room.databaseBuilder(context = context, WishDatabase::class.java, "wishlist.db").build()

    }
}