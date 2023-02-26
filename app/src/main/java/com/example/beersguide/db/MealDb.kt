package com.example.beersguide.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.beersguide.pojo.Meal

@Database(entities = [Meal::class], version = 1)
@TypeConverters(MealTypeConverter::class)
abstract class MealDb:RoomDatabase() {
    abstract fun mealDao():MealDao
    companion object{
        @Volatile
        var instance:MealDb?=null
        @Synchronized
        fun getInstance(context: Context):MealDb{
          if(instance==null){
              instance= Room.databaseBuilder(context,MealDb::class.java,"meal.db")
                  .fallbackToDestructiveMigration().build()
          }
            return instance as MealDb
        }
    }
}