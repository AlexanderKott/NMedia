package ru.netology.nmedia.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Posts")
data class Post (
    @PrimaryKey(autoGenerate = true)
    var id : Long? = 0,
    val likesCount : Int,
    val like : Boolean,
    val shareCount : Int,
    val viewsCount: Int,
    val caption : String,
    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    val body : String?,
    val published : String,
    val videoURI : String)