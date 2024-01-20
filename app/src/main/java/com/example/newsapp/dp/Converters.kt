package com.example.newsapp.dp

import androidx.room.TypeConverter
import com.example.newsapp.models.Source
//room only handle primitive datatype(String) not another class
//so we create type converter to convert Source class to ex "String" and string to Source so that room can treat with it
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