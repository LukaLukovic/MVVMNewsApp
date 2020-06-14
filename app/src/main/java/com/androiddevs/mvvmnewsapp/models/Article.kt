package com.androiddevs.mvvmnewsapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(
    tableName = "articles"

)
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
) :Serializable


//Trebamod a stavimo da je serializable da bi passovle arguments jer article nije primitivan datatype
//serialzible govori kotlinu da zselimo da passujemo ovaj class izmedju fragmenta za navigation
//components kotlin ce da uradi serialization iza scena

