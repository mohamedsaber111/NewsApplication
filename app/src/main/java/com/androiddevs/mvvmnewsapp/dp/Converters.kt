package com.androiddevs.mvvmnewsapp.dp

import androidx.room.TypeConverter
import com.androiddevs.mvvmnewsapp.models.Source

class Converters {
    @TypeConverter
    fun fromSource(source: Source): String{
        //convert source to string by taking the name of the source
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name,name)
    }
}