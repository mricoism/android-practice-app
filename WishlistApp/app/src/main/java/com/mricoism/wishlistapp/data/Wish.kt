package com.mricoism.wishlistapp.data

data class Wish(
    val id: Long =0L,
    val title: String = "",
    val description: String = ""
)


object DummyWish {
    val wishList = listOf(
        Wish(title = "Tiker Haji", description =  "Buat Mama sama Bapak"),
        Wish(title = "MACBOOK", description = "Buat kerja"),
        Wish(title = "Istri", description =  "Lengkapi hidup"),
    )
}
