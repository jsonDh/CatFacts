package com.json.catfacts.data.entities

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CatFactDao {

    @Query("SELECT * FROM catfacts")
    suspend fun getAllFacts(): List<CatFact>

    @Insert
    suspend fun saveCatFact(catFact: CatFact)

    @Delete
    suspend fun deleteCatFact(catFact: CatFact)

}