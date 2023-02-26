package com.example.beersguide.db

import androidx.room.TypeConverter
import androidx.room.TypeConverters

@TypeConverters
class MealTypeConverter {

    @TypeConverter
    fun anyToString(attr:Any?):String{
        return if(attr==null)
            ""
        else
            attr as String
    }
    @TypeConverter
    fun strToAny(attr:String?):Any{
        return attr ?: ""
    }
}