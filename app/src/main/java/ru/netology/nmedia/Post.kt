package ru.netology.nmedia

data class Post (val likesCount : Int, val like : Boolean, val shareCount : Int, val viewsCount: Int,
val caption : String, val body : String, val published : String)