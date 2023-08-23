package com.example.individualproject.ui.theme.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    // 음식점이름, 음식메뉴, 칼로리, 가격
    @ColumnInfo("store") var store: String? = null,
    @ColumnInfo("menu") var menu: String? = null,
    @ColumnInfo("calorie") var calorie: String? = null,
    @ColumnInfo("price") val price: String? = null,
    @ColumnInfo("image") var image: Int? = null,
)