package com.mricoism.wishlistapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wish-table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id: Long =0L,
    @ColumnInfo(name = "wish-title")
    val title: String = "",
    @ColumnInfo(name = "wish-desc")
    val description: String = ""
)


object DummyWish {
    val wishList = listOf(
        Wish(title = "Tiker Haji", description =  "Buat Mama sama Bapak"),
        Wish(title = "MACBOOK", description = "Buat kerja"),
        Wish(title = "Istri", description =  "Lengkapi hidup"),
    )
}
