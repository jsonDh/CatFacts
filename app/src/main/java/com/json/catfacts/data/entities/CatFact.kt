package com.json.catfacts.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "catfacts", indices = [Index(value = ["fact", "image_url"], unique = true)])
data class CatFact(

    @ColumnInfo(name = "fact")
    @SerializedName("fact")
    val fact: String?,

    @ColumnInfo(name = "size")
    @SerializedName("length")
    val size: Int?,

    @ColumnInfo(name = "image_url")
    @SerializedName("url")
    var url : String,

    @PrimaryKey(autoGenerate = true) val id: Int = 0
)