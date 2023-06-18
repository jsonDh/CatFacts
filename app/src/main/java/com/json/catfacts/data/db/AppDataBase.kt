package com.json.catfacts.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.json.catfacts.data.entities.CatFact
import com.json.catfacts.data.entities.CatFactDao

@Database(entities = [CatFact::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun catFactDao(): CatFactDao

}